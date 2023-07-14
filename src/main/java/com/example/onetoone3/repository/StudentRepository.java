package com.example.onetoone3.repository;

import com.example.onetoone3.entity.Group;
import com.example.onetoone3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    boolean existsByGroup_Id(Integer group_id);
}
