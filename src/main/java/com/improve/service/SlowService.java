package com.improve.service;

import org.springframework.stereotype.Service;

@Service
public interface SlowService {
    public String slowProcess(int secs);
}
