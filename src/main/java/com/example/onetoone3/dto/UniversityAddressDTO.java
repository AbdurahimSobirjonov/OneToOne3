package com.example.onetoone3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivilegedAction;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniversityAddressDTO {
    private String name;
    private String city;
    private String district;
    private String street;
}
