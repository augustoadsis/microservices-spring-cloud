package com.microservices.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findAllByUser(Long id);

    List<Subscription> findAllByCourse(Long id);
}
