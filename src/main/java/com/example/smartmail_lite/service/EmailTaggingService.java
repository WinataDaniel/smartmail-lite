package com.example.smartmail_lite.service;

import com.example.smartmail_lite.entity.Rule;
import com.example.smartmail_lite.mongo.EmailRecord;
import com.example.smartmail_lite.mongoRepository.EmailRecordRepository;
import com.example.smartmail_lite.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailTaggingService {
    private final RuleRepository ruleRepo;
    private final EmailRecordRepository emailRepo;

    public EmailTaggingService(RuleRepository ruleRepo, EmailRecordRepository emailRepo) {
        this.ruleRepo = ruleRepo;
        this.emailRepo = emailRepo;
    }

    public EmailRecord ingest(EmailRecord incoming) {
        List<Rule> rules = ruleRepo.findAll();

        List<String> appliedTags = new ArrayList<>();
        for(Rule r : rules) {
            if (incoming.getSubject().toLowerCase().contains(r.getCondition().toLowerCase())) {
                appliedTags.add(r.getTag());
            }
        }

        incoming.setTags(appliedTags);
        incoming.setReceivedAt(Instant.now());
        return emailRepo.save(incoming);
    }
}
