package com.example.spring_mongo.service;

import com.example.spring_mongo.dto.JobCategoryDTO;
import com.example.spring_mongo.exceptions.ResourceAlreadyExistException;
import com.example.spring_mongo.exceptions.ResourceNotFoundException;
import com.example.spring_mongo.model.JobCategory;
import com.example.spring_mongo.repository.JobCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class JobCategoryService {
    private final JobCategoryRepository jobCategoryRepository;

    public JobCategory create(JobCategoryDTO jobCategoryDTO){
        JobCategory existingCategory = jobCategoryRepository.findBySlug(jobCategoryDTO.getSlug());
        if(existingCategory != null){
            throw new ResourceAlreadyExistException("Slug must be unique.");
        }
        JobCategory category = new JobCategory();
        BeanUtils.copyProperties(jobCategoryDTO,category);
        category.setCreatedAt(LocalDateTime.now());

        return jobCategoryRepository.save(category);
    }

    public Page<JobCategory> getAll (String id,PageRequest pageRequest){
        return jobCategoryRepository.findAll(pageRequest);
    }

    public JobCategory update (String id,JobCategory category){

        return jobCategoryRepository.findById(id).map(item->{
            item.setName(category.getName());
            item.setSlug(category.getSlug());
            item.setUpdatedAt(LocalDateTime.now());

            return jobCategoryRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Job category not found."));
    }

    public String delete (String id){
        JobCategory category = jobCategoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Job category not found."));
        jobCategoryRepository.deleteById(id);

        return "Job category deleted successfully.";
    }
}
