package com.flightbookingservice.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightbookingservice.app.model.BookingRequest;

public interface FlightbookingRepository extends MongoRepository<BookingRequest, String>{

	
	
}
