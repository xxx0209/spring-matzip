package com.restaurant.matjip.users.dto.response;

import com.restaurant.matjip.users.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
//    private String name;
    private User.Role role;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
//                user.getName(),
                user.getRole()
        );
    }
}
