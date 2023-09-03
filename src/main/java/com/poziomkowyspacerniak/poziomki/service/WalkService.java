package com.poziomkowyspacerniak.poziomki.service;

import com.poziomkowyspacerniak.poziomki.model.Dog;
import com.poziomkowyspacerniak.poziomki.model.Walk;
import com.poziomkowyspacerniak.poziomki.repository.WalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalkService {
    @Autowired
    private WalkRepository walkRepository;

    @Autowired
    private DogService dogService;

    public List<Walk> getAllWalksBetweenDates(LocalDateTime start, LocalDateTime end) {
        return walkRepository.findAllByWalkDateBetween(start, end);
    }
    public List<Walk> getWalksByDogName(String dogName) {
        return walkRepository.findWalksByDogName(dogName);
    }
    public List<Walk> getWalksByVolunteerName (String firstName, String lastName) {
        return walkRepository.findWalksByVolunteerName(firstName, lastName);
    }
    public List<Walk> getWalksByDogColorCategory(Dog.ColorCategory colorCategory){
        return walkRepository.findWalksByDogColorCategory(colorCategory);
    }
    public Walk assignDogToWalk(LocalDateTime walkDateTime, Dog.ColorCategory colorCategory) {
        // Pobiera listę dostępnych psów o danym kolorze
        List<Dog> availableDogs = dogService.getDogsByColorCategory(colorCategory);

        if (availableDogs.isEmpty()) {
            return null;
        }

        // Filtruje dostępne psy, aby zobaczyć, które z nich są już "zajęte" na spacer tego dnia
        LocalDateTime startOfDay = walkDateTime.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = walkDateTime.toLocalDate().atTime(23, 59, 59);
        List<Walk> walksOnSameDay = walkRepository.findAllByWalkDateBetween(startOfDay, endOfDay);

        for (Walk walk : walksOnSameDay) {
            availableDogs.remove(walk.getDog());
        }

        if (availableDogs.isEmpty()) {
            return null;
        }

        // Wybiera pierwszego dostępnego psa (w tym przypadku)
        Dog selectedDog = availableDogs.get(0);

        // Tworzy nowego spaceru
        Walk newWalk = new Walk();
        newWalk.setDog(selectedDog);
        newWalk.setWalkDate(walkDateTime);

        // Zapisuje spacer w bazie danych
        walkRepository.save(newWalk);

        return newWalk;
    }
}
