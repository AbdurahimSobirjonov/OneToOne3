package com.example.onetoone3.controller;

import com.example.onetoone3.dto.UniversityAddressDTO;
import com.example.onetoone3.entity.Address;
import com.example.onetoone3.entity.Univercity;
import com.example.onetoone3.repository.AddressRepository;
import com.example.onetoone3.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UnivercityController {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<Univercity> getUniversity() {
        return universityRepository.findAll();
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityAddressDTO universityAddressDTO) {
        boolean byName = universityRepository.existsByName(universityAddressDTO.getName());
        if (!byName) {
            Univercity univercity = new Univercity();
            univercity.setName(universityAddressDTO.getName());
            Address address = new Address();
            address.setCity(universityAddressDTO.getCity());
            address.setDistrict(universityAddressDTO.getDistrict());
            address.setStreet(universityAddressDTO.getStreet());
            univercity.setAddress(address);
            addressRepository.save(address);
            univercity.setAddress(address);
            universityRepository.save(univercity);
            return "Qushildi";
        }
        return "foydalanuvchi bor";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String updateUniversity(@PathVariable Integer id, @RequestBody UniversityAddressDTO universityAddressDTO) {
        Address address = new Address();
        Optional<Univercity> univercityOptional = universityRepository.findById(id);
        if (univercityOptional.isPresent()) {
            Univercity univercity = univercityOptional.get();
            univercity.setName(universityAddressDTO.getName());
            address.setCity(universityAddressDTO.getCity());
            address.setStreet(universityAddressDTO.getStreet());
            address.setDistrict(universityAddressDTO.getDistrict());
            addressRepository.save(address);
            univercity.setAddress(address);
            universityRepository.save(univercity);
        }
        return "Update ok";
    }
}
