package jp.keipro2013.grame;

import android.app.Activity;
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

	//static使う予定
	//緯度
	public float latitude = 0.0f;
	//経度
	public float longitude = 5.0f;
	//標高
	public float altitude = 0.0f;

/*
	//初期化時に呼ばれる
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		//super.onCreate(savedInstanceState);

		//setContentView(R.layout.activity_main);

		Log.i("System.out" , "GPS onCreate前！！");

		// GPSサービス取得
		manager = (LocationManager)getSystemService(LOCATION_SERVICE);

		if(manager == null)
		{
			Log.i("System.out" , "マネージャーnull");
		}
		else
		{
			Log.i("System.out" , "マネージャーOK");
		}

		Log.i("System.out" , "GPS onCreate成功！！");

		latitude = 0.0f;
		longitude = 5.0f;
		altitude = 0.0f;
	}
*/

/*
	//GPS情報の更新を停止
	@Override
	protected void onPause()
	{
		Log.i("System.out" , "!!onPauseStart!!");
		if(manager != null)
		{
			Log.i("System.out" , "!!onPauseStart!!if");
			//位置情報取得が不要になった時
			manager.removeUpdates(this);
		}
		super.onPause();
	}
*/

/*
	//位置情報の取得
	@Override
	protected void onResume()
	{
		Log.i("System.out" , "!!onresumeStart!!");
		if(manager != null)
		{
			Log.i("System.out" , "!!onresumeStart!!if");
			//位置情報取得
			//引数（GPSを利用する, 取得時間最少時間, 最少距離, ？？？）
			manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

			Log.i("System.out" , "GPS取得！！");
		}
		super.onResume();
	}
*/

	//コンストラクタ
	public Gps(Context context)
	{
		Log.i("System.out" , "GPSコンストラクタ");

		mContext = context;

		// GPSサービス取得
		manager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

		if(manager == null)
		{
			Log.i("System.out" , "マネージャーnull");
		}
		else
		{
			Log.i("System.out" , "マネージャーOK");
		}
	}

	//GPS取得
	public void getGps()
	{
		Log.i("System.out" , "GPS");
		if(manager != null)
		{
			Log.i("System.out" , "取得");
			//位置情報取得
			//引数（GPSを利用する, 取得時間最少時間, 最少距離, ？？？）
			manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		}
	}

	//位置情報更新を停止
	public void stopGps()
	{
		Log.i("System.out" , "GPS更新を停止");
		if(manager != null)
		{
			//位置情報取得が不要になった時
			manager.removeUpdates(this);
		}
	}


	//LocationManagerが取得されると呼び出される（位置情報取得時）起動直後
	@Override
	public void onLocationChanged(Location location)
	{
		Log.i("System.out" , "LocationManager取得");
		//MainActivityのststic変数変更
		//緯度
		latitude = (float)location.getLatitude();

		//経度取得
		longitude = (float)location.getLongitude();

		//標高取得
		altitude = (float)location.getAltitude();
	}

	//LocationManagerが有効になった場合に呼び出される（位置情報有効）待機中
	@Override
	public void onProviderDisabled(String provider)
	{

	}

	//LocationManagerが無効になった場合に呼び出される（位置情報無効）電波届かない時
	@Override
	public void onProviderEnabled(String provider)
	{

	}

	//LocationManagerが更新されると呼び出される（位置情報変更）移動したとき
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{

	}

	//緯度取得
	public float GetLatitude()
	{
		return latitude;
	}

	//経度取得
	public float GetLongitude()
	{
		return longitude;
	}

	//標高取得
	public float GetAltitude()
	{
		return altitude;
	}
}