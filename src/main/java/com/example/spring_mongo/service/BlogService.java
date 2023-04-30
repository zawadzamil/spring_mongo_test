package com.example.spring_mongo.service;

import com.example.spring_mongo.dto.BlogDTO;
import com.example.spring_mongo.enums.StatusEnum;
import com.example.spring_mongo.exceptions.ResourceAlreadyExistException;
import com.example.spring_mongo.exceptions.ResourceNotFoundException;
import com.example.spring_mongo.model.Blog;
import com.example.spring_mongo.model.BlogCategory;
import com.example.spring_mongo.repository.BlogCategoryRepository;
import com.example.spring_mongo.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogCategoryRepository blogCategoryRepository;

    public Blog create(BlogDTO blogDTO){
        Blog existingBlog = blogRepository.findBySlug(blogDTO.getSlug());
        if(existingBlog != null){
            throw new ResourceAlreadyExistException("Slug must be unique");
        }

        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDTO,blog);

        blog.setCreatedAt(LocalDateTime.now());

        if(blogDTO.getCategory() != null){
            BlogCategory category = blogCategoryRepository.findById(blogDTO.getCategory()).orElseThrow(()->new ResourceNotFoundException("Blog category not found not found"));
            blog.setCategory(category);
        }
        return blogRepository.save(blog);
    }

    public Page<Blog> getAll(String id,PageRequest pageRequest){
        return blogRepository.findByStatus(StatusEnum.PUBLISHED, pageRequest);
    }

    public Page<Blog> getAllPrivate(String id,PageRequest pageRequest){
        return blogRepository.findAll( pageRequest);
    }

    public Blog getInfo(String id){
        return blogRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Blog not found."));
    }

    public Blog update(String id, Blog blog){
        return blogRepository.findById(id).map(item->{
            item.setTitle(blog.getTitle());
            item.setSlug(blog.getSlug());
            item.setContent(blog.getContent());
            item.setTags(blog.getTags());
            item.setCategory(blog.getCategory());
            item.setUpdatedAt(LocalDateTime.now());

            return blogRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Blog not found."));
    }
    public String delete(String id){
        Blog blog = blogRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Blog not found."));

        blogRepository.deleteById(id);
        return "Blog deleted successfully.";
    }

   public String publish(String id,StatusEnum status){
        Blog blog = blogRepository.findById(id).map(item->{
            item.setStatus(status);

            return blogRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Blog not found."));

        return "Blog "+blog.getStatus().toString().toLowerCase()+" successfully.";
   }
}
