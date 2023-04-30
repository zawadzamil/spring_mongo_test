package com.example.spring_mongo.service;

import com.example.spring_mongo.dto.BlogCategoryDTO;
import com.example.spring_mongo.exceptions.ResourceAlreadyExistException;
import com.example.spring_mongo.exceptions.ResourceNotFoundException;
import com.example.spring_mongo.model.BlogCategory;
import com.example.spring_mongo.repository.BlogCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BlogCategoryService {
    private final BlogCategoryRepository blogCategoryRepository;

    public BlogCategory create (BlogCategoryDTO blogCategoryDTO){
        BlogCategory existingBlogCategory = blogCategoryRepository.findBySlug(blogCategoryDTO.getSlug());
        if(existingBlogCategory!=null){
            throw new ResourceAlreadyExistException("Slug must be unique");
        }
        BlogCategory blogCategory = new BlogCategory();
        BeanUtils.copyProperties(blogCategoryDTO,blogCategory);
        blogCategory.setCreatedAt(LocalDateTime.now());
        return blogCategoryRepository.save(blogCategory);
    }

    public Page<BlogCategory> getAll(String id,PageRequest pageRequest){
        return blogCategoryRepository.findAll(pageRequest);
    }

    public BlogCategory update(String id,BlogCategory category){
        BlogCategory oldBlogCategory = blogCategoryRepository.findById(id).map(item->{
            item.setName(category.getName());
            item.setSlug(category.getSlug());
            item.setUpdatedAt(LocalDateTime.now());

            return blogCategoryRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Blog category not found"));

        return oldBlogCategory;

    }

    public String delete(String id) {
        BlogCategory blogCategory = blogCategoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Blog category not found"));
        blogCategoryRepository.deleteById(id);
        return "Blog category deleted successfully.";
    }
}
