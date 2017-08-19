package com.TrevorDouglas;

import java.util.ArrayList;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Utilities u = new Utilities();
		ArrayList<Player> playerList = new ArrayList<Player>();
		u.readRegistrantsFromFile("2017Registrations.csv", playerList);
		
		UtilsGeo ug = new UtilsGeo();
		
		UtilsGeo.requestGeoCodesFromPlayeList(playerList);
		
		Utilities.saveMergedRegistrantsToFile("test.csv", playerList);
		System.out.println("Finished!!!");

	}

}
