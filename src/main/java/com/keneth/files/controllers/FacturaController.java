package com.keneth.files.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keneth.files.models.Factura;
import com.keneth.files.models.FacturaDTO;
import com.keneth.files.models.Person;
import com.keneth.files.models.Producto;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FacturaController {
    private String pathPeople = "./src/main/resources/persons.json";
    private String pathProducts = "./src/main/resources/productos.json";
    private String pathBills = "./src/main/resources/bills.json";
    private ObjectMapper mapper = new ObjectMapper();
    File peopleFile = new File(pathPeople);
    File productsFile = new File(pathProducts);
    File billsFile = new File(pathBills);
    List<Producto> productos;
    List<Person> people;
    List<Factura> bills;

    @GetMapping("/facturar")
    public String mostrarFacturaForm(Model model) {
        try {
            people = mapper.readValue(peopleFile, new TypeReference<List<Person>>() {
            });
            productos = mapper.readValue(productsFile, new TypeReference<List<Producto>>() {
            });
            model.addAttribute("personas", people);

            model.addAttribute("productos", productos);
            model.addAttribute("facturaDTO", new FacturaDTO());
        } catch (Exception e) {
        }
        return "facturaForm";
    }

    @PostMapping("/facturar")
    public String procesarFactura(@ModelAttribute FacturaDTO facturaDTO, Model model) {
        try {
            Person selectedPerson = people.stream()
                    .filter(p -> p.getCedula().equals(facturaDTO.getCedula()))
                    .findFirst()
                    .orElse(null);

            List<Producto> selectedProductos = facturaDTO.getProductosIds().stream()
                    .map(id -> productos.stream()
                            .filter(prod -> prod.getNombre().equals(id))
                            .findFirst()
                            .orElse(null))
                    .collect(Collectors.toList());
            Factura factura = new Factura();
            factura.setPersona(selectedPerson);
            factura.setProductos(selectedProductos);
            factura.setTotal(selectedProductos.stream().mapToInt(Producto::getPrecio).sum());
            bills = mapper.readValue(billsFile, new TypeReference<List<Factura>>() {
            });
            bills.add(factura);
            mapper.writeValue(billsFile, bills);
            model.addAttribute("facturas", bills);
        } catch (Exception e) {
        }
        return "redirect:/facturas";
    }

    @GetMapping("/facturas")
    public String geFacturas(Model model) {
        try {
            bills = mapper.readValue(billsFile, new TypeReference<List<Factura>>() {
            });
            model.addAttribute("facturas", bills);
        } catch (Exception e) {
        }
        return "FacturaDetalles";
    }

}
