package anuj.guiAndroidPackage;

//import com.example.android.jetboy.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import anuj.guiAndroidPackage.grid.MyGLSurfaceViewGrid;


public class AndroidMainClass extends Activity implements View.OnClickListener{
    /** Called when the activity is first created. */
    static final private int BACK_ID = Menu.FIRST;
	static final private int CLEAR_ID = Menu.FIRST + 1;
	
	 private MediaPlayer up,a;
	private GLSurfaceView mGLView;
	private MyGLSurfaceView mMyGLView;
	private MyGLSurfaceViewGrid mMyGLViewGrid;
	private Button mButtontryit;
	private Button mButtontournament;
	private Button mButtoncustomize;
	private Button mButtoncontinue;
	private Button mButtonlevel1;
//	private Button mButtonlevel2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       // mGLView = new GLSurfaceView(this);
       // mGLView.setRenderer(new OpenGLRenderer());
        //setContentView(mGLView);
        mMyGLView= new MyGLSurfaceView(this);
        mMyGLViewGrid=new MyGLSurfaceViewGrid(this);
      //  mMyGLView.setRenderer(new OpenGLRenderer());
   //  setContentView(mMyGLView);
        setContentView(R.layout.mainnew);
       // findViewById(R.id.includetryit).setVisibility(View.VISIBLE);;
   // AndroidSmainClass temp = new AndroidSmainClass();
    //temp.onCreate(savedInstanceState);
        // TryitList b = new TryitList();
        //b.onCreate(savedInstanceState);
        
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      //  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
   		//view.setRenderer(new OpenGLRenderer());
   		
   		
        mButtontryit = (Button)findViewById(R.id.Button01);
        mButtontryit.setOnClickListener(this);
        mButtontournament = (Button)findViewById(R.id.Button02);
        mButtontournament.setOnClickListener(this);
        mButtoncustomize = (Button)findViewById(R.id.Button03);
        mButtoncustomize.setOnClickListener(this);
        
        mButtoncontinue = (Button)findViewById(R.id.Button001);
        mButtoncontinue.setOnClickListener(this);
        mButtonlevel1=(Button)findViewById(R.id.Button002);	
        mButtonlevel1.setOnClickListener(this);
        
        up = MediaPlayer.create(this, R.raw.up);
        a = MediaPlayer.create(this, R.raw.a);
    }
  /*
    @Override
    protected void onPause() {
        super.onPause();
        mMyGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyGLView.onResume();
    }
*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       MediaPlayer mp;
       mp=a;  //because .start was showing errror
       switch (keyCode) {
       case KeyEvent.KEYCODE_DPAD_UP:
          mp = up;
          Log.v("manjeet,,,,,,,,,,,,", "anuj,,,,,,,,,,,,,,,,,,,,,");
          break;
       case KeyEvent.KEYCODE_DPAD_DOWN:
       //   mp = down;
          break;
       case KeyEvent.KEYCODE_DPAD_LEFT:
         // mp = left;
          break;
       case KeyEvent.KEYCODE_DPAD_RIGHT:
          //mp = right;
          break;
       case KeyEvent.KEYCODE_DPAD_CENTER:
       case KeyEvent.KEYCODE_ENTER:
          //mp = enter;
          break;
       case KeyEvent.KEYCODE_A:
          mp = a;
          break;
       case KeyEvent.KEYCODE_S:
          //mp = s;
          break;
       case KeyEvent.KEYCODE_D:
          //mp = d;
          break;
       case KeyEvent.KEYCODE_F:
          //mp = f;
          break;
       default:
    	   
          return super.onKeyDown(keyCode, event);
       }
       mp.seekTo(0); 
       mp.start();
       return true;
    }

    
    public void onClick(View v) {
      
        // this is a retry button
         if (mButtontryit.equals(v)) {
        	// setContentView(R.layout.tryit);
        	 
        	 findViewById(R.id.mainmenuscreen).setVisibility(View.INVISIBLE);
        	 findViewById(R.id.includetryit).setVisibility(View.VISIBLE);
         }
         else if(mButtoncontinue.equals(v)) {
          	// setContentView(mGLView);
          	 setContentView(mMyGLView);
          	Log.v("manjeet,,,,,,,,,,,,", "anuj,,,,,,,,,,,,,,,,,,,,,");
          	// setContentView(R.layout.tryit);
         	 //findViewById(R.id.includetryit).setVisibility(View.VISIBLE);
           }
         else if(mButtonlevel1.equals(v)) {
           	// setContentView(mGLView);
           	 setContentView(mMyGLViewGrid);
           	// setContentView(R.layout.tryit);
          	 //findViewById(R.id.includetryit).setVisibility(View.VISIBLE);
            }
         else if (mButtontournament.equals(v)) {
        	// setContentView(mGLView);
        	
        	 setContentView(mMyGLView);
        	// setContentView(R.layout.tryit);
         }
         else if(mButtoncustomize.equals(v)) {
         	// setContentView(mGLView);
         	// setContentView(mMyGLView);
         	// setContentView(R.layout.tryit);
        	 findViewById(R.id.includetryit).setVisibility(View.VISIBLE);
          }
    }
    

   
    
    // Called when your activity's options menu needs to be created.
     
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // We are going to create two menus. Note that we assign them
        // unique integer IDs, labels from our string resources, and
        // given them shortcuts.
        menu.add(0, BACK_ID, 0, R.string.back).setShortcut('0', 'b');
        menu.add(0, CLEAR_ID, 0, R.string.clear).setShortcut('1', 'c');

        return true;
    }

   
     // Called right before your activity's option menu is displayed.
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        // Before showing the menu, we need to decide whether the clear
        // item is enabled depending on whether there is text to clear.
     //   menu.findItem(CLEAR_ID).setVisible(mEditor.getText().length() > 0);

        return true;
    }

    
     //Called when a menu item is selected.
     
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case BACK_ID:
        //	setContentView(R.layout.mainnew);
            
            return true;
        case CLEAR_ID:
        	finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    
}
         
