package com.harmlessprince.springdatarelationship.service.interfaces;

import com.harmlessprince.springdatarelationship.dtos.requests.CategoryRequestDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.CategoryResponseDto;
import com.harmlessprince.springdatarelationship.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryServiceInterface {
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
    public Category getCategory(Integer categoryId);

    public CategoryResponseDto getCategoryById(Integer categoryId);

    public List<CategoryResponseDto> getCategories();

    public void deleteCategory(Integer categoryId);

    public CategoryResponseDto updateCategory(Integer categoryId, CategoryRequestDto categoryRequestDto);
}
