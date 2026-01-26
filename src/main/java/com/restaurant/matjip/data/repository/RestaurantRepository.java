package com.restaurant.matjip.data.repository;

import com.restaurant.matjip.data.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
