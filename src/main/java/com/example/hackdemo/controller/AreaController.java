package com.example.hackdemo.controller;

import com.example.hackdemo.model.Area;
import com.example.hackdemo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping
    public List<Area> getAllAreas() {
        return areaService.getAllAreas();
    }

    @GetMapping("/{id}")
    public Area getAreaById(@PathVariable Long id) {
        return areaService.getAreaById(id);
    }

    /*    @PostMapping
    public Area createArea(@RequestBody Area area) {
        return areaService.saveArea(area);
    }

    @PutMapping("/{id}")
    public Area updateArea(@PathVariable Long id, @RequestBody Area area) {
        area.setId(id);
        return areaService.saveArea(area);
    }

    @DeleteMapping("/{id}")
    public void deleteArea(@PathVariable Long id) {
        areaService.deleteArea(id);
    }*/

}
