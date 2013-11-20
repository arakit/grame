package jp.keipro2013.grame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;

public class TextureMnager extends GLSurfaceView{
	public TextureMnager(Context context)
	{
		super(context);
	}
	
	public Bitmap[] naviTexture = new Bitmap[100];

	public int naviTextureId = 0;

	public String naviTextureName = "navi";

	public float[] naviLongitude = new float[100];
	public float[] naviLatitude = new float[100];
	public float[] naviAltitude = new float[100];

	public float[] naviDirection = new float[100];
	public float[] naviInclination = new float[100];
	public float[] naviRotation = new float[100];

	public float[] tempNaviLongitude = new float[100];
	public float[] tempNaviLatitude = new float[100];
	public float[] tempNaviAltitude = new float[100];
	
	public float[] tempNaviDirection = new float[100];
	public float[] tempNaviInclination = new float[100];
	public float[] tempNaviRotation = new float[100];

	public Bitmap[] partTexture = new Bitmap[10];

	public int partTextureId = 0;

	public String partTextureName = "part";

	public float[] partLongitude = new float[10];	
	public float[] partLatitude = new float[10];
	public float[] partAltitude = new float[10];

	public float[] partDirection = new float[10];
	public float[] partInclination = new float[10];
	public float[] partRotation = new float[10];

	public float[] tempPartLongitude = new float[10];
	public float[] tempPartLatitude = new float[10];
	public float[] tempPartAltitude = new float[10];

	public float[] tempPartDirection = new float[10];
	public float[] tempPartInclination = new float[10];
	public float[] tempPartRotation = new float[10];

	public Bitmap messageTexture[] = new Bitmap[1];

	public int messageTextureID = 0;

	public String messageTextureName = "message";

	public float messageLongitude[] = new float[1];
	public float messageLatitude[] = new float[1];
	public float messageAltitude[] = new float[1];

	public float messageDirection[] = new float[1];
	public float messageInclination[] = new float[1];
	public float messageRotation[] = new float[1];

	public float tempMessageLongitude[] = new float[1];
	public float tempMessageLatitude[] = new float[1];
	public float tempMessageAltitude[] = new float[1];
	
	public float tempMessageDirection[] = new float[1];
	public float tempMessageInclination[] = new float[1];
	public float tempMessageRotation[] = new float[1];

	public void onSurfaceCreated(){
		for(int i = 0; i < 100; i++){
			tempNaviLongitude[i] = 0.0f;
			tempNaviLatitude[i] = 0.0f;
			tempNaviAltitude[i] = 0.0f;

			tempNaviDirection[i] = 0.0f;
			tempNaviInclination[i] = 0.0f;
			tempNaviRotation[i] = 0.0f;
		}
		
		for(naviTextureId = 0; naviTextureId <100 ; naviTextureId++){
			TextureBind(naviTexture, naviTextureId, naviTextureName);
			CoordinatesMnager(naviTextureId, naviLongitude, tempNaviLongitude, naviLatitude, tempNaviLatitude, naviAltitude, tempNaviAltitude);
			RotationMnager(naviTextureId, naviDirection, tempNaviDirection, naviInclination, tempNaviInclination, naviRotation, tempNaviRotation);
		}

		for(int j = 0; j < 10; j++){
			tempPartLongitude[j] = 0.0f;
			tempPartLatitude[j] = 0.0f;
			tempPartAltitude[j] = 0.0f;

			tempPartDirection[j] = 0.0f;
			tempPartInclination[j] = 0.0f;
			tempPartRotation[j] = 0.0f;
		}
		for(partTextureId = 0; partTextureId <10 ; partTextureId++){
			TextureBind(partTexture, partTextureId, partTextureName);
			CoordinatesMnager(partTextureId, partLongitude, tempPartLongitude, partLatitude, tempPartLatitude, partAltitude, tempPartAltitude);
			RotationMnager(partTextureId, partDirection, tempPartDirection, partInclination, tempPartInclination, partRotation, tempPartRotation);
		}
		
		tempMessageLongitude[0] = 0.0f;
		tempMessageLatitude[0] = 0.0f;
		tempMessageAltitude[0] = 0.0f;

		tempMessageDirection[0] = 0.0f;
		tempMessageInclination[0] = 0.0f;
		tempMessageRotation[0] = 0.0f;

		TextureBind(messageTexture, messageTextureID, messageTextureName);
		CoordinatesMnager(0, messageLongitude, tempMessageLongitude, messageLatitude, tempMessageLatitude, messageAltitude, tempMessageAltitude);
		RotationMnager(0, messageDirection, tempMessageDirection, messageInclination, tempMessageInclination, messageRotation, tempMessageRotation);
	}

	public void TextureBind(Bitmap[] texture, int textureId, String textureName){
		naviTexture[0] = BitmapFactory.decodeResource(getResources(), R.drawable.aaa);
		naviTexture[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bbb);

		textureId = getResources().getIdentifier(textureName + "Texture" + naviTextureId, "drawable", "com.example.graffitimessage");
		texture[naviTextureId] = BitmapFactory.decodeResource(getResources(), naviTextureId);

	}

	public void CoordinatesMnager(int textureId, float longitude[], float tempLongitude[], float latitude[], float tempLatitude[], float altitude[], float tempAltitude[]){
		longitude[textureId] = tempLongitude[textureId];
		latitude[textureId] = tempLatitude[textureId];
		altitude[textureId] = tempAltitude[textureId];
	}

	public void RotationMnager(int textureId, float direction[], float tempDirection[], float inclination[], float tempInclination[], float rotation[], float tempRotation[]){
		direction[textureId] =tempDirection[textureId];
		inclination[textureId] = tempInclination[textureId];
		rotation[textureId] = tempRotation[textureId];
	}
}
