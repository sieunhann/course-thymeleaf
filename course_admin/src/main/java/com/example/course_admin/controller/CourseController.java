package com.example.course_admin.controller;

import com.example.course_admin.model.Course;
import com.example.course_admin.request.CoursePostRequest;
import com.example.course_admin.request.CourseUpdateRequest;
import com.example.course_admin.service.CourseService;
import com.example.course_admin.service.SupporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SupporterService supporterService;

    @GetMapping("/course-list")
    public String getAllCourse(Model model){
        model.addAttribute("courses", courseService.findAll());
        return "course-list";
    }

    @GetMapping("/course-create")
    public String createCourse(Model model){
        model.addAttribute("supporters", supporterService.findAll());
        return "course-create";
    }

    @PostMapping("/course-create")
    public String createCourse(@RequestBody CoursePostRequest request){
         courseService.addCourse(request);
        return "redirect:course-list";
    }

    @GetMapping("/course-edit/{id}")
    public String getCourseById(@PathVariable int id, Model modelCourse, Model modelSupporter){
        modelCourse.addAttribute("course", courseService.findById(id));
        modelSupporter.addAttribute("supporters", supporterService.findAll());
        return "course-edit";
    }

    @PutMapping("/course-edit/{id}")
    public String updateCourseById(@PathVariable int id, @RequestBody CourseUpdateRequest request){
        courseService.updateCourse(id, request);
        return "course-list";
    }

    // Upload file
    @PostMapping("/course-edit/{id}/files")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file, @PathVariable int id){
        String filePath = courseService.uploadFile(id, file);
        return ResponseEntity.ok(filePath);
    }

    // Xem file
    @GetMapping("/course-edit/{id}/files")
    public ResponseEntity<?> readFile(@PathVariable int id){
        byte[] bytes = courseService.readFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    @DeleteMapping("/course-edit/{id}")
    public String deleteCourse(@PathVariable int id){
        courseService.deleteById(id);
        return "course-list";
    }
}
