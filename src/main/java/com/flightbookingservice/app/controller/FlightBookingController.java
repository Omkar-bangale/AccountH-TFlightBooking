package com.flightbookingservice.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flightbookingservice.app.model.BookingRequest;
import com.flightbookingservice.app.model.SearchRequest;
import com.flightbookingservice.app.model.SearchResponse;
import com.flightbookingservice.app.services.BookingService;

@RestController
public class FlightBookingController {

	@Autowired
	private BookingService bookingservice;
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders httpheaders;

	@PostMapping("/flightbooking/book")
	public String bookFlight(@RequestHeader Map<String, String> headers, @RequestBody BookingRequest bookingrequest) {

		FlightBookingController.validateToken(headers);
		if (validated) {
			return bookingservice.bookFlightdata(bookingrequest);
		} else {
			throw new RuntimeException("Token validation failed");
		}
	}

	@GetMapping("/flightbooking/getbooking/{bookingId}")
	public BookingRequest getAllBookings(@RequestHeader Map<String, String> headers, @PathVariable String bookingId) {
		FlightBookingController.validateToken(headers);
		if (validated)
			return bookingservice.getBooking(bookingId);
		else
			throw new RuntimeException("Token Validation Failed");
	}

	@DeleteMapping("/flightbooking/deletebooking/{bookingId}")
	public String deleteBooking(@PathVariable String bookingId, @RequestHeader Map<String, String> headers)
	{
		FlightBookingController.validateToken(headers);
		if (validated)
			return bookingservice.deleteFlightbooking(bookingId);
		else
	throw new RuntimeException("Token Validation Failed");
	}
	private static boolean validated = false;

	public static void validateToken(Map<String, String> header) {

		String token = "";
		for (String key : header.keySet()) {
			if (key.equals("authorization"))
				token = header.get(key);
		}
		HttpHeaders httpheader = new HttpHeaders();
		httpheader.set("Authorization", token);
		HttpEntity<Void> requestentity = new HttpEntity<>(httpheader);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:9004/validatejwt", HttpMethod.GET,
				requestentity, boolean.class);
		validated = response.getBody().booleanValue();
	}

}
