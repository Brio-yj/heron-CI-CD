package com.example.hackdemo;

import com.example.hackdemo.excel.ExcelService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HackDemoApplication{
	@Autowired
	private ExcelService excelService;
	public static void main(String[] args) {
		SpringApplication.run(HackDemoApplication.class, args);
	}

/*
 implements CommandLineRunner
	@Override
	public void run(String... args) {
		try {

			String areaFilePath = "document/area.xlsx";
			excelService.readAndSaveAreas(areaFilePath);

			String restaurantFilePath = "document/restaurant.xlsx";
			excelService.readAndSaveRestaurants(restaurantFilePath);

			String tourSpotFilePath = "document/tourSpot.xlsx";
			excelService.readAndSaveTourSpots(tourSpotFilePath);

			String courseFilePath = "document/course.xlsx";
			excelService.readAndSaveCourses(courseFilePath);

		} catch (InvalidFormatException e) {
			System.err.println("Invalid format in one of the Excel files: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error reading one of the Excel files: " + e.getMessage());
		}
	}
*/
}
