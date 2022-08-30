package com.example.course_admin.service;

import com.example.course_admin.model.Supporter;
import com.example.course_admin.repository.SupporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupporterService {
    @Autowired
    private SupporterRepository supporterRepository;

    public List<Supporter> findAll(){
        return supporterRepository.findAll();
    }
}
