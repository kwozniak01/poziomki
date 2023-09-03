package com.poziomkowyspacerniak.poziomki.controller;

import com.poziomkowyspacerniak.poziomki.model.Dog;
import com.poziomkowyspacerniak.poziomki.model.Walk;
import com.poziomkowyspacerniak.poziomki.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/walks")
public class WalkController {

    @Autowired
    private WalkService walkService;

    @GetMapping
    public String getAllWalks(Model model) {
        List<Walk> walks = walkService.getAllWalksBetweenDates(LocalDateTime.now().minusMonths(1), LocalDateTime.now());
        model.addAttribute("walks", walks);
        return "walk-list";
    }

    @GetMapping("/between-dates")
    public String getAllWalksBetweenDates(@RequestParam("start") LocalDateTime start,
                                          @RequestParam("end") LocalDateTime end, Model model) {
        List<Walk> walks = walkService.getAllWalksBetweenDates(start, end);
        model.addAttribute("walks", walks);
        return "walks-between-dates";
    }

    @GetMapping("/by-dog-name/{dogName}")
    public String getWalksByDogName(@PathVariable String dogName, Model model) {
        List<Walk> walks = walkService.getWalksByDogName(dogName);
        model.addAttribute("walks", walks);
        return "walks-by-dog-name";
    }

    @GetMapping("/by-volunteer-name")
    public String getWalksByVolunteerName(@RequestParam("firstName") String firstName,
                                          @RequestParam("lastName") String lastName, Model model) {
        List<Walk> walks = walkService.getWalksByVolunteerName(firstName, lastName);
        model.addAttribute("walks", walks);
        return "walks-by-volunteer-name";
    }

    @GetMapping("/by-dog-color-category/{colorCategory}")
    public String getWalksByDogColorCategory(@PathVariable Dog.ColorCategory colorCategory, Model model) {
        List<Walk> walks = walkService.getWalksByDogColorCategory(colorCategory);
        model.addAttribute("walks", walks);
        return "walks-by-dog-color-category";
    }

    @GetMapping("/add")
    public String showAddWalkForm() {
        return "add-walk";
    }

    @PostMapping
    public String addWalk(@ModelAttribute Walk walk) {
        walkService.assignDogToWalk(walk.getWalkDate(), walk.getDog().getColorCategory()); // Załóżmy, że masz taką metodę
        return "redirect:/walks";
    }
}
