package com.poziomkowyspacerniak.poziomki.service;

import com.poziomkowyspacerniak.poziomki.model.Walk;
import com.poziomkowyspacerniak.poziomki.repository.WalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalkService {

    @Autowired
    private WalkRepository walkRepository;

    public List<Walk> getAllWalks() {
        return walkRepository.findAll();
    }


    public Walk getWalkById(Long id) {
        Optional<Walk> optionalWalk = walkRepository.findById(id);
        return optionalWalk.orElse(null);
    }

    public void addWalk(Walk walk) {
        walkRepository.save(walk);
    }

    public void updateWalk(Walk walk) {
        walkRepository.save(walk);
    }

    public void deleteWalk(Long id) {
        System.out.println("Chcesz usunąć spacer: " + id);
        walkRepository.deleteById(id);
    }
}
