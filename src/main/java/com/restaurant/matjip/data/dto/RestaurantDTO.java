package com.restaurant.matjip.data.dto;

import com.restaurant.matjip.data.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private Long id;
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String phone;
    private String description;
    private Set<String> categories;

    public static RestaurantDTO fromEntity(Restaurant r) {
        return new RestaurantDTO(
                r.getId(),
                r.getName(),
                r.getAddress(),
                r.getLatitude(),
                r.getLongitude(),
                r.getPhone(),
                r.getDescription(),
                r.getCategories().stream().map(c -> c.getName()).collect(Collectors.toSet())
        );
    }
}

