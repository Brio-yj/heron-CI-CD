package com.example.hackdemo.service;

import com.example.hackdemo.dto.CourseItemDTO;
import com.example.hackdemo.model.CourseItem;
import com.example.hackdemo.repository.CourseItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseItemService {
    @Autowired
    private CourseItemRepository courseItemRepository;
    public CourseItemDTO convertItemToDTO(CourseItem courseItem) {
        return new CourseItemDTO(courseItem.getId(),
                courseItem.getDescription(),
                courseItem.getMission(),
                courseItem.getDuration(),
                courseItem.isLandmark());
    }

    public List<CourseItem> getAllCourseItems() {
        return courseItemRepository.findAll();
    }

    public CourseItem getCourseItemById(Long id) {
        return courseItemRepository.findById(id).orElse(null);
    }

    public CourseItem saveCourseItem(CourseItem courseItem) {
        return courseItemRepository.save(courseItem);
    }

    public void deleteCourseItem(Long id) {
        courseItemRepository.deleteById(id);
    }
}
