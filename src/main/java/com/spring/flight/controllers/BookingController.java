package com.spring.flight.controllers;

import com.spring.flight.exceptions.BookingNotFoundException;
import com.spring.flight.exceptions.FlightNotFoundException;
import com.spring.flight.models.Booking;
import com.spring.flight.models.Flight;
import com.spring.flight.models.Payment;
import com.spring.flight.services.BookingService;
import com.spring.flight.services.FlightService;
import com.spring.flight.services.PassengerService;
import com.spring.flight.validators.BookingDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private BookingDataValidator bookingDataValidator;

    @GetMapping("/list")
    public String fetchBooking(Model model)
    {
        List<Booking> li=bookingService.findAllBooking();
        model.addAttribute("bookings",li);
        model.addAttribute("success", li.size()+" Booking Found");
        return "booking";
    }

    @GetMapping("/new")
    public String createNewBooking(Model model, @RequestParam Integer flightId){
        try {
            Flight flight = flightService.getFlightById(flightId);
            Booking booking = new Booking();
            booking.setFlight(flight);
            booking.setPayment(new Payment());
            model.addAttribute("booking",booking);
            model.addAttribute("passengers", passengerService.findAllPassenger());

                model.addAttribute("flight", flightService.getFlightById(flightId));
        } catch (FlightNotFoundException e) {
            model.addAttribute("error", "Flight not found");
        }

        return "booking-form";
    }

    @GetMapping("/delete/{id}")
    public String removeBookingById(@PathVariable Integer id,Model model)
    {
       try {
           bookingService.deleteBookingById(id);
           model.addAttribute("success","Booking Cancelled");
       }
       catch (BookingNotFoundException n)
       {
           model.addAttribute("error", n.getMessage());
       }
       model.addAttribute("bookings", bookingService.findAllBooking());
       return "booking";
    }



    @PostMapping("/save")
    public String saveBooking(@ModelAttribute Booking b, Model model) {
        model.addAttribute("flights", flightService.findAllFlight());
        model.addAttribute("passengers", passengerService.findAllPassenger());
        if (b.getId() == null) {
            List<String> errors = bookingDataValidator.validate(b);
            if (!errors.isEmpty()) {
                model.addAttribute("error", errors);
            } else {
                try {
                    bookingService.saveABooking(b);
                    model.addAttribute("success", "Booking Successfull");
                } catch (Exception e) {
                    model.addAttribute("error", "Error during Booking");
                }
            }
        } else {
            try {
                bookingService.saveABooking(b);
                model.addAttribute("success", "Booking updated successfully");
            } catch (Exception e) {
                model.addAttribute("error", "Error during Booking update");
            }
        }
        model.addAttribute("bookings", bookingService.findAllBooking());
        return "redirect:/booking/list";
    }

    @GetMapping("/edit/{id}")
    public String getBookingById(@PathVariable Integer id, Model model) {
        try {
            Booking booking = bookingService.getBookingById(id);
            model.addAttribute("booking", booking);
            model.addAttribute("passengers", passengerService.findAllPassenger());
            model.addAttribute("flights", flightService.findAllFlight());
            model.addAttribute("success", "Booking found");
            return "booking-form";
        } catch (BookingNotFoundException n) {
            model.addAttribute("error", "No Booking Found ");
            model.addAttribute("bookings", bookingService.findAllBooking());
            return "booking";
        }
    }

    @GetMapping("/planner")
    public String getPlanner(){
        return "ticket";
    }

    @GetMapping("/search") // PathVariable vs RequestParam
    public String searchResult(Model model, @RequestParam LocalDateTime journeyDate, @RequestParam String source, @RequestParam String destination){
        List<Flight> res = flightService.searchFlight(source, destination);
        if(res.isEmpty()){
            model.addAttribute("error", "No Flights Found");
            return "ticket";
        }
        model.addAttribute("flights", res);
        model.addAttribute("success", res.size() + " flights found");
        return "ticket";
    }
}
