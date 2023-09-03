package com.poziomkowyspacerniak.poziomki.repository;

import com.poziomkowyspacerniak.poziomki.model.Dog;
import com.poziomkowyspacerniak.poziomki.model.Walk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WalkRepository extends JpaRepository<Walk, Long> {
    List<Walk> findAllByWalkDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT w FROM Walk w JOIN w.dog d JOIN w.volunteer v WHERE d.name= :dogName")
    List<Walk> findWalksByDogName(@Param("dogName") String dogName);

    @Query("SELECT w FROM Walk w JOIN w.dog d JOIN w.volunteer v WHERE v.firstName = :firstName AND v.lastName = :lastName")
    List<Walk> findWalksByVolunteerName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT w FROM Walk w JOIN w.dog d WHERE d.colorCategory = :colorCategory")
    List<Walk> findWalksByDogColorCategory(@Param("colorCategory") Dog.ColorCategory colorCategory);

}
