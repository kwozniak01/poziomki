package com.poziomkowyspacerniak.poziomki.repository;

import com.poziomkowyspacerniak.poziomki.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    List<Dog> findAllByColorCategory(Dog.ColorCategory colorCategory);
    List<Dog> findAllByAge(Integer age);
    List<Dog>findAllByName(String name);
    List<Dog> findAllByOrderByNameAsc();

    List<Dog>findAll();

}
