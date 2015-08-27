/**
 * 
 */
package com.TrevorDouglas;

import java.util.Date;

import com.google.code.geocoder.model.LatLng;

/**
 * @author tdouglas
 *
 */
public class Player {
	private String name;
	private String address;
	private Date dateOfBirth;
	private double longitude, latitude;
	private LatLng latitudeLongitude;
	private String postalCode;
	
	
	
	public Player(String name, String address, Date dateOfBirth, String postalCode)
	{
		
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.postalCode = postalCode;
		
	}

	public Player(String name, String address, Date dateOfBirth)
	{
		
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.postalCode = null;
		
	}
	
	public String getName()	{
		return name;
			
	}
	
	public Date getDate()	{
		return dateOfBirth;
	}
	
	public String getAddress()	{
		
		return address;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setLatLng(LatLng latitudeLongitude)
	{
		this.latitudeLongitude = latitudeLongitude;
		
	}
	
	
	public LatLng getLatLng()
	{
		return latitudeLongitude;
		
	}
	
	
	
	
	public void setLongitude(Double longitude){
		this.longitude = longitude;
		
	}
	
	public void setLatitude(Double latitude){
		this.latitude = latitude;
	}
	
	
	
	
	
	
	
	
}
