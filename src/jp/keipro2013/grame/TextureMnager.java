package jp.keipro2013.grame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;

public class TextureMnager extends GLSurfaceView
{
	public TextureMnager(Context context)
	{
		super(context);
	}

	//ナビ画像管理
	public Bitmap[] naviTexture = new Bitmap[100];	//画像

	//ナビ画像ID
	public int naviTextureId = 0;

	//ナビ画像名前
	public String naviTextureName = "navi";

	//ナビ座標
	public float[] naviLongitude = new float[100];	//ナビ経度
	public float[] naviLatitude = new float[100];	//ナビ緯度
	public float[] naviAltitude = new float[100];	//ナビ高度

	//ナビ回転
	public float[] naviDirection = new float[100];	//ナビ方位角
	public float[] naviInclination = new float[100];	//ナビ傾斜角
	public float[] naviRotation = new float[100];	//ナビ回転角

	//サーバーから受け取るナビ座標（サーバー通信までは仮で置く）
	public float[] tempNaviLongitude = new float[100];	//ナビ経度
	public float[] tempNaviLatitude = new float[100];	//ナビ緯度
	public float[] tempNaviAltitude = new float[100];	//ナビ高度

	//サーバーから受け取るナビ回転（サーバー通信までは仮で置く）
	public float[] tempNaviDirection = new float[100];	//サーバーからうけとるナビ方位角
	public float[] tempNaviInclination = new float[100];	//サーバーからうけとるナビ傾斜角
	public float[] tempNaviRotation = new float[100];	//サーバーからうけとるナビ回転角



	//パーツ画像管理
	public Bitmap[] partTexture = new Bitmap[10];	//画像

	//パーツ画像ID
	public int partTextureId = 0;

	//パーツ画像名前
	public String partTextureName = "part";

	//パーツ座標
	public float[] partLongitude = new float[10];	//パーツ経度
	public float[] partLatitude = new float[10];	//パーツ緯度
	public float[] partAltitude = new float[10];	//パーツ高度

	//パーツ回転
	public float[] partDirection = new float[10];	//パーツ方位角
	public float[] partInclination = new float[10];	//パーツ傾斜角
	public float[] partRotation = new float[10];	//パーツ回転角

	//サーバーから受け取るパーツ座標（サーバー通信までは仮で置く）
	public float[] tempPartLongitude = new float[10];	//パーツ経度
	public float[] tempPartLatitude = new float[10];	//パーツ緯度
	public float[] tempPartAltitude = new float[10];	//パーツ高度

	//サーバーから受け取るパーツ回転（サーバー通信までは仮で置く）
	public float[] tempPartDirection = new float[10];	//サーバーからうけとるパーツ方位角
	public float[] tempPartInclination = new float[10];	//サーバーからうけとるパーツ傾斜角
	public float[] tempPartRotation = new float[10];	//サーバーからうけとるパーツ回転角



	//メッセージ画像
	public Bitmap messageTexture[] = new Bitmap[1];

	//メッセージ画像ID
	public int messageTextureID = 0;

	//メッセージ画像名前
	public String messageTextureName = "message";

	//メッセージ座標
	public float messageLongitude[] = new float[1];	//メッセージ経度
	public float messageLatitude[] = new float[1];	//メッセージ緯度
	public float messageAltitude[] = new float[1];	//メッセージ高度

	//メッセージ回転
	public float messageDirection[] = new float[1];	//メッセージ方位角
	public float messageInclination[] = new float[1];	//メッセージ傾斜角
	public float messageRotation[] = new float[1];	//メッセージ回転角

	//サーバーから受け取るメッセージ座標（サーバー通信までは仮で置く）
	public float tempMessageLongitude[] = new float[1];	//メッセージ経度
	public float tempMessageLatitude[] = new float[1];	//メッセージ緯度
	public float tempMessageAltitude[] = new float[1];	//メッセージ高度

	//サーバーから受け取るメッセージ回転（サーバー通信までは仮で置く）
	public float tempMessageDirection[] = new float[1];	//サーバーからうけとるメッセージ方位角
	public float tempMessageInclination[] = new float[1];	//サーバーからうけとるメッセージ傾斜角
	public float tempMessageRotation[] = new float[1];	//サーバーからうけとるメッセージ回転角


