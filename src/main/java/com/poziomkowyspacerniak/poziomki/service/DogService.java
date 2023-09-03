package com.poziomkowyspacerniak.poziomki.service;

import com.poziomkowyspacerniak.poziomki.model.Dog;
import com.poziomkowyspacerniak.poziomki.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;

    public List<Dog> getAll() {
        return dogRepository.findAll();
    }
    public List<Dog> getAllSortedByName() {
        return dogRepository.findAllByOrderByNameAsc();
    }

    public Dog getDogById(Long id) {
        return dogRepository.findById(id).orElse(null);
    }

    public List<Dog> getDogsByColorCategory(Dog.ColorCategory colorCategory) {
        return dogRepository.findAllByColorCategory(colorCategory);
    }

    public List<Dog> getDogsByAge(Integer age) {
        return dogRepository.findAllByAge(age);
    }

    public List<Dog> getDogsByName(String name) {
        return dogRepository.findAllByName(name);
    }

    public Dog addDog(Dog dog) {
        return dogRepository.save(dog);
    }

    public Dog updateDog(Dog dog) {
        return dogRepository.save(dog);
    }

    public void deleteDog(Long id) {
       dogRepository.deleteById(id);
    }

}
