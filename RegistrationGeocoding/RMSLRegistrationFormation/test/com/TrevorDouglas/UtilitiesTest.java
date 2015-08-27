package com.TrevorDouglas;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class UtilitiesTest {

	@Test
	public void testSaveStringToFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadRegistrantsFromFile() {
		ArrayList<Player> playerList = new ArrayList<Player>();
		Utilities.readRegistrantsFromFile("2015Registrations.csv", playerList);
		//Ok.. now merge with new registrants.
		
		Utilities.mergeNewRegistrantsFromFile("withGeo.csv", playerList);
		
	
	}
	
	
	@Test
	public void testSaveMergedRegistrantsToFile(){
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		Utilities.readRegistrantsFromFile("2015Registrations.csv", playerList);
		//Ok.. now merge with new registrants.
		
		Utilities.mergeNewRegistrantsFromFile("withGeo.csv", playerList);
		
		
		//ok now.. lets request some geocodes
		for(int i = 0; i<20; ++i)
		{
			System.out.println("Iteration: " + i + " requesting Geocodes");
			UtilsGeo.requestGeoCodesFromPlayeList(playerList);
		}

		boolean result = Utilities.saveMergedRegistrantsToFile("testout.csv", playerList);

		
	}

}
