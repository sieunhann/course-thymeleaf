package com.example.course_admin.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateRequest {
    private String name;
    private String description;
    private String type;
    private List<String> topics;
    private String thumbnail;
    private int supporterId;
}
