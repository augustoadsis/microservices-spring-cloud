package com.microservices.subscription;

import com.microservices.core.exceptions.ObjectNotFoundException;
import com.microservices.core.response.PageService;
import com.microservices.core.user.AuthService;
import com.microservices.core.user.UserDTO;
import com.microservices.subscription.course.CourseDTO;
import com.microservices.subscription.course.CourseService;
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
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthService authService;
    @Autowired
    private CourseService courseService;

    public SubscriptionDTO findById(Long id) {
        return findSubscriptionById(id).map(SubscriptionDTO::new).orElseThrow(() -> new ObjectNotFoundException("Course not found"));
    }

    public Optional<Subscription> findSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows({DataIntegrityViolationException.class})
    public Subscription save(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = modelMapper.map(subscriptionDTO, Subscription.class);

        UserDTO user = authService.findByToken();
        CourseDTO course = courseService.findById(subscriptionDTO.getCourse());

        subscription.setUser(user.getId());
        subscription.setCourse(course.getId());
        return subscriptionRepository.save(subscription);
    }

    public Page<SubscriptionDTO> findAll(Integer page, Integer size, String orderBy, String filter, String param) {
        PageRequest pageRequest = PageService.of(page, size, filter, orderBy, new Subscription());
        Page<SubscriptionDTO> subscriptionDTO = subscriptionRepository.findAll(pageRequest).map(SubscriptionDTO::new);
        return subscriptionDTO.getContent().isEmpty() ? null : subscriptionDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Subscription subscription = findSubscriptionById(id).orElseThrow(() -> new ObjectNotFoundException("Course not found"));
        subscription.softDelete();
        subscriptionRepository.save(subscription);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByUser(Long id) {
        List<Subscription> subscriptions = subscriptionRepository.findAllByUser(id);
        subscriptions.forEach(s -> {
            log.info("Removing subscription: " + s.toString());
            s.softDelete();
            subscriptionRepository.save(s);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByCourse(Long id) {
        List<Subscription> subscriptions = subscriptionRepository.findAllByCourse(id);
        subscriptions.forEach(s -> {
            log.info("Removing subscription: " + s.toString());
            s.softDelete();
            subscriptionRepository.save(s);
        });
    }

}
