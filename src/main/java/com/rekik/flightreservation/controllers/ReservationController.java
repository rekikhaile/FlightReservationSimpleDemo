package com.rekik.flightreservation.controllers;

import com.rekik.flightreservation.dto.ReservationRequest;
import com.rekik.flightreservation.entities.Flight;
import com.rekik.flightreservation.entities.Passenger;
import com.rekik.flightreservation.entities.Reservation;
import com.rekik.flightreservation.repos.FlightRepository;
import com.rekik.flightreservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ReservationService reservationService;

    @RequestMapping("/showCompleteReservation/{id}")
    public String showCompleteReservaton(@PathVariable("id") Long id, ModelMap modelMap){
        //changed requestparam to pathvariable and it works

        Flight flight = flightRepository.findById(id).get();
        modelMap.addAttribute("flight",flight);
        modelMap.addAttribute("passenger", new Passenger());
        return "completereservation";
    }

    @RequestMapping(value = "/completereservation/{id}", method = RequestMethod.POST)
    public String completeReservation(
            @PathVariable("id") Long id, ReservationRequest request , ModelMap modelMap){
        Reservation reservation = reservationService.bookFlight(request);
        modelMap.addAttribute("msg", "Reservation created succesfully " +
                "and the id is "+ reservation.getId());
        return "reservationconfirmation";
    }

}
