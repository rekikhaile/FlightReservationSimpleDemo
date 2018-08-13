package com.rekik.flightreservation.services;

import com.rekik.flightreservation.dto.ReservationRequest;
import com.rekik.flightreservation.entities.Reservation;

public interface ReservationService {
    public Reservation bookFlight(ReservationRequest request);
}
