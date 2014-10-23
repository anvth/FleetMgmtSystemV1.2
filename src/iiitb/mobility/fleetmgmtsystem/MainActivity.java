package iiitb.mobility.fleetmgmtsystem;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener{
	
	MapView mapView;
	MapController mapController;
	GeoPoint geoPoint;
	
	
	private Button btnUpdateView;
	private GoogleMap googleMap, vehicleMap;
	private Location currentLocation;
	private LocationClient locationClient;
	final Context context = this;
	StringBuffer newResult = new StringBuffer();
	
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
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationClient = new LocationClient(this, this, this);        
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    }
    
    @Override
    protected void onStart(){
    	super.onStart();
    	addListenerOnButton();
    	locationClient.connect();
    	
    	CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(12.9667, 77.5667)).zoom(9).build();
 
    	 googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    	 googleMap.addMarker(new MarkerOptions().position(new LatLng(12.9667, 77.5667)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
    }
    
    public void addListenerOnButton(){
    	
    	btnUpdateView = (Button) findViewById(R.id.btnUpdateView);
    	
    	vehicleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    	
    	btnUpdateView.setOnClickListener(new OnClickListener() {
    		 
  		  @Override
  		  public void onClick(View arg0) {
  			  
  			  final String views[] = { "Vehicle Asset", "Commodity Asset", "Human Asset", "Vehicle Base"};
  			  final boolean[] itemsChecked = new boolean[views.length];
  			  
  			  AlertDialog.Builder builder = new AlertDialog.Builder(context);
  			  
  			  builder.setTitle("Choose a view")
  			  			.setMultiChoiceItems(views, null, new DialogInterface.OnMultiChoiceClickListener() {
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
								Toast.makeText(MainActivity.this, newResult.toString(), Toast.LENGTH_LONG).show();
								plotMarkers();
								
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

    public void plotMarkers(){
    	if(newResult.toString().equalsIgnoreCase("1000")){
			for(int i=0;i<6;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title("KA-50-K-2540").snippet("hbhdsb"+ "\n" + "abcd"));
			}
			for(int i=6;i<8;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
			}
			for(int i=8;i<10;i++){
				vehicleMap.addMarker(new MarkerOptions().position(new LatLng(locations[i][0], locations[i][1])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
			}
		}
		else if(newResult.toString().equalsIgnoreCase("1100")){
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
		else if(newResult.toString().equalsIgnoreCase("1110")){
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
		else if(newResult.toString().equalsIgnoreCase("0000")){
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
    	newResult.setLength(0);
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

	
}
