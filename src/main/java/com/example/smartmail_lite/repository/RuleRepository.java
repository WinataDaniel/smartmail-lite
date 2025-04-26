package com.example.smartmail_lite.repository;

import com.example.smartmail_lite.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    Optional<Rule> findByName(String name);
}
