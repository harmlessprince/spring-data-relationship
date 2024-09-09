package com.harmlessprince.springdatarelationship.service.implementations;

import com.harmlessprince.springdatarelationship.dtos.requests.CategoryRequestDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.CategoryResponseDto;
import com.harmlessprince.springdatarelationship.exceptions.NotFoundException;
import com.harmlessprince.springdatarelationship.models.Category;
import com.harmlessprince.springdatarelationship.repositories.CategoryRepository;
import com.harmlessprince.springdatarelationship.service.interfaces.CategoryServiceInterface;
import com.harmlessprince.springdatarelationship.utils.MapperUtil;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInterface {

    private  final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        Category createdCategory = categoryRepository.save(category);
        return MapperUtil.categoryToCategoryResponseDto(createdCategory);
    }

    @Override
    public Category getCategory(Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()){
            throw  new NotFoundException("Category", categoryId);
        }
        return optionalCategory.get();
    }

    @Override
    public CategoryResponseDto getCategoryById(Integer categoryId) {
        Category category = getCategory(categoryId);
        return  MapperUtil.categoryToCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return MapperUtil.categoriesToCategoryResponseDtos(categories);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        return;
    }

    @Override
    public CategoryResponseDto updateCategory(Integer categoryId, CategoryRequestDto categoryRequestDto) {
        Category category = getCategory(categoryId);
        category.setName(categoryRequestDto.getName());
        Category updatedCategory = categoryRepository.save(category);
        return MapperUtil.categoryToCategoryResponseDto(updatedCategory);
    }
}
