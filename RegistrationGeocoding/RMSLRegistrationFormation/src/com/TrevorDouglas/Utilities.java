/**
 * 
 */
package com.TrevorDouglas;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.google.code.geocoder.model.LatLng;



/**
 * @author tdouglas
 *
 */
public class Utilities {

		public static boolean saveStringToFile(String fileName, String stringToSave){
			boolean saved = false;
			
			
			BufferedWriter bw = null;
			
			try{
				bw = new BufferedWriter(new FileWriter(fileName));
				//if the above failed then this will skip
				
				try{
					bw.write(stringToSave);
					saved = true;
				}
				finally{		//Want to make sure this gets executed to close the file.
					bw.close();
				}
			}
			catch (IOException ex){
				
				ex.printStackTrace();
			}
			
			return saved;
			
		}
		

		public static String getStringFromFile(){
			
			return null;
		}
		
		
		
		//This should be a generic method... but use this for now.
		//Eventually this will be a database but for now use a file.
		public static boolean readRegistrantsFromFile(String fileName, ArrayList<Player> playerList){
			
			BufferedReader br = null;
		
			
			try{
				br = new BufferedReader(new FileReader(fileName));
				//if the above failed then this will skip
				try{
					String s;
					s = br.readLine();  // Chuck out the first line header
					while((s = br.readLine()) != null){
						
						Player p;
						try {
							p = extractPlayer(s);
							boolean addPlayer = true;
							for(int i= 0; i<playerList.size(); ++i)
							{
								if (p.getName().equalsIgnoreCase(playerList.get(i).getName()))
								{
									//We have a match now see if they have moved
						//			System.out.println("match");
									//for now break and move to the next line.
									addPlayer = false;
									break;
								}
							}
									
							if(addPlayer){
								playerList.add(p);
							}
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
						
					}

				}
				finally{
					br.close();
				}
			
			}
			
			catch (IOException ex){
				
				ex.printStackTrace();
			}
			
			
			
			return false;
			
			
		}

		
		
		//This should be a generic method... but use this for now.
		public static boolean mergeNewRegistrantsFromFile(String fileName, ArrayList<Player> playerList){
			
			BufferedReader br = null;
		
			
			try{
				br = new BufferedReader(new FileReader(fileName));
				//if the above failed then this will skip
				try{
					String s;
					s = br.readLine();  // Chuck out the first line header
					while((s = br.readLine()) != null){
						
						Player p;
						try {
							p = extractPlayerFromOldData(s);
							boolean addPlayer = true;
							for(int i= 0; i<playerList.size(); ++i)
							{
								if (p.getName().equalsIgnoreCase(playerList.get(i).getName()))
								{
									
									//We have a match... check to see if there is a latitude or longitude for them.
									if(playerList.get(i).getLatLng() == null && p.getLatLng() != null)
									{
										playerList.get(i).setLatLng(p.getLatLng());
									}
									
									//We have a match now see if they have moved
						//			System.out.println("match");
									//for now break and move to the next line.
									addPlayer = false;
									break;
								}
							}
									
							if(addPlayer){
								playerList.add(p);
							}
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
						
					}
				}
				finally{
					br.close();
				}
			
			
			
			}
			
			catch (IOException ex){
				
				ex.printStackTrace();
			}
			
			
			
			return true;
			
			
		}
		
		
		//This should be a generic method... but use this for now.
		public static boolean saveMergedRegistrantsToFile(String fileName, ArrayList<Player> playerList){
			boolean saved = false;
			writeToCSV(fileName, playerList);
			
			
			return saved;
			
		}
				
		
		//A player may not have lat and long with it.
		private static Player extractPlayerFromOldData(String s) throws ParseException
		{
			String[] ar=s.split(",");
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			cal.setTime(sdf.parse(ar[4]));// all done

			Player p = new Player(ar[0] + " " + ar[1], ar[7], cal);
			
			if(ar.length > 12)
			
			//Do we have lat and long elements... check..
			{
			
				String lat = ar[12];
				lat = lat.replace("\"", "");
				
				String longitude = "\"" + ar[13];
				longitude = longitude.replace("\" ", "");						
				longitude = longitude.replace("\"", "");
				
				LatLng latitudeLongitude = new LatLng();
				latitudeLongitude.setLat(new BigDecimal(lat));
				latitudeLongitude.setLng(new BigDecimal(longitude));
				
				p.setLatLng(latitudeLongitude);
				
			}
			
			return p;
			
		}
		

		//A player may not have lat and long with it.
		private static Player extractPlayer(String s) throws ParseException
		{
			String[] ar=s.split(",");
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			cal.setTime(sdf.parse(ar[4]));// all done
			String addr = new String(ar[6] + ',' + ar[7]);
			Player p = new Player(ar[1] + " " + ar[2], addr, cal);
		
			if(ar.length > 16)
			
			//Do we have lat and long elements... check..
			{
			
				String lat = ar[12];
				lat = lat.replace("\"", "");
				
				String longitude = "\"" + ar[13];
				longitude = longitude.replace("\" ", "");						
				longitude = longitude.replace("\"", "");
				
				LatLng latitudeLongitude = new LatLng();
				latitudeLongitude.setLat(new BigDecimal(lat));
				latitudeLongitude.setLng(new BigDecimal(longitude));
				
				p.setLatLng(latitudeLongitude);
				
			}
			
			return p;
			
		}
		
		
		
		private static final String CSV_SEPARATOR = ",";
		private static void writeToCSV(String fileName, ArrayList<Player> playerList)
		{
			try
		    {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
		        for (Player player : playerList)
		        {
		        	StringBuffer oneLine = new StringBuffer();
		             
		        	oneLine.append(player.getAddress());
		            oneLine.append(CSV_SEPARATOR);
		        	//oneLine.append(player.getName());
		            //oneLine.append(CSV_SEPARATOR);
		            oneLine.append(player.getDate().get(Calendar.YEAR));
		            oneLine.append(CSV_SEPARATOR);
		            
		            if (player.getLatLng() != null)
		            {
		            	//oneLine.append(player.getLatLng().getLat());
		            	//oneLine.append(CSV_SEPARATOR);
		            	//oneLine.append(player.getLatLng().getLng());
		            	oneLine.append('"');
		            	oneLine.append(player.getLatLng().getLat());
		            	oneLine.append(',');
		            	oneLine.append(player.getLatLng().getLng());
		            	oneLine.append('"');
		            	
		            }
		            
             
	                bw.write(oneLine.toString());
	                bw.newLine();
	            }
	            bw.flush();
	            bw.close();
	       }
		   catch (UnsupportedEncodingException e) {}
		   catch (FileNotFoundException e){}
		   catch (IOException e){}
		}
}
