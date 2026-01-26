package com.restaurant.matjip.data.service;

import com.restaurant.matjip.data.domain.Category;
import com.restaurant.matjip.data.dto.CategoryDTO;
import com.restaurant.matjip.data.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public CategoryDTO createCategory(String name) {
        if (categoryRepository.findByName(name).isPresent()) {
            throw new RuntimeException("Category already exists");
        }
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return CategoryDTO.fromEntity(category);
    }
}
