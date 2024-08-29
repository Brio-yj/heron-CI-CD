package com.example.hackdemo.controller;

import com.example.hackdemo.dto.CourseDTO;
import com.example.hackdemo.dto.CourseItemDTO;
import com.example.hackdemo.model.Course;
import com.example.hackdemo.model.CourseItem;
import com.example.hackdemo.model.Favorite;
import com.example.hackdemo.model.TourSpot;
import com.example.hackdemo.model.Area;
import com.example.hackdemo.repository.CourseRepository;
import com.example.hackdemo.service.CourseService;
import com.example.hackdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<Course>> getFavoriteCourse(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Long userId = Long.parseLong(authentication.getName());
        List<Course> favorites = userService.getFavoriteCourses(userId);

        return ResponseEntity.ok(favorites);
    }

    /*
    @PostMapping("/{id}/favorites")
    public ResponseEntity<?> toggleFavoriteCourse(@PathVariable Long id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in to favorite a Course");
        }

        Long userId = Long.parseLong(authentication.getName());
        userService.toggleFavorite(userId,null,null ,id);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        course.setId(id);
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }*/
}
