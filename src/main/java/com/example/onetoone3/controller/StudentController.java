package com.example.onetoone3.controller;

import com.example.onetoone3.dto.GroupDTO;
import com.example.onetoone3.dto.StudentDTO;
import com.example.onetoone3.entity.Group;
import com.example.onetoone3.entity.Student;
import com.example.onetoone3.repository.GroupRepository;
import com.example.onetoone3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        boolean byGroup_id = studentRepository.existsByGroup_Id(studentDTO.getGroup_id());
        if (!byGroup_id) {
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setDataBirthday(studentDTO.getDataBirthday());
            Optional<Group> optionalGroup = groupRepository.findById(studentDTO.getGroup_id());
            student.setGroup(optionalGroup.get());
            studentRepository.save(student);
            return "Ok";
        }
        return "NG";
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
    }
    @RequestMapping(value = "/student/{id}",method = RequestMethod.PUT)
    public void updateStudent(@PathVariable Integer id, @RequestBody StudentDTO studentDTO){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            Student student =optionalStudent.get();
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setDataBirthday(studentDTO.getDataBirthday());
            studentRepository.save(student);
        }
    }

}


