package com.poziomkowyspacerniak.poziomki.service;

import com.poziomkowyspacerniak.poziomki.model.Volunteer;
import com.poziomkowyspacerniak.poziomki.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional  // Wszystkie metody w klasie będą wykonywane w ramach jednej transakcji
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;


   @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id).orElse(null);
    }

    public List<Volunteer> getVolunteerByFirstName(String firstName) {
        return volunteerRepository.findAllByFirstName(firstName);
    }

    public List<Volunteer> getVolunteerByLastName(String lastName) {
        return volunteerRepository.findAllByLastName(lastName);
    }

    public List<Volunteer> getVolunteerByColorCategory(Volunteer.ColorCategory colorCategory) {
        return volunteerRepository.findAllByColorCategory(colorCategory);
    }

    public Volunteer addVolunteer(Volunteer volunteer) {
        String hashedPassword = passwordEncoder.encode(volunteer.getPassword());
        volunteer.setPassword(hashedPassword);
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteer(Volunteer volunteer) {
        Volunteer existingVolunteer = getVolunteerById(volunteer.getVolunteerId());
        if (existingVolunteer == null) {
            return null;
        }
        if (!existingVolunteer.getPassword().equals(volunteer.getPassword())) {
            String hashedPassword = passwordEncoder.encode(volunteer.getPassword());
            volunteer.setPassword(hashedPassword);
        }
        return volunteerRepository.save(volunteer);
    }

    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
}
