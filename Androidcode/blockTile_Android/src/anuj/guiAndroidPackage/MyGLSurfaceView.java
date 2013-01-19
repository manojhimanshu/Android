/**
 * 
 */
package anuj.guiAndroidPackage;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author Anuj
 *
 */
public class MyGLSurfaceView extends GLSurfaceView{
	OpenGLRenderer mRenderer;
	public MyGLSurfaceView (Context context) {
        super(context);
        mRenderer = new OpenGLRenderer(context);
      setRenderer(mRenderer);
    }
	
	 public boolean onTouchEvent(final MotionEvent event) {
	        queueEvent(new Runnable(){
	            public void run() 
	            {
	              
	            //if( (event.getX()/ getWidth()-mRenderer.randomX)<0.1 && (event.getX() / getWidth()-mRenderer.randomX)<0.1 )
	            {
	            	  Log.v("manjeet,,inside if....s","X= "+event.getX()+"Y= "+event.getY());
	              mRenderer.setColor(event.getX() / getWidth(),
                     event.getY() / getHeight(), 1.0f);
	            }
	            Log.v("manjeet,,,,,,,,,,,, ","X= "+event.getX()+"Y= "+event.getY());
	            Log.v("manjeet,,,,,,,,,,,, ","widthtotX= "+getWidth()+"totY= "+getHeight());
	            //System.out.println("X= "+event.getX()+" Y= "+event.getY());
	            
	            }
	            });
	            return true;
	        }
}
