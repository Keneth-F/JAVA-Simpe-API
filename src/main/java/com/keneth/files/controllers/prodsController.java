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
import com.keneth.files.models.Producto;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class prodsController {
    private String path = "./src/main/resources/productos.json";
    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/prods")
    public String get(Model model) {
        model.addAttribute("prod", new Producto());
        return "prods";
    }

    @PostMapping("/guardarProd")
    public String postMethodName(@ModelAttribute Producto person) {
        try {
            List<Producto> persons = getProds();
            persons.add(person);
            mapper.writeValue(new File(path), persons);
        } catch (Exception e) {
        }
        return "redirect:/prodsList";
    }

    private List<Producto> getProds() {
        try {
            File file = new File(path);
            if (!file.exists()) {
                return new ArrayList<Producto>();
            }
            return mapper.readValue(file, new TypeReference<List<Producto>>() {
            });
        } catch (Exception e) {
            // TODO: handle exception
            return new ArrayList<Producto>();

        }
    }

    @GetMapping("/prodsList")
    public String getList(Model model) {
        List<Producto> persons = getProds();
        model.addAttribute("prods", persons);
        return "prodsList";
    }

}
