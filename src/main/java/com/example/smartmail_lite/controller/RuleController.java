package com.example.smartmail_lite.controller;

import com.example.smartmail_lite.entity.Rule;
import com.example.smartmail_lite.repository.RuleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    private final RuleRepository ruleRepository;

    public RuleController(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @GetMapping
    public List<Rule> listRules() {
        return ruleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rule> getRule(@PathVariable Long id) {
        Optional<Rule> found = ruleRepository.findById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(found.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
        Rule saved = ruleRepository.save(rule);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rule> updateRule(@PathVariable Long id, @RequestBody Rule rule) {
        Optional<Rule> found = ruleRepository.findById(id);
        if (found.isPresent()) {
            found.get().setName(rule.getName());
            found.get().setTag(rule.getTag());
            found.get().setCondition(rule.getCondition());
            return ResponseEntity.ok(found.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rule> deleteRule(@PathVariable Long id) {
        Optional<Rule> found = ruleRepository.findById(id);
        if (found.isPresent()) {
            ruleRepository.delete(found.get());
            return ResponseEntity.ok(found.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
