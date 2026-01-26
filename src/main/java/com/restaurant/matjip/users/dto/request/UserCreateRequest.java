package com.restaurant.matjip.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @NotBlank
    @Email
    @Size(max = 100) // DB 컬럼과 동일하게
    private String email;

    @NotBlank
    @Size(min = 8, max = 100) // 해시 길이 기준 고려, 최소 8자
    private String password;

    @NotBlank
    @Size(max = 10)
    private String role;   // "USER" or "ADMIN"

    @NotBlank
    @Size(max = 10)
    private String status; // "ACTIVE", "BLOCKED", "DELETED"
}
