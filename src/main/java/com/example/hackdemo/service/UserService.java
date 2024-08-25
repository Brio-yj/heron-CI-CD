package com.example.hackdemo.service;

import com.example.hackdemo.model.*;
import com.example.hackdemo.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private TourSpotRepository tourSpotRepository;
    @Autowired
    private CourseRepository courseRepository;


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Transactional
    public User findOrCreateUser(String email, String name) {
        logger.info("findOrCreateUser called with email: {} and name: {}", email, name);

        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    logger.debug("User not found, creating new user");

                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(name);

                    User savedUser = userRepository.save(newUser);
                    logger.info("New user created with id: {}", savedUser.getId());

                    return savedUser;
                });
    }

    public void toggleFavorite(Long userId, Long restaurantId, Long tourSpotId, Long courseId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));

        Optional<Favorite> existingFavorite = Optional.empty();

        if (restaurantId != null) {
            existingFavorite = favoriteRepository.findByUserIdAndRestaurantId(userId, restaurantId);
        } else if (tourSpotId != null) {
            existingFavorite = favoriteRepository.findByUserIdAndTourSpotId(userId, tourSpotId);
        } else if (courseId != null) {
            existingFavorite = favoriteRepository.findByUserIdAndCourseId(userId, courseId);
        } else {
            throw new IllegalArgumentException("One of restaurantId, tourSpotId, or courseId must be provided");
        }

        if (existingFavorite.isPresent()) {
            favoriteRepository.delete(existingFavorite.get());
        } else {
            Favorite newFavorite = new Favorite();
            newFavorite.setUser(user);
            newFavorite.setCreatedAt(LocalDateTime.now());

            if (restaurantId != null) {
                Restaurant restaurant = restaurantRepository.findById(restaurantId)
                        .orElseThrow(() -> new NoSuchElementException("Restaurant not found"));
                newFavorite.setRestaurant(restaurant);
            } else if (tourSpotId != null) {
                TourSpot tourSpot = tourSpotRepository.findById(tourSpotId)
                        .orElseThrow(() -> new NoSuchElementException("TourSpot not found"));
                newFavorite.setTourSpot(tourSpot);
            } else if (courseId != null) {
                Course course = courseRepository.findById(courseId)
                        .orElseThrow(() -> new NoSuchElementException("Course not found"));
                newFavorite.setCourse(course);
            }

            favoriteRepository.save(newFavorite);
        }
    }

    public List<Restaurant> getFavoriteRestaurants(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserIdAndRestaurantIsNotNull(userId);
        return favorites.stream()
                .map(Favorite::getRestaurant)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<TourSpot> getFavoriteTourSpots(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserIdAndTourSpotIsNotNull(userId);
        return favorites.stream()
                .map(Favorite::getTourSpot)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Course> getFavoriteCourses(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserIdAndCourseIsNotNull(userId);
        return favorites.stream()
                .map(Favorite::getCourse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Favorite> getAllFavorites(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return favoriteRepository.findAllByUserId(userId);
    }
}
