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

// OpenGLのViewクラス
class ViewTest extends GLSurfaceView implements Renderer
{
	//コンストラクタ
	public ViewTest(Context context)
	{
		super(context);

		//画像管理
		tm = new TextureMnager(context);
		//GPS情報
		gps = new Gps(context);
		//傾きセンサー情報
		is = new InclinationSensor(context);

		//透明設定
		this.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		this.setRenderer(this);
		this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
	}

	//カメラ移動
	float cameraX = 0.0f;
	float cameraZ = 1.0f;
	float cameraY = 0.0f;
	int change = 0;

	double testDress = 0;
	double testRadians = Math.toRadians(testDress);

	//方位角
	float directionRadian = 0.0f;
	//傾斜角
	float inclinationRadian = 0.0f;
	//回転各
	float rotationRadian = 0.0f;

	// 頂点座標
	private float apexs[] = new float[] { -1f, -1f, 1f, -1f, -1f, 1f, 1f, 1f, };
	// 頂点座標バッファ
	private FloatBuffer apexBuff;
	// 頂点テクスチャ
	private float coords[] = new float[] { 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, }; // 上下反転
	// 頂点テクスチャバッファ
	private FloatBuffer coordsBuff;
	private int[] textures = new int[1];
	//private int textureId = 0;

	//ナビ画像ID
	public int naviTextureId;

	//画像管理
	public Bitmap[] bm = new Bitmap[100];

	//GPSクラス
	Gps gps;
	//TextureMnagerクラス
	TextureMnager tm;
	//InclinationSensorクラス
	InclinationSensor is;

	//初期化時に呼ばれる。テクスチャ作成
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		Log.i("System.out" , "ViewTestクラスonSurfaceCreated成功！！");

		// バッファの生成
		apexBuff = makeFloatBuffer(apexs);
		coordsBuff = makeFloatBuffer(coords);

		// テクスチャ管理番号割り当て
		gl.glDeleteTextures(1, textures, 0);
		gl.glGenTextures(1, textures, 0);
		//textureId = textures[0];

		// テクスチャ管理番号バインド
		//gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);

		// 画像ファイルをロードし、テクスチャにバインド
		bm[0] = BitmapFactory.decodeResource(getResources(), R.drawable.a);
		bm[1] = BitmapFactory.decodeResource(getResources(), R.drawable.ccc);

		//テクスチャを画像サイズに合わせる
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		 //シェーディングの指定
        //gl.glShadeModel(GL10.GL_SMOOTH);

