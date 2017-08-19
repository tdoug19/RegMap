package com.TrevorDouglas;

import java.util.ArrayList;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Utilities u = new Utilities();
		ArrayList<Player> playerList = new ArrayList<Player>();
		u.readRegistrantsFromFile("2015Registrations.csv", playerList);
		
		UtilsGeo ug = new UtilsGeo();
		
		ug.requestGeoCodesFromPlayeList(playerList);
		
		u.saveMergedRegistrantsToFile("test.csv", playerList);
		System.out.println("Finished!!!");

	}

}
