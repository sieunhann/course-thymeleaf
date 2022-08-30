package com.example.course_admin.controller;

import com.example.course_admin.model.Supporter;
import com.example.course_admin.service.SupporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupporterController {
    @Autowired
    private SupporterService supporterService;

    @GetMapping("/supporters")
    public List<Supporter> getAllSupporter(){
        return supporterService.findAll();
    }
}
