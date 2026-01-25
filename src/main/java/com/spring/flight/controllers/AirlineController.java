package com.spring.flight.controllers;

import com.spring.flight.exceptions.AircraftNotFoundException;
import com.spring.flight.exceptions.NoAircraftFoundException;
import com.spring.flight.models.Airline;
import com.spring.flight.services.AirlineService;
import com.spring.flight.services.FlightService;
import com.spring.flight.validators.AirlineDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/airline")
public class AirlineController {


    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirlineDataValidator airlineDataValidator;


    @Autowired
    private FlightService flightService;


    @GetMapping("/")
    public String getAirlineHome()
    {
        return "redirect:/airline/list";
    }

    @GetMapping("/new")
    public String createAirline(Model model)
    {
        model.addAttribute("airline", new Airline());
        return "airline-form";
    }

    @GetMapping("/list")
    public String fetchAirline(Model model) {
        List<Airline> li=airlineService.findAllAirline();
        model.addAttribute("airlines", li);
        model.addAttribute("success", li.size()+" Aircraft found");
        return "airline";
    }

    @PostMapping("/save")
    public String saveAirline(@ModelAttribute Airline a, Model model) {
        if(a.getId()==null)       // agar null hoga to add kar denge-->(By AKM)
        {
            List<String> errors=airlineDataValidator.validate(a);
            if(!errors.isEmpty()) {
                model.addAttribute("error", errors);
            }
            else {
                try {
                    airlineService.saveAirline(a);
                    model.addAttribute("success","Airline created successfully");
                }
                catch (Exception e){
                    model.addAttribute("error","Error during Airline data creation");
                }
            }
        }
        else {
            try {
                airlineService.saveAirline(a);
                model.addAttribute("success", "Airline updated successfully");
            } catch (Exception e) {
                model.addAttribute("error", "Error during Airline update");
            }
        }
        model.addAttribute("airlines",airlineService.findAllAirline());
        return "airline";

    }

    @GetMapping("/edit/{id}")
    public String getAirlineById(@PathVariable Integer id, Model model) {
        try {
            Airline airline=airlineService.getAirlineById(id);

            model.addAttribute("airline",airline);
            model.addAttribute("success", "Aircraft found");

            return "airline-form";
        }
        catch (AircraftNotFoundException | NoAircraftFoundException n)
        {
            model.addAttribute("error","No airline Found ");
            model.addAttribute("airlines",airlineService.findAllAirline());
            return "airline";
        }
    }

    @GetMapping("/delete/{id}")
    public String removeAirlineById(@PathVariable Integer id, RedirectAttributes model) {
        try {
            airlineService.deleteAirlineById(id);
            model.addFlashAttribute("success", "Airline deleted successfully");
        }
        catch (NoAircraftFoundException a)
        {
            model.addFlashAttribute("error", a.getMessage());
        }
        return "redirect:/airline/";
    }
}
