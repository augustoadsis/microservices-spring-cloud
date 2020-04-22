package com.microservices.course;

import com.microservices.core.exceptions.ObjectNotFoundException;
import com.microservices.core.response.PageService;
import com.microservices.core.user.AuthService;
import com.microservices.core.user.UserDTO;
import com.microservices.course.mq.CourseProducer;
import com.microservices.course.mq.CourseSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthService authService;

    @Autowired
    private CourseSource courseSource;
    @Autowired
    private CourseProducer courseProducer;

    public CourseDTO findById(Long id) {
        return findCourseById(id).map(CourseDTO::new).orElseThrow(() -> new ObjectNotFoundException("Course not found"));
    }

    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows({DataIntegrityViolationException.class})
    public Course save(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);

        UserDTO user = authService.findByToken();
        course.setUser(user.getId());
        return courseRepository.save(course);
    }

    public Page<CourseDTO> findAll(Integer page, Integer size, String orderBy, String filter, String param) {
        PageRequest pageRequest = PageService.of(page, size, filter, orderBy, new Course());
        Page<CourseDTO> courseDTO = courseRepository.findAll(param, pageRequest).map(CourseDTO::new);
        return courseDTO.getContent().isEmpty() ? null : courseDTO;
    }

    public CourseDTO update(Long id, CourseDTO courseDTO) {
        return findCourseById(id)
                .map(course -> {
                    course.setDescription(courseDTO.getDescription());
                    course.setTitle(courseDTO.getTitle());
                    course.setPrice(courseDTO.getPrice());
                    courseRepository.save(course);
                    return new CourseDTO(course);
                }).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Course course = findCourseById(id).orElseThrow(() -> new ObjectNotFoundException("Course not found"));
        course.softDelete();
        courseRepository.save(course);
        courseProducer.sendMessageCourse(new CourseDTO(course), courseSource);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByUser(Long id) {
        List<Course> courses = courseRepository.findAllByUser(id);
        courses.forEach(c -> {
            log.info("Removing course: " + c.toString());
            c.softDelete();
            courseRepository.save(c);
            courseProducer.sendMessageCourse(new CourseDTO(c), courseSource);
        });
    }

}
