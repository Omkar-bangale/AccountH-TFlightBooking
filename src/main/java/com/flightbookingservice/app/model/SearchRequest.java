package com.flightbookingservice.app.model;

import java.util.Date;

public class SearchRequest {

	private Date fromdate;
	private Date todate;
	private String source;
	private String destination;
	public SearchRequest(Date fromdate, Date todate, String source, String destination) {
		super();
		this.fromdate = fromdate;
		this.todate = todate;
		this.source = source;
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "SearchRequest [fromdate=" + fromdate + ", todate=" + todate + ", source=" + source + ", destination="
				+ destination + "]";
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
	
	public SearchRequest() {}
	
	
}
