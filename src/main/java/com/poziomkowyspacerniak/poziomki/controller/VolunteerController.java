package com.poziomkowyspacerniak.poziomki.controller;

import com.poziomkowyspacerniak.poziomki.model.Volunteer;
import com.poziomkowyspacerniak.poziomki.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping
    public String getAllVolunteers(Model model) {
        List<Volunteer> volunteers = volunteerService.getAllSortedByLastName();
        model.addAttribute("volunteers", volunteers);
        return "volunteer-list";
    }

    @GetMapping("/add")
    public String showAddVolunteerForm() {
        return "add-volunteer";
    }

    @GetMapping("/{id}")
    public String getVolunteerById(@PathVariable Long id, Model model) {
        Volunteer volunteer = volunteerService.getVolunteerById(id);
        if (volunteer != null) {
            model.addAttribute("volunteer", volunteer);
            return "volunteer-details";
        }
        return "volunteer-not-found";
    }

    @GetMapping("/color-category/{colorCategory}")
    public String getVolunteersByColorCategory(@PathVariable Volunteer.ColorCategory colorCategory, Model model) {
        List<Volunteer> volunteers = volunteerService.getVolunteerByColorCategory(colorCategory);
        model.addAttribute("volunteers", volunteers);
        return "volunteers-by-color";
    }

    @GetMapping("/update")
    public String selectVolunteerForUpdate(Model model) {
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        model.addAttribute("volunteers", volunteers);
        return "select-volunteer-for-update";
    }

    @PostMapping
    public String addVolunteer(@ModelAttribute Volunteer volunteer) {
        volunteerService.addVolunteer(volunteer);
        return "redirect:/volunteers";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Volunteer volunteer = volunteerService.getVolunteerById(id);
        if (volunteer != null) {
            model.addAttribute("volunteer", volunteer);
            return "update-volunteer";
        }
        return "volunteer-not-found";
    }

    @PutMapping("/{id}")
    public String updateVolunteer(@PathVariable Long id, @ModelAttribute Volunteer volunteer) {
        Volunteer existingVolunteer = volunteerService.getVolunteerById(id);
        if (existingVolunteer != null) {
            volunteer.setVolunteerId(id);
            volunteerService.updateVolunteer(volunteer);
            return "redirect:/volunteers/";
        }
        return "volunteer-not-found";
    }

    @DeleteMapping("/{id}")
    public String deleteVolunteer(@PathVariable Long id) {
        Volunteer existingVolunteer = volunteerService.getVolunteerById(id);
        if (existingVolunteer != null) {
            volunteerService.deleteVolunteer(id);
            return "redirect:/volunteers";
        }
        return "volunteer-not-found";
    }
}
