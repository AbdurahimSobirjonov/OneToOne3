package com.example.onetoone3.controller;

import com.example.onetoone3.dto.FacultyDTO;
import com.example.onetoone3.entity.Faculty;
import com.example.onetoone3.entity.Univercity;
import com.example.onetoone3.repository.FacultyRepository;
import com.example.onetoone3.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    @RequestMapping(value = "/faculty", method = RequestMethod.POST)
    public String addFaculty(@RequestBody  FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        boolean byUnivercity_id=facultyRepository.existsByUnivercity_Id(facultyDTO.getUnivercity_id());
        if (!byUnivercity_id) {
            Optional<Univercity> optionalUnivercity = universityRepository.findById(facultyDTO.getUnivercity_id());
            Univercity univercity = optionalUnivercity.get();
            faculty.setName(facultyDTO.getName());
            faculty.setUnivercity(univercity);
            facultyRepository.save(faculty);
            return "Qo`shildi";
        }
            return "Bunday fakultet mavjud";
    }
    @RequestMapping(value = "/faculty",method = RequestMethod.GET)
    public List<Faculty> getFaculty(){
        return facultyRepository.findAll();
    }
    @RequestMapping(value = "/faculty/{id}",method = RequestMethod.DELETE)
    public String deleteFaculty(@PathVariable Integer id){
        facultyRepository.deleteById(id);
        return "O`chirildi";
    }
    @RequestMapping(value = "/faculty/{id}",method = RequestMethod.PUT)
    public void updateFaculty (@PathVariable Integer id,@RequestBody FacultyDTO facultyDTO){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if(optionalFaculty.isPresent()){
            Faculty faculty= optionalFaculty.get();
            faculty.setName(facultyDTO.getName());
            facultyRepository.save(faculty);
        }
    }
}
