package com.example.spring_mongo.service;

import com.example.spring_mongo.dto.RoleDTO;
import com.example.spring_mongo.exceptions.ResourceAlreadyExistException;
import com.example.spring_mongo.exceptions.ResourceNotFoundException;
import com.example.spring_mongo.model.Role;
import com.example.spring_mongo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role create(RoleDTO roleDTO){
        Role existingRole = roleRepository.findByAlias(roleDTO.getAlias());
        if(existingRole != null){
            throw new ResourceAlreadyExistException("Role '"+ roleDTO.getAlias() +"' already exists");
        }

        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        role.setCreatedAt(LocalDateTime.now());
        return roleRepository.save(role);
    }

    public Page<Role> getAll (PageRequest pageRequest){
        return roleRepository.findAll(pageRequest);
    }

    public Role getOne(String id){
        Role role = roleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Role not found"));
        return role;
    }

    public Role update(String id , Role role){
        Role oldRole = roleRepository.findById(id).map(item->{
            item.setAlias(role.getAlias());
            item.setPermissionString(role.getPermissionString());
            item.setUpdatedAt(LocalDateTime.now());

            return roleRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Role not found"));

        return oldRole;
    }

    public String delete (String id){
        Role role = roleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Role not found"));
        roleRepository.deleteById(id);

        return "Role deleted successfully.";
    }
}
