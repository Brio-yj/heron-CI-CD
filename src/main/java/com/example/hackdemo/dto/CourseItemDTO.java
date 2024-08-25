package com.example.hackdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseItemDTO {
    private Long id;
    private String description;
    private String mission;
    private String duration;
    private boolean isLandmark;
}
