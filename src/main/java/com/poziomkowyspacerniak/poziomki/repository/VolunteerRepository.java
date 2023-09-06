package com.poziomkowyspacerniak.poziomki.repository;

import com.poziomkowyspacerniak.poziomki.model.Dog;
import com.poziomkowyspacerniak.poziomki.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findAll();
    List<Volunteer> findAllByColorCategory(Volunteer.ColorCategory colorCategory);
    List<Volunteer> findAllByFirstName (String firstName);
    List<Volunteer> findAllByLastName (String lastName);
    Optional<Volunteer> findById (Long id);

    List<Volunteer> findAllByOrderByLastNameAsc();

    boolean existsByUsername(String username); // Sprawdzenie czy istnieje wolontariusz o podanym username
}
