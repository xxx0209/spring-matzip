package com.restaurant.matjip.users.controller;

import com.restaurant.matjip.global.common.ApiResponse;
import com.restaurant.matjip.global.common.CustomUserDetails;
import com.restaurant.matjip.global.exception.BusinessException;
import com.restaurant.matjip.global.exception.ErrorCode;
import com.restaurant.matjip.users.dto.request.UserCreateRequest;
import com.restaurant.matjip.users.dto.response.UserResponse;
import com.restaurant.matjip.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // -------------------------
    // 1 회원 생성
    // -------------------------
    @PostMapping
    public ApiResponse<UserResponse> createUser(
            @RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.success(userService.create(request));
    }

    // -------------------------
    // 2️ 회원 단일 조회
    // -------------------------
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(
            @PathVariable Long id) {
        return ApiResponse.success(userService.findById(id));
    }

    // -------------------------
    // 3️ 회원 전체 조회
    // -------------------------
    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.success(userService.findAll());
    }

    // -------------------------
    // 4️ 회원 정보 수정
    // -------------------------
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.success(userService.update(id, request));
    }

    // -------------------------
    // 5️ 회원 삭제
    // -------------------------
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ApiResponse.success(null);
    }

    // -------------------------
    // 6 로그인한 내정보 조회
    // -------------------------
    @GetMapping("/me")
    public ApiResponse<UserResponse> getMe(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_ERROR);
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        //todo : 권한관련하여 작업해야함.
        String role = "ROLE_USER";
        if (!userDetails.getAuthorities().isEmpty()) {
            GrantedAuthority firstAuthority = userDetails.getAuthorities().iterator().next();
            role = firstAuthority.getAuthority();
        }

        return ApiResponse.success(UserResponse.builder()
                .email(userDetails.getEmail())
                .id(userDetails.getId())
                .name(userDetails.getName())
                .nickname(userDetails.getNickname())
                .role(role)
                .build());
    }
}
