package com.example.course_admin.repository;

import com.example.course_admin.database.FakeDB;
import com.example.course_admin.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    // Repository : Chứa các phương thức để thao tác với database
    // Lấy danh sách tất cả khóa học
    public List<Course> findAll() {
        return FakeDB.courses;
    }

    // Them khoa hoc
    public Course save(Course newCourse) {
        FakeDB.courses.add(newCourse);
        return newCourse;
    }

    // Lay danh sach theo type
    public List<Course> findByType(String type) {
        return FakeDB.courses.stream()
                .filter(course -> course.getType().equals(type))
                .toList();
    }

    // Lay khoa hoc theo id
    public Optional<Course> findById(int id) {
        return FakeDB.courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    public void deleteById(int id) {
        FakeDB.courses.removeIf(course -> course.getId() == id);
    }
}
