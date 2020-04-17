package com.microservices.course;

import com.microservices.core.exceptions.ObjectNotFoundException;
import com.microservices.core.response.PageService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CourseDTO findById(Long id) {
        return findExamById(id).map(CourseDTO::new).orElseThrow(() -> new ObjectNotFoundException("Course not found"));
    }

    public Optional<Course> findExamById(Long id) {
        return courseRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows({DataIntegrityViolationException.class})
    public Course save(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        return courseRepository.save(course);
    }

    public Page<CourseDTO> findAll(Integer page, Integer size, String orderBy, String filter, String param) {
        PageRequest pageRequest = PageService.of(page, size, filter, orderBy, new Course());
        Page<CourseDTO> examDTO = courseRepository.findAll(param, pageRequest).map(CourseDTO::new);
        return examDTO.getContent().isEmpty() ? null : examDTO;
    }

    public CourseDTO update(Long id, CourseDTO courseDTO) {
        return findExamById(id)
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
        Course course = findExamById(id).orElseThrow(() -> new ObjectNotFoundException("Course not found"));
        course.softDelete();
        courseRepository.save(course);
    }

}
