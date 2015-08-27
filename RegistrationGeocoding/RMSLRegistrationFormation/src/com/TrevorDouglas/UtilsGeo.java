/**
 * 
 */
package com.TrevorDouglas;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.google.code.geocoder.*;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;



/**
 * @author tdouglas
 *
 */
public class UtilsGeo {
	
	
	public GeocodeResponse getGeocode(String address)
	{
	
		final Geocoder geocoder = new Geocoder();
	
	
	
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(address).setLanguage("en").getGeocoderRequest();
		GeocodeResponse geocoderResponse = null;
		try {
			geocoderResponse = geocoder.geocode(geocoderRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return geocoderResponse;
	}
	
	public static  void requestGeoCodesFromPlayeList(ArrayList<Player> playerList)
	{
		
		for(int i= 0; i<playerList.size(); ++i)
		{
			if(playerList.get(i).getLatLng() == null)
			{
				Float[] coords = performGeoCoding(playerList.get(i).getAddress() + ", " + 
						"Regina");	
				if(coords != null)
				{
				
					LatLng latitudeLongitude = new LatLng();

					latitudeLongitude.setLat(new BigDecimal(coords[0]));
					latitudeLongitude.setLng(new BigDecimal(coords[1]));
					playerList.get(i).setLatLng(latitudeLongitude);
				}
				 				
			}
			
		}
		
	}
	
	
	public static Float[] performGeoCoding(String location) {
		  if (location == null)  
		     return null;
		       
		  Geocoder geocoder = new Geocoder();
		  GeocoderRequest geocoderRequest
		     = new GeocoderRequestBuilder()
		       .setAddress(location) // location
		       .setLanguage("en") // language
		       .getGeocoderRequest();
		  GeocodeResponse geocoderResponse;

		  try {
		    geocoderResponse = geocoder.geocode(geocoderRequest);
		    if (geocoderResponse.getStatus() == GeocoderStatus.OK
		      & !geocoderResponse.getResults().isEmpty()) {
		      GeocoderResult geocoderResult =  
		        geocoderResponse.getResults().iterator().next();
		      LatLng latitudeLongitude =
		        geocoderResult.getGeometry().getLocation();
		      Float[] coords = new Float[2];
		      coords[0] = latitudeLongitude.getLat().floatValue();
		      coords[1] = latitudeLongitude.getLng().floatValue();
		      return coords;
		    }
		  } catch (IOException ex) {
		    ex.printStackTrace();
		  }
		  return null;
		}
	
	
	
	
}