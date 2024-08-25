package com.example.hackdemo.service;

import com.example.hackdemo.model.Area;
import com.example.hackdemo.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;

    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    public Area getAreaById(Long id) {
        return areaRepository.findById(id).orElse(null);
    }

    public Area saveArea(Area area) {
        return areaRepository.save(area);
    }

    public void deleteArea(Long id) {
        areaRepository.deleteById(id);
    }
    
}