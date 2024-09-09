package com.harmlessprince.springdatarelationship.controllers;

import com.harmlessprince.springdatarelationship.dtos.requests.CategoryRequestDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.CategoryResponseDto;
import com.harmlessprince.springdatarelationship.service.implementations.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CategoryController implements ControllerInterface<CategoryResponseDto, CategoryRequestDto>{

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<CategoryResponseDto>> index() {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getCategories();
        return ResponseEntity.ok(categoryResponseDtos);
    }


    @PostMapping
    @Override
    public ResponseEntity<CategoryResponseDto> store(@RequestBody CategoryRequestDto requestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.addCategory(requestDto);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CategoryResponseDto> show(@PathVariable Integer id) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<CategoryResponseDto> update(@PathVariable Integer id, @RequestBody CategoryRequestDto requestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(id, requestDto);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @DeleteMapping("/{id}")
    @Override
    public void destroy(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}
