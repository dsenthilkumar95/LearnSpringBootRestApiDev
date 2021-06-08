package com.improve.serviceImpl;

import com.improve.service.SlowService;
import org.springframework.stereotype.Service;

@Service
public class SlowServiceImpl implements SlowService {
    @Override
    public String slowProcess(int secs){
        try {
            Thread.sleep(secs*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return String.valueOf(secs);
    }
}
