package com.example.hackdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String name;
    private String theme;
    private String duration;
    private List<CourseItemDTO> courseItems;
    private List<String> areaNames; // Area의 name만 포함
}
