package anuj.guiAndroidPackage.grid;
/**
 * 
 */
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
  

/**
 * @author Anuj
 *
 */
public class MyGLSurfaceViewGrid extends GLSurfaceView{
	OpenGLRendererGrid mRenderer;

	public MyGLSurfaceViewGrid (Context context) {
        super(context);
        mRenderer = new OpenGLRendererGrid(context);
      setRenderer(mRenderer);
    }
	
	 public boolean onTouchEvent(final MotionEvent event) {
	        queueEvent(new Runnable(){
	            public void run() 
	            {
	              
	            if( (event.getX()/ getWidth()-mRenderer.randomX)<0.1 && (event.getX() / getWidth()-mRenderer.randomX)<0.1 )
	            {
	              mRenderer.setColor(event.getX() / getWidth(),
                     event.getY() / getHeight(), 1.0f);
	            }
	            //System.out.println("X= "+event.getX()+" Y= "+event.getY());
	            
	            }
	            });
	            return true;
	        }
}
