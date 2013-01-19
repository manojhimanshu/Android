package anuj.guiAndroidPackage;


import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;
import anuj.guiAndroidPackage.game.DrawObj;
import anuj.guiAndroidPackage.game.classBlock;
import anuj.guiAndroidPackage.game.gameVar;
import anuj.guiAndroidPackage.geometry.Cube;
import anuj.guiAndroidPackage.geometry.Square;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
	private final Context context;
	//private GL10 gl;
	private final Cube cube ;
	private Square square;
	private float angle = 0;
	 float randomX=0;
	 float randomY=0;
	 private int waitflag=0;
	private float randomZ=0;
	private Random mRandom = new Random();
	private boolean SEE_THRU=false;
	private DrawObj drawObj;
	private classBlock firstBlock;
	private int click=gameVar.STARTANIMATION;
	
	public OpenGLRenderer(Context context) {
		this.context = context;
		// Initialize our square. 
		square = new Square();
		// Initialize our cube
		cube = new Cube();

		//firstBlock=new classBlock(gl,drawObj);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition
	 * .khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Set the background color to black ( rgba ).
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		// Enable Smooth Shading, default not really needed.
		gl.glShadeModel(GL10.GL_SMOOTH);
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);
		 float lightAmbient[] = new float[] { 0.2f, 0.2f, 0.2f, 1 };
	      float lightDiffuse[] = new float[] { 1, 1, 1, 1 };
	      float[] lightPos = new float[] { 1, 1, 1, 1 };
	      gl.glEnable(GL10.GL_LIGHTING);
	      gl.glEnable(GL10.GL_LIGHT0);
	      gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
	      gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0);
	      gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
	      

	      
	      // What is the cube made of?
	      float matAmbient[] = new float[] { 1, 1, 1, 1 };
	      float matDiffuse[] = new float[] { 1, 1, 1, 1 };
	      gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT,
	            matAmbient, 0);
	      gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE,
	            matDiffuse, 0);
	      

	      
	      // Set up any OpenGL options we need
	      gl.glEnable(GL10.GL_DEPTH_TEST); 
	      gl.glDepthFunc(GL10.GL_LEQUAL);
	      gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

	      // Optional: disable dither to boost performance
	      // gl.glDisable(GL10.GL_DITHER);
	      

	      
	      // ...
	      if (SEE_THRU) {
	         gl.glDisable(GL10.GL_DEPTH_TEST);
	         gl.glEnable(GL10.GL_BLEND);
	         gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
	      }
	      
	      
	      // Enable textures
	      gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	      gl.glEnable(GL10.GL_TEXTURE_2D);
	      gl.glEnable (GL10.GL_BLEND); 
	      // Load the cube's texture from a bitmap
	      int sid=4;
			drawObj=new DrawObj(gl);
			drawObj.initDraw();
			 sid = drawObj.minny;
			 Log.v("manjee   side ........ ","X= "+sid);
			 firstBlock=new classBlock(gl,drawObj);
			firstBlock.updateLevel();
	      
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.microedition.
	 * khronos.opengles.GL10)
	 */
	public void onDrawFrame(GL10 gl) {
		gl.glClearColor(mRed, mGreen, mBlue, 1.0f);
		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// Replace the current matrix with the identity matrix
		gl.glLoadIdentity();

		// Translates 10 units into the screen.
		gl.glTranslatef(-3.0f, -2.0f, -10.0f); 
		//gl.glScalef(10f, 10f, 10f);
		gl.glRotatef(-77, 1,0, 0);
		gl.glRotatef(7, 0,0, 1);
	//	gl.glRotatef(22, 0,1, 0);
	//	if(waitflag==0)
		{
	//	randomX=mRandom.nextInt(11);
	//	randomY=mRandom.nextInt(11);
		}
		//gl.glTranslatef(randomX,randomY, 0);
	//loadTexture(gl, context, R.drawable.background_b);
		
		//gl.glLoadIdentity();
		//gl.glTranslatef(1.5f, 1.0f, -1f); 
		
		
		loadTexture(gl, context, R.drawable.berry);
		//cube.draw(gl);
		//drawObj.drawCube(1);
		
		drawObj.drawFloor();
		loadTexture(gl, context, R.drawable.icon);
		click=firstBlock.placeBlock(click);
	//	square.draw(gl);
	//	gl.glBlendFunc (GL10.GL_ONE, GL10.GL_ONE);
	//	waitflag++;
	//	if(waitflag==60)
		//	waitflag=0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition
	 * .khronos.opengles.GL10, int, int)
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Sets the current view port to the new size.
		gl.glViewport(0, 0, width, height);
		Log.v("manjee..onsrfc cng ","X= "+width+"Y= "+height);
		//Log.v("manjeet,,,,,,,,,,,, ","X= "+width+"Y= "+getHeight());
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
				100.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// Reset the modelview matrix
		gl.glLoadIdentity();
	}
	
	  void loadTexture(GL10 gl, Context context, int resource) {
	      Bitmap bmp = BitmapFactory.decodeResource(
	            context.getResources(), resource);
	      GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
	      gl.glTexParameterx(GL10.GL_TEXTURE_2D,
	            GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
	      gl.glTexParameterx(GL10.GL_TEXTURE_2D,
	            GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
	      bmp.recycle();
	   }
	 
	 
	  public void setColor(float r, float g, float b) {
	        mRed = r;
	        mGreen = g;
	        mBlue = b;
	    }

	    private float mRed=1;
	    private float mGreen=2;
	    private float mBlue=111;

}