		//オブジェクトを放棄し、メモリをシステムに返す
		//bm[0].recycle();
		//bm[1].recycle();
	}


	//画面切り替え時に呼ばれる
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		Log.i("System.out" , "AR呼び出し");

		//ポートビュー座標変換
		gl.glViewport(0, 0, width, height);

		//投資投影の行列を指定
		gl.glMatrixMode(GL10.GL_PROJECTION);

		//選択されている行列に単位行列をロード
		gl.glLoadIdentity();

		//視界を決定する（GL10のインスタンス, 縦の視野角, 縦に対する横の視野角の倍率, 一番近いZ位置, 一番遠いZ位置）
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 1.0f, 100.0f);

		// 背面塗り潰し色の指定
		//gl.glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
	}

	//描画のたびに呼ばれる
	@Override
	public void onDrawFrame(GL10 gl)
	{
		// 画面クリア
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glPushMatrix();

		//GPS取得
		//gps.getGps();
		//傾きセンサー
		is.getInclination();

		//ラジアンに変更
		//testRadians = Math.toRadians(testDress);
		//方位角
		directionRadian = (float)Math.toRadians(is.GetDirection());
		//傾斜角
		inclinationRadian = (float)Math.toRadians(is.GetInclination());
		//回転各
		rotationRadian = (float)Math.toRadians(is.GetRotation());

		// カメラの位置と見る方向
		//（X視点、Y視点、Z視点、X注視点、Y注視点、Z注視点、Xに対して上方向量、Yに対して上方向量、Zに対して上方向量）

		//GLU.gluLookAt(gl, (-1) * cameraX + (float)Math.cos(directionRadian), (-1) * (cameraY + (float)Math.sin(inclinationRadian)), (-1) * (cameraZ + (float)Math.sin(directionRadian)), cameraX + (float)Math.cos(directionRadian), (cameraY + (float)Math.sin(inclinationRadian)), cameraZ + (float)Math.sin(directionRadian), 0, 1, 0);

		Log.i("System.out" , "視点X(" + 0 + "),視点Y(" + 0 + "),視点Z(" + 0 + "),注視点(x,y,z)=("
				+ ( cameraX + (float)Math.cos(directionRadian)) + ","
				+ ( cameraY + (float)Math.sin(inclinationRadian)) + ","
				+ ( cameraZ + (float)Math.sin(directionRadian)) + ")");


		Log.i("System.out" , "緯度(" + gps.GetLatitude() + "),高度(" + gps.GetAltitude() + "),経度(" + gps.GetLongitude());

		GLU.gluLookAt(gl, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f , -0.2f, 0, 1, 0);

		//Log.i("System.out" , "視点(x,y,z) = (" + 0.0f + ","+ 0.0f +  "," + 0.5f + ")　注視点(x,y,z) = (" + 0.0f + "," + (float)Math.sin(inclinationRadian) + "," + 0.5f + ")" );

		gl.glPushMatrix();

		//Log.i("System.out", "緯度" + gps.GetLatitude() + "経度" +gps.GetLongitude() + "高度" + gps.GetAltitude());
		//Log.i("System.out", "方位角" + is.GetDirection() + "傾斜角" + is.GetInclination() + "高度" + "回転角" + is.GetRotation());

		// 頂点配列の指定
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, apexBuff);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		// テクスチャ配列の指定
		//gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, coordsBuff);

		// テクスチャーの有効化
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		//そのうち消す
		float x2=0,y2=0,z2=0;
		float xr2=0,yr2=0,zr2=0;

		//画像表示
		for(naviTextureId = 0; naviTextureId < 1;naviTextureId++)
		{
			//そのうち消す
			if(naviTextureId == 0){x2=0.0f; y2=0.0f; z2=0.0f; xr2=0.0f; yr2=0.0f; zr2=0.0f;}
			if(naviTextureId == 1){x2=-1.0f; y2=0.0f; z2=0.0f; xr2=32.0f; yr2=22.0f; zr2=54.0f;}

			//配置
			//DrawText(gl,naviTextureId,x2,y2,z2,xr2,yr2,zr2);

			//学際用？配置
			DrawText(gl,naviTextureId,x2,y2,z2,is.GetInclination(),is.GetDirection(),is.GetRotation());

			//そのうち
			//DrawTexT(GL,naviTextureId, サーバーX, サーバーY, サーバーZ, サーバー傾斜角, サーバー方位角, サーバー回転角)
			//にしたい

			//画像貼り付け
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bm[naviTextureId], 0);
		}

		// テクスチャーの無効化
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glPopMatrix();

		//オブジェクトを放棄し、メモリをシステムに返す
		//bm[0].recycle();
		//bm[1].recycle();
	}

	//画像配置（変数で仮においてあるが、最終的に位置情報をサーバーからもらう）
	public void DrawText(GL10 gl,int naviTextureId, float xx, float yy, float zz, float xrote, float yrote, float zlote)
	{
		//表示位置
		gl.glTranslatef(xx, yy, zz);

		//Log.i("System.out" ,"画像位置(前)x=" + xx + "y=" + yy + "z=" + zz);

		//X軸回転
		gl.glRotatef(xrote, 1.0f, 0.0f, 0.0f);
		//Y軸回転
		gl.glRotatef(yrote, 0.0f, 1.0f, 0.0f);
		//Z軸回転
		gl.glRotatef(zlote, 0.0f, 0.0f, 1.0f);

		// テクスチャ配置	(プリミティブの種類、スタート地点、必要な頂点の数)
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		//Log.i("System.out" ,"（描画前）ID=" + naviTextureId + "x=" + xx + "y=" + yy + "z=" + zz);

		//Z軸回転
		gl.glRotatef((-1)*zlote, 0.0f, 0.0f, 1.0f);
		//Y軸回転
		gl.glRotatef((-1)*yrote, 0.0f, 1.0f, 0.0f);
		//X軸回転
		gl.glRotatef((-1)*xrote, 1.0f, 0.0f, 0.0f);

		//表示位置を原点に戻す
		gl.glTranslatef((-1)*xx, (-1)*yy, (-1)*zz);
		//Log.i("System.out" ,"画像位置(後)x ="+ (-1)*xx + "y=" + (-1)*yy + "z=" + (-1)*zz);
	}

	//頂点の配列をバッファーに変換するメソッド
	private static FloatBuffer makeFloatBuffer(float[] values)
	{
		Log.i("System.out" , "バッファー");
		ByteBuffer bb = ByteBuffer.allocateDirect(values.length * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(values);
		fb.position(0);
		return fb;
	}
}