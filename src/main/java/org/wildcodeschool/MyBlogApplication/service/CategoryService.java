package org.wildcodeschool.MyBlogApplication.service;

import org.springframework.stereotype.Service;
import org.wildcodeschool.MyBlogApplication.dto.CategoryDTO;
import org.wildcodeschool.MyBlogApplication.model.Category;
import org.wildcodeschool.MyBlogApplication.mapper.CategoryMapper;
import org.wildcodeschool.MyBlogApplication.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryMapper categoryMapper, CategoryRepository categoryRepository){
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::convertToDTO).collect(Collectors.toList());
    }

    public CategoryDTO createCategory(Category category){
        Category saveCategory = categoryRepository.save(category);
        return categoryMapper.convertToDTO(saveCategory);
    }

    public CategoryDTO updateCategory(Long id, Category categoryDetails){
        Category category = categoryRepository.findById(id).orElse(null);
        category.setName(categoryDetails.getName());
        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.convertToDTO((updatedCategory));
    }

    public Boolean deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            return false;
        }
        categoryRepository.deleteById(id);
        return true;

    }

}
