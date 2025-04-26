package com.example.smartmail_lite.mongoRepository;

import com.example.smartmail_lite.mongo.EmailRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmailRecordRepository
        extends MongoRepository<EmailRecord, String> {
    List<EmailRecord> findByTags(String tag);
}