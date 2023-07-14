package com.example.onetoone3.repository;

import com.example.onetoone3.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    boolean existsByFaculty_Id(Integer faculty_id);
}
