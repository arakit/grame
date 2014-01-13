package jp.keipro2013.grame;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;
import android.view.SurfaceHolder;

class ViewTest extends GLSurfaceView implements Renderer{
	public ViewTest(Context context){
		super(context);

		tm = new TextureMnager(context);
		gps = new Gps(context);
		is = new InclinationSensor(context);

		this.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		this.setRenderer(this);
		this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
	}
	

	float cameraX = 0.0f;
	float cameraZ = 1.0f;
	float cameraY = 0.0f;
	int change = 0;

	double testDress = 0;
	double testRadians = Math.toRadians(testDress);

	float directionRadian = 0.0f;
	float inclinationRadian = 0.0f;
	float rotationRadian = 0.0f;

	private float apexs[] = new float[] { -1f, -1f, 1f, -1f, -1f, 1f, 1f, 1f, };
	private FloatBuffer apexBuff;
	private float coords[] = new float[] { 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, }; // 上下反転
	private FloatBuffer coordsBuff;
	private int[] textures = new int[1];

	public int naviTextureId;

	public Bitmap[] bm = new Bitmap[100];

	Gps gps;
	TextureMnager tm;
	InclinationSensor is;

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config){

		apexBuff = makeFloatBuffer(apexs);
		coordsBuff = makeFloatBuffer(coords);

		gl.glDeleteTextures(1, textures, 0);
		gl.glGenTextures(1, textures, 0);

		bm[0] = BitmapFactory.decodeResource(getResources(), R.drawable.a);
		bm[1] = BitmapFactory.decodeResource(getResources(), R.drawable.ccc);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height){
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 1.0f, 100.0f);
	}
	
	@Override
	public void onDrawFrame(GL10 gl){
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glPushMatrix();

		is.getInclination();

		directionRadian = (float)Math.toRadians(is.GetDirection());
		inclinationRadian = (float)Math.toRadians(is.GetInclination());
		rotationRadian = (float)Math.toRadians(is.GetRotation());

		GLU.gluLookAt(gl, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f , -0.2f, 0, 1, 0);

		gl.glPushMatrix();

		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, apexBuff);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, coordsBuff);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		float x2=0,y2=0,z2=0;
		float xr2=0,yr2=0,zr2=0;

		for(naviTextureId = 0; naviTextureId < 1;naviTextureId++){
			if(naviTextureId == 0){x2=0.0f; y2=0.0f; z2=0.0f; xr2=0.0f; yr2=0.0f; zr2=0.0f;}
			if(naviTextureId == 1){x2=-1.0f; y2=0.0f; z2=0.0f; xr2=32.0f; yr2=22.0f; zr2=54.0f;}
			
			DrawText(gl,naviTextureId,x2,y2,z2,is.GetInclination(),is.GetDirection(),is.GetRotation());

			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bm[naviTextureId], 0);
		}

		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glPopMatrix();

	}
	public void DrawText(GL10 gl,int naviTextureId, float xx, float yy, float zz, float xrote, float yrote, float zlote){
		gl.glTranslatef(xx, yy, zz);

		gl.glRotatef(xrote, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(yrote, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(zlote, 0.0f, 0.0f, 1.0f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		gl.glRotatef((-1)*zlote, 0.0f, 0.0f, 1.0f);
		gl.glRotatef((-1)*yrote, 0.0f, 1.0f, 0.0f);
		gl.glRotatef((-1)*xrote, 1.0f, 0.0f, 0.0f);

		gl.glTranslatef((-1)*xx, (-1)*yy, (-1)*zz);
	}

	private static FloatBuffer makeFloatBuffer(float[] values){
		ByteBuffer bb = ByteBuffer.allocateDirect(values.length * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(values);
		fb.position(0);
		return fb;
	}
}