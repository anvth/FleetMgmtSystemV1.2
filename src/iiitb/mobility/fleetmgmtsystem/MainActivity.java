package iiitb.mobility.fleetmgmtsystem;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	MapView mapView;
	MapController mapController;
	GeoPoint geoPoint;
	
	private CheckBox chkVehicleAsset, chkCommodityAsset, chkHumanResource, chkVehicleBase;
	private Button btnUpdateCheckList;
	private GoogleMap googleMap;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addListenerOnButton();
        
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.addMarker(new MarkerOptions().position(new LatLng(12.9667, 77.5667)));
    }
    
    public void addListenerOnButton(){
    	chkVehicleAsset = (CheckBox) findViewById(R.id.chkVehicleAsset);
    	chkCommodityAsset = (CheckBox) findViewById(R.id.chkCommodityAsset);
    	chkHumanResource = (CheckBox) findViewById(R.id.chkHumanAsset);
    	chkVehicleBase = (CheckBox) findViewById(R.id.chkVehicleBase);
    	
    	btnUpdateCheckList = (Button) findViewById(R.id.btnUpdateCheckList);
    	
    	btnUpdateCheckList.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StringBuffer result = new StringBuffer();
				result.append(chkVehicleAsset.isChecked()+" ");
				result.append(chkCommodityAsset.isChecked()+" ");
				result.append(chkHumanResource.isChecked()+" ");
				result.append(chkVehicleBase.isChecked()+" ");
				
				Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();
			}
		});
    }
}