	//初期化時呼ばれる
	//画像情報保存
	public void onSurfaceCreated()
	{
	//ナビ画像関連
		//サーバー通信できるまで仮の座標を置く
		for(int i = 0; i < 100; i++)
		{
			//ナビ座標
			tempNaviLongitude[i] = 0.0f;
			tempNaviLatitude[i] = 0.0f;
			tempNaviAltitude[i] = 0.0f;

			//ナビ回転
			tempNaviDirection[i] = 0.0f;
			tempNaviInclination[i] = 0.0f;
			tempNaviRotation[i] = 0.0f;
		}
		//ナビ画像管理
		for(naviTextureId = 0; naviTextureId <100 ; naviTextureId++)
		{
			//ナビ画像貼り付け
			TextureBind(naviTexture, naviTextureId, naviTextureName);
			//ナビ画像座標
			CoordinatesMnager(naviTextureId, naviLongitude, tempNaviLongitude, naviLatitude, tempNaviLatitude, naviAltitude, tempNaviAltitude);
			//ナビ画像回転
			RotationMnager(naviTextureId, naviDirection, tempNaviDirection, naviInclination, tempNaviInclination, naviRotation, tempNaviRotation);
		}


	//パーツ画像関連
		//サーバー通信できるまで仮の座標を置く
		for(int j = 0; j < 10; j++)
		{
			//パーツ座標
			tempPartLongitude[j] = 0.0f;
			tempPartLatitude[j] = 0.0f;
			tempPartAltitude[j] = 0.0f;

			//パーツ回転
			tempPartDirection[j] = 0.0f;
			tempPartInclination[j] = 0.0f;
			tempPartRotation[j] = 0.0f;
		}
		//パーツ画像管理
		for(partTextureId = 0; partTextureId <10 ; partTextureId++)
		{
			//パーツ画像貼り付け
			TextureBind(partTexture, partTextureId, partTextureName);
			//パーツ画像座標
			CoordinatesMnager(partTextureId, partLongitude, tempPartLongitude, partLatitude, tempPartLatitude, partAltitude, tempPartAltitude);
			//パーツ画像回転
			RotationMnager(partTextureId, partDirection, tempPartDirection, partInclination, tempPartInclination, partRotation, tempPartRotation);
		}


	//メッセージ画像関連
		//サーバー通信できるまで仮の座標を置く
		//メッセージ座標
		tempMessageLongitude[0] = 0.0f;
		tempMessageLatitude[0] = 0.0f;
		tempMessageAltitude[0] = 0.0f;

		//メッセージ回転
		tempMessageDirection[0] = 0.0f;
		tempMessageInclination[0] = 0.0f;
		tempMessageRotation[0] = 0.0f;

		//メッセージ画像管理
		//メッセージ画像貼り付け
		TextureBind(messageTexture, messageTextureID, messageTextureName);
		//メッセージ画像座標
		CoordinatesMnager(0, messageLongitude, tempMessageLongitude, messageLatitude, tempMessageLatitude, messageAltitude, tempMessageAltitude);
		//メッセージ画像回転
		RotationMnager(0, messageDirection, tempMessageDirection, messageInclination, tempMessageInclination, messageRotation, tempMessageRotation);
	}


	//画像を貼り付け
	public void TextureBind(Bitmap[] texture, int textureId, String textureName)
	{
		// 画像ファイルをロードし、テクスチャにバインド
		naviTexture[0] = BitmapFactory.decodeResource(getResources(), R.drawable.aaa);
		naviTexture[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bbb);

		//ファイル名、クラス名、パッケージ名
		//画像名は「画像の種類+Texture+ID」
		textureId = getResources().getIdentifier(textureName + "Texture" + naviTextureId, "drawable", "com.example.graffitimessage");
		texture[naviTextureId] = BitmapFactory.decodeResource(getResources(), naviTextureId);

	}

	//画像の座標管理
	public void CoordinatesMnager(int textureId, float longitude[], float tempLongitude[], float latitude[], float tempLatitude[], float altitude[], float tempAltitude[])
	{
		longitude[textureId] = tempLongitude[textureId];	//経度
		latitude[textureId] = tempLatitude[textureId];	//緯度
		altitude[textureId] = tempAltitude[textureId];	//高度
	}

	//画像の座標管理
	public void RotationMnager(int textureId, float direction[], float tempDirection[], float inclination[], float tempInclination[], float rotation[], float tempRotation[])
	{
		direction[textureId] =tempDirection[textureId];	//方位角
		inclination[textureId] = tempInclination[textureId];	//傾斜角
		rotation[textureId] = tempRotation[textureId];	//回転各
	}
}
