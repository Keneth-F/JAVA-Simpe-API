package com.keneth.files.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keneth.files.models.Person;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class personaController {
    private String path = "./src/main/resources/persons.json";
    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/persons")
    public String get(Model model) {
        model.addAttribute("persona", new Person());
        return "persons";
    }

    @PostMapping("/guardarPersona")
    public String postMethodName(@ModelAttribute Person person) {
        try {
            List<Person> persons = getPersons();
            persons.add(person);
            mapper.writeValue(new File(path), persons);
        } catch (Exception e) {
        }
        return "redirect:/personsList";
    }

    private List<Person> getPersons() {
        try {
            File file = new File(path);
            if (!file.exists()) {
                return new ArrayList<Person>();
            }
            return mapper.readValue(file, new TypeReference<List<Person>>() {
            });
        } catch (Exception e) {
            // TODO: handle exception
            return new ArrayList<Person>();

        }
    }

    @GetMapping("/personsList")
    public String getList(Model model) {
        List<Person> persons = getPersons();
        model.addAttribute("personas", persons);
        return "personsList";
    }

}
