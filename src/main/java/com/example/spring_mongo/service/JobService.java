package com.example.spring_mongo.service;

import com.example.spring_mongo.dto.JobDTO;
import com.example.spring_mongo.exceptions.ResourceAlreadyExistException;
import com.example.spring_mongo.exceptions.ResourceNotFoundException;
import com.example.spring_mongo.model.Job;
import com.example.spring_mongo.model.JobCategory;
import com.example.spring_mongo.repository.JobCategoryRepository;
import com.example.spring_mongo.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final JobCategoryRepository jobCategoryRepository;

    public Job create(JobDTO jobDTO) {
        Job existingJob = jobRepository.findBySlug(jobDTO.getSlug());
        if(existingJob != null){
            throw new ResourceAlreadyExistException("Slug must be unique");
        }
        Job job = new Job();
        BeanUtils.copyProperties(jobDTO,job);
        job.setCreatedAt(LocalDateTime.now());

        if(jobDTO.getCategory() != null){
            JobCategory jobCategory = jobCategoryRepository.findById(jobDTO.getCategory()).orElseThrow(()-> new ResourceNotFoundException("Job Category not found"));
            job.setCategory(jobCategory);
        }
        return jobRepository.save(job);
    }

    public Page<Job> getAll (String id, PageRequest pageRequest, boolean isPrivate){
        if (isPrivate){
            return jobRepository.findAll(pageRequest);
        }
        else{
            return jobRepository.findByActive(true,pageRequest);
        }
    }

    public Job update(String id,Job job){

        return jobRepository.findById(id).map(item->{
            item.setCategory(job.getCategory());
            item.setSlug(job.getSlug());
            item.setDeadline(job.getDeadline());
            item.setDescription(job.getDescription());
            item.setExperience(job.getExperience());
            item.setNature(job.getNature());
            item.setNoOfVacancies(job.getNoOfVacancies());
            item.setExperience(job.getExperience());
            item.setTitle(job.getTitle());
            item.setType(job.getType());
            item.setUpdatedAt(LocalDateTime.now());

            return jobRepository.save(item);
        }).orElseThrow(()-> new ResourceNotFoundException("Job not found."));
    }

    public String activation(String id, boolean isActive){
        Job job = jobRepository.findById(id).map(item->{
            item.setActive(isActive);

            return jobRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Job not found."));

        String activation = isActive? "activated" : "deactivated";
        return "Job " + activation + " successfully";
    }

    public String delete(String id){
        Job job = jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Job not found."));
        jobRepository.deleteById(id);

        return "Job deleted successfully.";
    }
}
