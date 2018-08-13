package com.rekik.flightreservation.controllers;

import com.rekik.flightreservation.entities.Flight;
import com.rekik.flightreservation.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    FlightRepository flightRepository;


    @RequestMapping(value = "/findflights")
    public String findFlightsnow(@RequestParam("from") String from,
                              @RequestParam("to") String to,/*
                             @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate,*/
                              ModelMap modelMap){

        List<Flight> flights = flightRepository.findFlights(from,to);

        //  List<Flight> flights = flightRepository.findFlights(from,to,departureDate);
        //List<Flight> flights = flightRepository.findAll();
        modelMap.addAttribute("flights",flights);
        for(Flight item: flights){
            System.out.println("Hi "+item);
        }
       ;
        return "displayflights";
    }
}
