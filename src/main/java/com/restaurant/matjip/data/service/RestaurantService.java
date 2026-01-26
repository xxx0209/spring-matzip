package com.restaurant.matjip.data.service;

import com.restaurant.matjip.data.domain.Restaurant;
import com.restaurant.matjip.data.dto.RestaurantDTO;
import com.restaurant.matjip.data.repository.CategoryRepository;
import com.restaurant.matjip.data.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public RestaurantDTO createRestaurant(String name, String address, String phone, String description, List<Long> categoryIds) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setPhone(phone);
        restaurant.setDescription(description);

        if (categoryIds != null && !categoryIds.isEmpty()) {
            restaurant.setCategories(categoryIds.stream()
                    .map(id -> categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found")))
                    .collect(Collectors.toSet()));
        }

        restaurantRepository.save(restaurant);
        return RestaurantDTO.fromEntity(restaurant);
    }
}
