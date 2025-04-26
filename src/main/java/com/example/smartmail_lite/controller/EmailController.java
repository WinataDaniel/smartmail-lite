package com.example.smartmail_lite.controller;

import com.example.smartmail_lite.mongo.EmailRecord;
import com.example.smartmail_lite.service.EmailTaggingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
public class EmailController {
    private final EmailTaggingService tagging;

    public EmailController(EmailTaggingService tagging) {
        this.tagging = tagging;
    }

    @PostMapping
    public ResponseEntity<EmailRecord> ingestEmail(@RequestBody EmailRecord in) {
        EmailRecord saved = tagging.ingest(in);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
