package com.example.onetoone3.controller;

import com.example.onetoone3.dto.GroupDTO;
import com.example.onetoone3.entity.Faculty;
import com.example.onetoone3.entity.Group;
import com.example.onetoone3.repository.FacultyRepository;
import com.example.onetoone3.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @RequestMapping(value = "/group",method = RequestMethod.POST)
    public String addGroup(@RequestBody GroupDTO groupDTO){
        Group group = new Group();
        boolean byFaculty_id = groupRepository.existsByFaculty_Id(groupDTO.getFaculty_id());
        if(!byFaculty_id){
            group.setName(groupDTO.getName());
            Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDTO.getFaculty_id());
            group.setFaculty(optionalFaculty.get());
            groupRepository.save(group);
            return "Ok";
        }
        return "NG";
    }
    @RequestMapping(value = "/group",method = RequestMethod.GET)
    public List<Group> getGroup(){
        return groupRepository.findAll();
    }
    @RequestMapping(value = "/group/{id}",method = RequestMethod.DELETE)
    public void deleteGroup(@PathVariable Integer id){
        facultyRepository.deleteById(id);
    }
    @RequestMapping(value = "/group",method = RequestMethod.PUT)
    public void updateGroup(@PathVariable Integer id,@RequestBody GroupDTO groupDTO){
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()){
            Group group=optionalGroup.get();
            group.setName(groupDTO.getName());
            groupRepository.save(group);
        }
    }


}
