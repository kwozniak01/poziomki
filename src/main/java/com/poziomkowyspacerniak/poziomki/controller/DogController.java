package com.poziomkowyspacerniak.poziomki.controller;

import com.poziomkowyspacerniak.poziomki.model.Dog;
import com.poziomkowyspacerniak.poziomki.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dogs")
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping
    public String getAllDogs(Model model) {
        List<Dog> dogs = dogService.getAllSortedByName(); // alfabetycznie
        model.addAttribute("dogs", dogs);
        return "dog-list";
    }
    @GetMapping("/add")
    public String showAddDogForm() {
        return "add-dog";
    }

    @GetMapping("/{id}")
    public String getDogById(@PathVariable Long id, Model model) {
        Dog dog = dogService.getDogById(id);
        if (dog != null) {
            model.addAttribute("dog", dog);
            return "dog-details";
        }
        return "dog-not-found";
    }

    @GetMapping("/color/{color}")
    public String getDogsByColor(@PathVariable("color") Dog.ColorCategory colorCategory, Model model) {
        List<Dog> dogs = dogService.getDogsByColorCategory(colorCategory);
        model.addAttribute("dogs", dogs);
        return "dogs-by-color";
    }

    @GetMapping("/age/{age}")
    public String getDogsByAge(@PathVariable Integer age, Model model) {
        List<Dog> dogs = dogService.getDogsByAge(age);
        model.addAttribute("dogs", dogs);
        return "dogs-by-age";
    }

    @GetMapping("/name/{name}")
    public String getDogsByName(@PathVariable String name, Model model) {
        List<Dog> dogs = dogService.getDogsByName(name);
        model.addAttribute("dogs", dogs);
        return "dogs-by-name";
    }
    @GetMapping("/update")
    public String selectDogForUpdate(Model model) {
        List<Dog> dogs = dogService.getAll();
        model.addAttribute("dogs", dogs);
        return "select-dog-for-update";
    }

    @PostMapping
    public String addDog(@ModelAttribute Dog dog) {
        dogService.addDog(dog);
        return "redirect:/dogs";
    }

    @PutMapping("/{id}")
    public String updateDog(@PathVariable Long id, @ModelAttribute Dog dog) {
        Dog existingDog = dogService.getDogById(id);
        if (existingDog != null) {
            dog.setDogId(id);
            dogService.updateDog(dog);
            return "redirect:/dogs/" + id;
        }
        return "dog-not-found";
    }


    @DeleteMapping("/{id}")
    public String deleteDog(@PathVariable Long id) {
        Dog existingDog = dogService.getDogById(id);
        if (existingDog != null) {
            dogService.deleteDog(id);
            return "redirect:/dogs";
        }
        return "dog-not-found";
    }
}
