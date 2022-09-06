package com.flightbookingservice.app.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flightbookingservice.app.model.BookingRequest;
import com.flightbookingservice.app.repository.FlightbookingRepository;

@Service
public class BookingService {
	
	@Autowired
	FlightbookingRepository bookingrepo;
	
	public List<BookingRequest> getAllbookings()
	{
		return bookingrepo.findAll();
	}
	
	public BookingRequest getBooking(String bookingid)
	{
		try {
			return bookingrepo.findById(bookingid).get();
			}catch(Exception e)
		{
				CustomBookingException.message="No details Found";
				throw new CustomBookingException();
		}
	}
	
	
	public Integer adjustSeats(String flightName, Integer numberoftickets)
	{
		RestTemplate template= new RestTemplate();
		int temp1=template.getForObject("http://localhost:9006/flight/adjustseat/"+flightName+"/"+numberoftickets, Integer.class);
		return temp1;
	}
	public Integer adjustSeatsAfterCancellation(String flightName, Integer numberoftickets)
	{
		RestTemplate template= new RestTemplate();
		int temp1=template.getForObject("http://localhost:9006/flight/adjustseatsaftercancellation/"+flightName+"/"+numberoftickets, Integer.class);
		return temp1;
	}
	
	public String bookFlightdata(BookingRequest request)
	{
		int numberoftickets = request.getPassengers().size();
		int remainingseats = adjustSeats(request.getFlightName(), numberoftickets);
		
		return bookingrepo.save(request).getBookingId();

		//return bookingrepo.save(request).getBookingId();
	}
	
	public String deleteFlightbooking(String bookingId)
	{
		try {
			BookingRequest req=new BookingRequest();
			req=bookingrepo.findById(bookingId).get();
		Date current = new Date();
		long millis = req.getFromdate().getTime()-current.getTime();
		long diffInHours = TimeUnit.MILLISECONDS.toHours(millis);
		if(diffInHours>24)
		{
		
		int remainingseats = adjustSeatsAfterCancellation(req.getFlightName(), req.getNumberofseats());
		bookingrepo.deleteById(bookingId);

		return "Booking deleted";
		}
		else {
			return "Less than 24 hrs to cancel the booking";
		}
//		TimeZone tz=TimeZone.getTimeZone("UTC");
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
//		df.setTimeZone(tz);
//		String nowAsISO = df.format(new Date());
		
	}catch(Exception e)
	{
	throw new RuntimeException("Error while cancelling the booking "+e.getMessage());	
	}
}

}
