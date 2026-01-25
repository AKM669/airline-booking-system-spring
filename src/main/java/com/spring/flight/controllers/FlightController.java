package com.spring.flight.controllers;

import com.spring.flight.exceptions.AircraftNotFoundException;
import com.spring.flight.exceptions.FlightNotFoundException;
import com.spring.flight.exceptions.NoAircraftFoundException;
import com.spring.flight.models.Airline;
import com.spring.flight.models.Flight;
import com.spring.flight.repo.AirlineRepository;
import com.spring.flight.repo.FlightRepository;
import com.spring.flight.services.AirlineService;
import com.spring.flight.services.FlightService;
import com.spring.flight.validators.FlightDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/flight")
public class FlightController {


    @Autowired
    private AirlineService airlineService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightDataValidator flightDataValidator;

    @GetMapping("/")
    public String displayFlightHome()
    {
        return "redirect:/flight/list";
    }

    @GetMapping("/list")
    public String fetchFlight(Model model)
    {
        List<Flight> li=flightService.findAllFlight();
        model.addAttribute("flights",li);
        model.addAttribute("success",li.size()+" Flight found");
        return "flight";
    }

    @GetMapping("/new")
    public String createNewFlight(Model model) throws NoAircraftFoundException {
        model.addAttribute("flight",new Flight());
        model.addAttribute("aircraft",airlineService.findAllAirline());
        return "flight-form";
    }

    @PostMapping("/save")
    public String saveFlight(@ModelAttribute Flight f, Model model) {
        if(f.getId()==null)       // agar null hoga to add kar denge-->(By AKM)
        {
            List<String> errors=flightDataValidator.validate(f);
            if(!errors.isEmpty()) {
                model.addAttribute("error", errors);
            }
            else {
                try {
                    flightService.saveFlight(f);
                    model.addAttribute("success","Flight created successfully");
                }
                catch (Exception e){
                    model.addAttribute("error","Error during Flight data creation");
                }
            }
        }
        else {
            try {
                flightService.saveFlight(f);
                model.addAttribute("success", "Flight updated successfully");
            } catch (Exception e) {
                model.addAttribute("error", "Error during Flight update");
            }
        }
        model.addAttribute("flights",flightService.findAllFlight());
        return "flight";

    }

    @GetMapping("/edit/{id}")
    public String getFlightById(@PathVariable Integer id, Model model) throws FlightNotFoundException, NoAircraftFoundException {
        try {
            Flight flight=flightService.getFlightById(id);

            model.addAttribute("flight",flight);
            model.addAttribute("aircraft", airlineService.findAllAirline());
            model.addAttribute("success", "Flight found");

            return "flight-form";
        }
        catch (FlightNotFoundException f)
        {
            model.addAttribute("error","No flight Found ");
            model.addAttribute("flight",flightService.findAllFlight());
            return "flight";
        }
    }


    @GetMapping("/delete/{id}")
    public String removeFlightById(@PathVariable Integer id, RedirectAttributes model) throws FlightNotFoundException {
        try {
            flightService.deleteFlightById(id);
            model.addFlashAttribute("success", "Flight deleted successfully");
        }
        catch (FlightNotFoundException f)
        {
            model.addFlashAttribute("error", f.getMessage());
        }
        return "redirect:/flight/";

    }

}
