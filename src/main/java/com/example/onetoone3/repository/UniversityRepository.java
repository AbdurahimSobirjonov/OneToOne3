package com.example.onetoone3.repository;

import com.example.onetoone3.entity.Univercity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<Univercity,Integer> {
 boolean existsByName(String name);
}
