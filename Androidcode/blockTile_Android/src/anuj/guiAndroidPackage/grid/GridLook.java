package anuj.guiAndroidPackage.grid;

public class GridLook {
	static float screenWidth;
	static float screenHeight;
	static int boxNoWidth;
	static int boxNoHeight;
	static float squareSide;
	public GridLook()
	{
		
	}
	
	public static void setScreenDim(float sW,float sH)
	{
		screenWidth=sW; screenHeight=sH;
		//HCF(sW,sH);
		
	}
	//we may need to put set get for each varible , if we could not get any static type idea. static=?
}
