package anuj.guiAndroidPackage.game;


public class gameVar {
	
	public static final int NOCLICK=0;
	public static final int STARTANIMATION=1;
	public static final int UP=2;
	public static final int DOWN=3;
	public static final int RIGHT=4;
	public static final int LEFT=5;
	public static final int FAIL=6;
	public static final int FINISH=7;
	public static final int LEVEL_COMPLETE=8;
	
	public static final float side=0.4f;
	
	public static final int noTiles_v = 7;
	public static final int noTiles_h = 16;
	public static final int MaxLevel = 5;
	public static float waitTime = 0.01f; // wait time in animation
	public static float animationFloorSpeedFactor = 0.25f;
	//initial position of block may not need to be changed, but it is defined here for flexibity
	public static final int[][] arenaInitPos = {{2, 4}, {5, 1}, {5, 2}, {3, 4}, {3, 4}};
 
	public static int [][][]arena =
	{
		{
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,0,0,1,1,1,1,1,0,0,0,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,1,1,1,1,0,0,0,0,1,1,1,3,1,0},
			
			{0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0},
			{0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		},

		
		{
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0},
			{0,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0},
			
			{0,1,1,1,1,1,0,0,0,0,1,1,3,1,0,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		},
		
		{
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,1,1,1,0,0,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,3,1,1,1,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0},
			
			{0,0,0,1,1,1,1,1,1,1,1,0,0,0,1,0},
			{0,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		},
		
		{
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,1,1,1,0,0,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,3,1,1,1,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0},
			
			{0,0,0,1,1,1,1,1,1,1,1,0,0,0,1,0},
			{0,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		},
		
		
		{
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,1,1,1,0,0,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,3,1,1,1,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0},
			
			{0,0,0,1,1,1,1,1,1,1,1,0,0,0,1,0},
			{0,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		}
	};

		
	
	
}


