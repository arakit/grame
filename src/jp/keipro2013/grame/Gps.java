package jp.keipro2013.grame;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class Gps implements LocationListener
{
	Context mContext;

	private LocationManager manager = null;

	public float latitude = 0.0f;
	public float longitude = 5.0f;
	public float altitude = 0.0f;

	public Gps(Context context){

		mContext = context;

		manager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

		if(manager == null)
		{
		}
		else
		{
			
		}
	}

	public void getGps(){
		if(manager != null){
			manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		}
	}

	public void stopGps(){
		if(manager != null){
			manager.removeUpdates(this);
		}
	}

	@Override
	public void onLocationChanged(Location location){
		latitude = (float)location.getLatitude();

		longitude = (float)location.getLongitude();

		altitude = (float)location.getAltitude();
	}

	@Override
	public void onProviderDisabled(String provider){

	}

	@Override
	public void onProviderEnabled(String provider){

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras){

	}

	public float GetLatitude(){
		return latitude;
	}

	public float GetLongitude(){
		return longitude;
	}
	
	public float GetAltitude(){
		return altitude;
	}
}