package com.spring.flight.controllers;

import com.spring.flight.exceptions.NoPassengerFoundException;
import com.spring.flight.models.Passenger;
import com.spring.flight.services.PassengerService;
import com.spring.flight.validators.PassengerDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PassengerDataValidator passengerDataValidator;

    @GetMapping("/")
    public String getPassengerHome()
    {
        return "redirect:/passenger/list";
    }

    @GetMapping("/list")
    public String fetchPassenger(Model model)
    {
        List<Passenger> li=passengerService.findAllPassenger();
        model.addAttribute("passengers", li);
        model.addAttribute("success", li.size()+" Passenger found");
        return "passenger";
    }

    @GetMapping("/new")
    public String createNewPassenger(Model model)
    {
        model.addAttribute("passenger", new Passenger());
        return "passenger-form";
    }

    @PostMapping("/save")
    public String savePassenger(@ModelAttribute Passenger p, Model model) {
        if(p.getId()==null)       // agar null hoga to add kar denge-->(By AKM)
        {
            List<String> errors=passengerDataValidator.validate(p);
            if(!errors.isEmpty()) {
                model.addAttribute("error", errors);
            }
            else {
                try {
                    passengerService.savePassenger(p);
                    model.addAttribute("success","Passenger created successfully");
                }
                catch (Exception e){
                    model.addAttribute("error","Error during Passenger data creation");
                }
            }
        }
        else {
            try {
                passengerService.savePassenger(p);
                model.addAttribute("success", "Passenger updated successfully");
            } catch (Exception e) {
                model.addAttribute("error", "Error during Passenger update");
            }
        }
        model.addAttribute("passengers",passengerService.findAllPassenger());
        return "passenger";
    }

    @GetMapping("/edit/{id}")
    public String getPassengerById(@PathVariable Integer id, Model model) {
        try {
            Passenger passenger = passengerService.getPassengerById(id);
            model.addAttribute("passenger", passenger);
            model.addAttribute("success", "Passenger Found");
            return "passenger-form";
        } catch (NoPassengerFoundException ne) {
            List<Passenger> li = passengerService.findAllPassenger();
            model.addAttribute("error", "No passenger found ");
            model.addAttribute("passenger", li);
            return "passenger";
        }
    }

    @GetMapping("/delete/{id}")
    public String removePassengerById(@PathVariable Integer id,RedirectAttributes model)
    {

        try {
            passengerService.deletePassengerById(id);
            model.addFlashAttribute("success","Passenger Removed");
        }
        catch (NoPassengerFoundException a){
            model.addFlashAttribute("error",a.getMessage());
        }
        return "redirect:/passenger/";
    }
}
