package iiitb.mobility.fleetmgmtsystem;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import iiitb.mobility.fleetmgmtsystem.controller.PlotMarkers;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, LocationListener{
	
	MapView mapView;
	MapController mapController;
	GeoPoint geoPoint;
	
	
	private Button btnUpdateView;
	private GoogleMap googleMap, vehicleMap;
	private Location currentLocation;
	private LocationClient locationClient;
	private Marker marker;
	final Context context = this;
	StringBuffer newResult = new StringBuffer();
	private String oldResult = "";
	protected LocationManager locationManager;
	protected LocationListener locationListener;
	
	PlotMarkers obj = new PlotMarkers();
	
	
   
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        locationClient = new LocationClient(this, this, this);        
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }
    
    @Override
    protected void onStart(){
    	super.onStart();
    	addListenerOnButton();
    	locationClient.connect();
    	
    	CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(12.9667, 77.5667)).zoom(12).build();
 
    	 googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    	 googleMap.addMarker(new MarkerOptions().position(new LatLng(12.9667, 77.5667)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
    	 
    	 Location defaultLocation = new Location(Context.LOCATION_SERVICE);
    	 
    	 onLocationChanged(defaultLocation);
    	 
    	 vehicleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
     	 vehicleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(oldResult));
     	 
     	
     	try{
	         FileInputStream fin = openFileInput("updateView.txt");
	         int c;
	         //String temp="";
	         while( (c = fin.read()) != -1){
	            oldResult = oldResult + Character.toString((char)c);
	         }
	         obj.plotMarkers(oldResult, googleMap, vehicleMap);
	         //oldResult = null;
	         //Toast.makeText(MainActivity.this, oldResult.toString(), Toast.LENGTH_LONG).show();

	      }catch(Exception e){

	      }
     	 
     	
     	
     		
    }
    
    public void addListenerOnButton(){
    	
    	btnUpdateView = (Button) findViewById(R.id.btnUpdateView);
    	
    	vehicleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    	vehicleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(oldResult));
    	
    	btnUpdateView.setOnClickListener(new OnClickListener() {
    		 
  		  @Override
  		  public void onClick(View arg0) {
  			  
  			  final String views[] = { "Vehicle Asset", "Commodity Asset", "Human Asset", "Vehicle Base"};
  			  final boolean[] itemsChecked = new boolean[views.length];
  			Toast.makeText(MainActivity.this, oldResult.toString(), Toast.LENGTH_LONG).show();
  			  for(int i=0;i<views.length;i++){
  				  if(oldResult.charAt(i)=='1'){
  					  itemsChecked[i] = true; 
  				  }
  			  }
  			  
  			  AlertDialog.Builder builder = new AlertDialog.Builder(context);
  			  
  			  builder.setTitle("Choose a view")
  			  			.setMultiChoiceItems(views, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which, boolean isChecked) {
								// TODO Auto-generated method stub
								itemsChecked[which] = isChecked;						
							}
						})
						.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								for(int i=0;i<views.length;i++){
									if(itemsChecked[i]){
										newResult.append("1");
									}
									else{
										newResult.append("0");
									}
								}
								//Toast.makeText(MainActivity.this, newResult.toString(), Toast.LENGTH_LONG).show();
								try {
									FileOutputStream fOut = openFileOutput("updateView.txt",Context.MODE_PRIVATE);
							         fOut.write(newResult.toString().getBytes());
							         oldResult = newResult.toString();
							         fOut.close();
							         								
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								obj.plotMarkers(newResult.toString(), googleMap, vehicleMap);
								newResult.setLength(0);								
							}
						})
						.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						});
  			  			AlertDialog alertDialog = builder.create();
  			  			alertDialog.show();
    		  }
  		});
    	
    	    	
    }

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onStop(){
		locationClient.disconnect();
		super.onStop();
		
		
	}

	public class CustomInfoWindowAdapter implements InfoWindowAdapter{
		
		private View view;
		
		public CustomInfoWindowAdapter(String val){
			
				view = getLayoutInflater().inflate(R.layout.infowindow_vehicle, null);
		}

		@Override
		public View getInfoContents(Marker marker) {
			// TODO Auto-generated method stub
			if(MainActivity.this.marker != null && MainActivity.this.marker.isInfoWindowShown()){
				MainActivity.this.marker.hideInfoWindow();
				MainActivity.this.marker.showInfoWindow();
			}
			return null;
		}

		@Override
		public View getInfoWindow(Marker marker) {
			// TODO Auto-generated method stub
			if(marker != null){
				//TextView titleUi = (TextView) view.findViewById(R.id.vehicleInfo);
				//titleUi.setText(marker.getTitle());
			}
			return view;
		}
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		googleMap.addMarker(new MarkerOptions()
	   	 .position(new LatLng(location.getLatitude(),location.getLongitude()))
	   			 .icon(BitmapDescriptorFactory.
	   					 fromResource(R.drawable.ic_launcher_current)));
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}
