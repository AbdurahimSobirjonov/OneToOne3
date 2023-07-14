package com.example.onetoone3.repository;

import com.example.onetoone3.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    boolean existsByUnivercity_Id(Integer univercity_id);
}
