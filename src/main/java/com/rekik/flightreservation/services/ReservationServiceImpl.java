package com.rekik.flightreservation.services;

import com.rekik.flightreservation.dto.ReservationRequest;
import com.rekik.flightreservation.entities.Flight;
import com.rekik.flightreservation.entities.Passenger;
import com.rekik.flightreservation.entities.Reservation;
import com.rekik.flightreservation.repos.FlightRepository;
import com.rekik.flightreservation.repos.PassengerRepository;
import com.rekik.flightreservation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation bookFlight(ReservationRequest request) {
       // here we would use third party payment gateway web service(not this time)
        Long flightId = request.getId();
        Flight flight = flightRepository.findById(flightId).get();

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getFirstName());
        passenger.setLastName(request.getLastName());
        passenger.setPhone(request.getPhone());
        passenger.setEmail(request.getEmail());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
        Reservation savedReservation = reservationRepository.save(reservation);

        return savedReservation;
    }
}
