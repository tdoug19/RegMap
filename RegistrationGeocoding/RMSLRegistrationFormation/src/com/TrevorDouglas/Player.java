/**
 * 
 */
package com.TrevorDouglas;

/**
 * @author tdouglas
 *
 */
public class Player {
	private String name;
	private String address;
	private int yearOfBirth;
	private double longitude, latitude;
	
	
	
	public Player(String name, String address, int yearOfBirth)
	{
		
		this.name = name;
		this.address = address;
		this.yearOfBirth = yearOfBirth;
		
	}

	
	public String getName()	{
		return name;
			
	}
	
	
	public String getAddress()	{
		
		return address;
	}
	
	
	public void setLongitude(Double longitude){
		this.longitude = longitude;
		
	}
	
	public void setLatitude(Double latitude){
		this.latitude = latitude;
	}
	
	
	
	
	
	
	
	
}
