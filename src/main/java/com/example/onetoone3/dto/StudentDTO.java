package com.example.onetoone3.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String firstName;
    private String lastName;
    private String dataBirthday;
    private Integer group_id;
}
