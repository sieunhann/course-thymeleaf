package com.example.course_admin.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int id;
    private String name;
    private String description;
    private String type;
    private List<String> topics;
    private String thumbnail;
    private int supporterId;
}
