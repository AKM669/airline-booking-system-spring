package com.spring.flight.controllers;

import com.spring.flight.enums.PaymentMode;
import com.spring.flight.exceptions.BookingNotFoundException;
import com.spring.flight.models.Booking;
import com.spring.flight.services.BookingService;
import com.spring.flight.services.FlightService;
import com.spring.flight.services.PassengerService;
import com.spring.flight.validators.BookingDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

//    @Autowired
//    private PaymentMode paymentMode;

    @Autowired
    private BookingDataValidator bookingDataValidator;

    @GetMapping("/")
    public String getBookingHome()
    {
        return "redirect:/booking/list";
    }

    @GetMapping("/list")
    public String fetchBooking(Model model)
    {
        List<Booking> li=bookingService.findAllBooking();
        model.addAttribute("bookings",li);
        model.addAttribute("success", li.size()+" Booking Found");
        return "booking";
    }

    @GetMapping("/new")
    public String createNewBooking(Model model){
        model.addAttribute("booking",new Booking());
        model.addAttribute("passengers", passengerService.findAllPassenger());
        model.addAttribute("flights", flightService.findAllFlight());

        return "booking-form";
    }

    @GetMapping("/delete/{id}")
    public String removeBookingById(@PathVariable Integer id,RedirectAttributes model)
    {
       try {
           bookingService.deleteBookingById(id);
           model.addFlashAttribute("success","Booking Cancelled");
       }
       catch (BookingNotFoundException n)
       {
           model.addFlashAttribute("error", n.getMessage());
       }
       return "redirect:/booking/";
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
}
