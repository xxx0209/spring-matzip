package com.restaurant.matjip.users.controller;

import com.restaurant.matjip.global.common.ApiResponse;
import com.restaurant.matjip.users.dto.request.UserCreateRequest;
import com.restaurant.matjip.users.dto.response.UserResponse;
import com.restaurant.matjip.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // -------------------------
    // 1️⃣ 회원 생성
    // -------------------------
    @PostMapping
    public ApiResponse<UserResponse> createUser(
            @RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.success(userService.create(request));
    }

    // -------------------------
    // 2️⃣ 회원 단일 조회
    // -------------------------
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(
            @PathVariable Long id) {
        return ApiResponse.success(userService.findById(id));
    }

    // -------------------------
    // 3️⃣ 회원 전체 조회
    // -------------------------
    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.success(userService.findAll());
    }

    // -------------------------
    // 4️⃣ 회원 정보 수정
    // -------------------------
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.success(userService.update(id, request));
    }

    // -------------------------
    // 5️⃣ 회원 삭제
    // -------------------------
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ApiResponse.success(null);
    }
}
