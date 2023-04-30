package com.example.spring_mongo.service;

import com.example.spring_mongo.dto.LeaveDTO;
import com.example.spring_mongo.enums.LeaveStatus;
import com.example.spring_mongo.exceptions.ResourceNotFoundException;
import com.example.spring_mongo.model.Leave;
import com.example.spring_mongo.model.User;
import com.example.spring_mongo.repository.LeaveRepository;
import com.example.spring_mongo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final UserRepository userRepository;

    public Leave create(LeaveDTO leaveDTO){
        Leave leave = new Leave();
        BeanUtils.copyProperties(leaveDTO,leave);

        User user = userRepository.findById("643bc08aa7c7e04da347dfc0").orElseThrow(()->new ResourceNotFoundException("User not Found"));
        leave.setUser(user);
        return leaveRepository.save(leave);
    }

    public Page<Leave> getList (String id,PageRequest pageRequest){
        return leaveRepository.findAll(pageRequest);
    }

    public String action(String id, LeaveStatus status){
        Leave existingLeave = leaveRepository.findById(id).map(item->{
            item.setStatus(status);
             return leaveRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Leave application not found"));

        return "Leave application " + status.toString().toLowerCase() + " successfully.";

    }

    public Leave update (String id, Leave leave){

        return leaveRepository.findById(id).map(item->{
            item.setNatureOfLeave(leave.getNatureOfLeave());
            item.setStartDate(leave.getStartDate());
            item.setEndDate(leave.getEndDate());
            item.setReason(leave.getReason());
            item.setDateOfJoining(leave.getDateOfJoining());

            return leaveRepository.save(item);
        }).orElseThrow(()-> new ResourceNotFoundException("Leave application not found."));
    }

    public String delete(String id) {
        Leave leave = leaveRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Leave application not found."));
        leaveRepository.deleteById(id);

        return "Leave application deleted successfully.";
    }
}
