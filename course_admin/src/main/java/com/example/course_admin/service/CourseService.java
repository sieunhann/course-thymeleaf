package com.example.course_admin.service;

import com.example.course_admin.exception.NotFoundException;
import com.example.course_admin.model.Course;
import com.example.course_admin.repository.CourseRepository;
import com.example.course_admin.request.CoursePostRequest;
import com.example.course_admin.request.CourseUpdateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Random;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private ModelMapper modelMapper;
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Course doesn't exist");
        });
        return course;
    }

    public Course addCourse(CoursePostRequest request){
        Random rd = new Random();
        int id = rd.nextInt(1000);
        Course newCourse = modelMapper.map(request, Course.class);
        newCourse.setId(id);
        courseRepository.save(newCourse);
        return newCourse;
    }

    public void updateCourse(int id, CourseUpdateRequest request){
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Course doesn't exist");
        });
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setTopics(request.getTopics());
        course.setType(request.getType());
        course.setThumbnail(request.getThumbnail());
        course.setSupporterId(request.getSupporterId());
    }

    // Upload file
    public String uploadFile(int id, MultipartFile file) {
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Can not find user");
        });
        return fileService.uploadFile(id, file);
    }

    // Doc file
    public byte[] readFile(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Can not find user");
        });
        return fileService.readFile(id);
    }


    public void deleteById(int id) {
        courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Can not find user");
        });
        courseRepository.deleteById(id);
    }
}
