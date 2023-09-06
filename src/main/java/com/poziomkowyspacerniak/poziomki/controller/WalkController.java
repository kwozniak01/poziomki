package com.poziomkowyspacerniak.poziomki.controller;

import com.poziomkowyspacerniak.poziomki.model.Dog;
import com.poziomkowyspacerniak.poziomki.model.Volunteer;
import com.poziomkowyspacerniak.poziomki.model.Walk;
import com.poziomkowyspacerniak.poziomki.service.DogService;
import com.poziomkowyspacerniak.poziomki.service.VolunteerService;
import com.poziomkowyspacerniak.poziomki.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;




@Controller
@RequestMapping("/walks")
public class WalkController {

    @Autowired
    private WalkService walkService;
    @Autowired
    private DogService dogService;

    @Autowired
    private VolunteerService volunteerService;


    @GetMapping
    public String getAllWalks(Model model) {
        List<Walk> walks = walkService.getAllWalksSortedByWalkDate();
        model.addAttribute("walks", walks);
        return "walk-list";
    }

    @GetMapping("/add")
    public String showAddWalkForm(Model model) {
        List<Dog> allDogs = dogService.getAllDogs();
        List<Volunteer> allVolunteers = volunteerService.getAllVolunteers();

        model.addAttribute("allDogs", allDogs);
        model.addAttribute("allVolunteers", allVolunteers);

        return "add-walk";
    }

    @GetMapping("/{id}")
    public String getWalkById(@PathVariable Long id, Model model) {
        Walk walk = walkService.getWalkById(id);
        if (walk != null) {
            model.addAttribute("walk", walk);
            return "walk-details";
        }
        return "walk-not-found";
    }

    @GetMapping("/update")
    public String selectWalkForUpdate(Model model) {
        List<Walk> walks = walkService.getAllWalks();
        model.addAttribute("walks", walks);
        return "select-walk-for-update";
    }

    @PostMapping
    public String addWalk(@ModelAttribute Walk walk,
                          @RequestParam("volunteerId") Long volunteerId,
                          @RequestParam("dogId") Long dogId,
                          @RequestParam("stringWalkDate") String stringWalkDate,
                          @RequestParam("stringWalkTime") String stringWalkTime,
                          Model model) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalDate walkDate = LocalDate.parse(stringWalkDate, dateFormatter);
            LocalTime walkTime = LocalTime.parse(stringWalkTime, timeFormatter);
            LocalDateTime walkDateTime = LocalDateTime.of(walkDate, walkTime);
            walk.setWalkDate(walkDateTime);
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "Niepoprawny format daty lub czasu");
            return "add-walk";
        }

        // Wyszukiwanie obiektu Volunteer i Dog na podstawie ID
        Volunteer volunteer = volunteerService.getVolunteerById(volunteerId);
        Dog dog = dogService.getDogById(dogId);

        // Jeżeli któryś z obiektów jest null, wraca do formularza z komunikatem błędu
        if (volunteer == null || dog == null) {
            model.addAttribute("error", "Nie można znaleźć wolontariusza lub psa o podanych identyfikatorach");
            return "add-walk";
        }

        walk.setVolunteer(volunteer);
        walk.setDog(dog);

        walkService.addWalk(walk);

        return "redirect:/walks";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Walk walk = walkService.getWalkById(id);
        if (walk != null) {
            model.addAttribute("walk", walk);
            return "update-walk";
        }
        return "walk-not-found";
    }

    @PutMapping("update/{id}")
    public String updateWalk(@PathVariable Long id, @ModelAttribute Walk walk) {
        Walk existingWalk = walkService.getWalkById(id);
        if (existingWalk != null) {
            walk.setWalkId(id);
            walkService.updateWalk(walk);
            return "redirect:/walks";
        }
        return "walk-not-found";
    }


    @DeleteMapping("delete/{id}")
    public String deleteWalk(@PathVariable Long id) {
        Walk existingWalk = walkService.getWalkById(id);
        if (existingWalk != null) {
            walkService.deleteWalk(id);
            return "redirect:/walks";
        }
        return "walk-not-found";
    }
}
