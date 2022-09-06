package com.flightbookingservice.app.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("bookingdata")
public class BookingRequest {

	@Id
	private String bookingId;
	
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	private String flightName;
	private String airlineName;
	private String source;
	private String destination;
	private Date fromdate;
	private Date todate;
	private int numberofseats;
	private List<Passenger> passengers;
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getFromdate() {
		return fromdate;
	}
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public int getNumberofseats() {
		return numberofseats;
	}
	public void setNumberofseats(int numberofseats) {
		this.numberofseats = numberofseats;
	}
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	@Override
	public String toString() {
		return "BookingRequest [bookingId=" + bookingId + ", flightName=" + flightName + ", airlineName=" + airlineName
				+ ", source=" + source + ", destination=" + destination + ", fromdate=" + fromdate + ", todate="
				+ todate + ", numberofseats=" + numberofseats + ", passengers=" + passengers + "]";
	}
	public BookingRequest(String bookingId, String flightName, String airlineName, String source, String destination,
			Date fromdate, Date todate, int numberofseats, List<Passenger> passengers) {
		super();
		this.bookingId = bookingId;
		this.flightName = flightName;
		this.airlineName = airlineName;
		this.source = source;
		this.destination = destination;
		this.fromdate = fromdate;
		this.todate = todate;
		this.numberofseats = numberofseats;
		this.passengers = passengers;
	}
	
	public BookingRequest() {}
	
}
