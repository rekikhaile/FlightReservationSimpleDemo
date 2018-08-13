package com.rekik.flightreservation.repos;

import com.rekik.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
/*
    @Query("select f from Flight f where f.departureCity=:departureCity and f.arrivalCity=:arrivalCity and f.dateofDeparture=:dateofDeparture")
*/
    /*List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity")String to,
                            @Param("dateofDeparture")Date departureDate);
*/
@Query("select f from Flight f where f.departureCity=:departureCity and f.arrivalCity=:arrivalCity")
List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity")String to);


}
