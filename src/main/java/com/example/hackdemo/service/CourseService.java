package com.example.hackdemo.service;

import com.example.hackdemo.dto.CourseDTO;
import com.example.hackdemo.dto.CourseItemDTO;
import com.example.hackdemo.model.Course;
import com.example.hackdemo.model.Area;
import com.example.hackdemo.repository.AreaRepository;
import com.example.hackdemo.repository.CourseItemRepository;
import com.example.hackdemo.repository.CourseRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseItemService courseItemService;

    private CourseDTO convertToDTO(Course course) {
        // 중복되지 않도록 Set 사용
        Set<String> areaNames = course.getAreas().stream()
                .map(Area::getName)
                .collect(Collectors.toSet()); // Set을 사용하여 중복 제거

        List<CourseItemDTO> courseItems = course.getCourseItems().stream()
                .map(courseItemService::convertItemToDTO)
                .collect(Collectors.toList());

        return new CourseDTO(course.getId(), course.getName(), course.getTheme(),
                course.getDuration(), courseItems, new ArrayList<>(areaNames)); // List로 변환
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.map(this::convertToDTO).orElse(null);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}