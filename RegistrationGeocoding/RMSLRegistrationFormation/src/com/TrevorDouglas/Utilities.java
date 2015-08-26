/**
 * 
 */
package com.TrevorDouglas;

import java.io.*;
import java.util.ArrayList;


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
						
						playerList.add(extractPlayer(s));
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
						
						
						Player p = extractPlayer(s);
						for(int i= 0; i<playerList.size(); ++i)
						{
							if (p.getName().equals(playerList.get(i).getName()))
							{
								System.out.println("match");
								
							}
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
		
		
		//A player may not have lat and long with it.
		private static Player extractPlayer(String s)
		{
			String[] ar=s.split(",");
			
			Player p = new Player(ar[0] + " " + ar[1], ar[7], Integer.parseInt(ar[2]));
			
			
			if(ar.length < 12)
			
			//Do we have lat and long elements... check..
			{
			
				String lat = ar[12];
				String newLat = lat.replace("\"", "");
				
				String longitude = "\"" + ar[13];
				String newLong = longitude.replace("\"", "");						
				
				p.setLatitude(Double.parseDouble(newLat));
				p.setLongitude(Double.parseDouble(newLong));
			}
			
			return p;
			
			
			
		}

		
		
		
		
		
		
		
		
}
