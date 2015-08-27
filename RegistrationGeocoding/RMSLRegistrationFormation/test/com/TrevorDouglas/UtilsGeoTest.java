/**
 * 
 */
package com.TrevorDouglas;

import static org.junit.Assert.*;

import com.google.code.geocoder.model.GeocodeResponse;

import org.junit.Test;

/**
 * @author tdouglas
 *
 */
public class UtilsGeoTest {

	/**
	 * Test method for {@link com.TrevorDouglas.UtilsGeo#getGeocode(java.lang.String)}.
	 */
	@Test
	public void testGetGeocode() {
		
		UtilsGeo ug = new UtilsGeo();
		GeocodeResponse gr = ug.getGeocode("3210 Tufts Bay, Regina");
		
		int i =0;
		++i;
		

	}
	
	@Test
	public void testPerformGeoCoding()
	{
	
		String location = "Troia, Foggia, Italy";
		UtilsGeo ug = new UtilsGeo();
		Float[] coords = ug.performGeoCoding(location);
	  System.out.println(location + ": " 
	      + coords[0] + ", " + coords[1]);
	
	}
	
	

}
