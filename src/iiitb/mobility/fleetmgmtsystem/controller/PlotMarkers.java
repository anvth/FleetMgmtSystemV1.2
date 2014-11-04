package iiitb.mobility.fleetmgmtsystem.controller;


import java.io.FileInputStream;

import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlotMarkers {
	
	//private GoogleMap vehicleMap;
	
	private Double[][] locations = new Double[10][2];{
		locations[0][0]= 13.076970918273926;
		locations[0][1]= 77.595664208984375;
		locations[1][0]= 12.941904067993164;
		locations[1][1]= 77.58087921142578;
		locations[2][0]= 12.979331016540527;
		locations[2][1]= 77.59944915771484;
		locations[3][0]= 12.93258285522461;
		locations[3][1]= 77.61878967205156;
		locations[4][0]= 12.949662208557129;
		locations[4][1]= 77.58612823486328;
		locations[5][0]= 12.91746711730957;
		locations[5][1]= 77.57286071777344;
		locations[6][0]= 12.997771263122559;
		locations[6][1]= 77.55481719970703;
		locations[7][0]= 13.019174575805664;
		locations[7][1]= 77.56629180908203;
		locations[8][0]= 13.011507987976074;
		locations[8][1]= 77.57398223876953;
		locations[9][0]= 12.978318214416504;
		locations[9][1]= 77.572280883778906;
	}

	public void plotMarkers(String newResult, GoogleMap googleMap, GoogleMap vehicleMap){
		
    	if(newResult.equalsIgnoreCase("1000")){
			for(int i=0;i<6;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));/*.title("KA-50-K-2540").snippet("hbhdsb"+ "\n" + "abcd"))*/
			}
			for(int i=6;i<8;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
			}
			for(int i=8;i<10;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
			}
		}
		else if(newResult.equalsIgnoreCase("1100")){
			for(int i=0;i<5;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
			}
			for(int i=5;i<8;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
			}
			for(int i=8;i<10;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
			}
		}
		else if(newResult.equalsIgnoreCase("1110")){
			for(int i=0;i<4;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
			}
			for(int i=4;i<8;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
			}
			for(int i=8;i<10;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
			}
		}
		else if(newResult.equalsIgnoreCase("0000")){
			vehicleMap.clear();
	        googleMap.addMarker(new MarkerOptions().position(new LatLng(12.9667, 77.5667)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
		}
		else{
			for(int i=0;i<4;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
			}
			for(int i=4;i<7;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
			}
			for(int i=7;i<10;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
			}
		}
}
}