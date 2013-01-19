package anuj.guiAndroidPackage.game;

import javax.microedition.khronos.opengles.GL10;
import anuj.guiAndroidPackage.geometry.Cube;
import anuj.guiAndroidPackage.geometry.Square;
//#include "DrawObj.h"

public class DrawObj {
private GL10 gl;
private Cube cube ;
private Square square;
int Level_g;
boolean isFloorAnimated;

private float[][] animateSpeed;

public int minny=3;;
private int minnx;
private float[][] transFloorZ;
private float maxTransFloorZ;
private int floorTexID;
int drawFloor_countAnimate;
private float drawFloor_dz;
private float halfSide;
private float halfTileDepth;

public DrawObj(GL10 gl)
{	
	animateSpeed = new float[gameVar.noTiles_v][gameVar.noTiles_h];
	// Initialize our square. 
	square = new Square();
	// Initialize our cube
	cube = new Cube();
	
	this.gl=gl;
	Level_g = 0;
	isFloorAnimated = false;
	drawFloor_countAnimate=0;
	drawFloor_dz=halfSide - halfTileDepth;;
}

int getLevel(){return Level_g;};
void setLevel(int level){ Level_g = level;};
boolean getIsFloorAnimated(){return isFloorAnimated;};
void setIsFloorAnimated(boolean animatedFlag){ isFloorAnimated = animatedFlag;};


public void updateArenaVar()
{
	float minspeed = 1000.0f;
	transFloorZ = new float[gameVar.noTiles_v][gameVar.noTiles_h];
	for(int ny =gameVar.noTiles_v-2; ny >= 1; ny--)
	{  
		for(int nx= 1; nx<=gameVar.noTiles_h-2; nx++)
		{
			animateSpeed[ny][nx]=(nx+(gameVar.noTiles_v-ny)+5)*gameVar.animationFloorSpeedFactor;

			// min speed of tiles which is not to be created ,it's depth in is not updated in "drawFloor" funtion
			//so calculation based on that minspeed will not give right result
			if(animateSpeed[ny][nx] < minspeed && gameVar.arena[Level_g][ny][nx] != 0)
			{
				minspeed = animateSpeed[ny][nx];
				minny = ny;
				minnx = nx;
			}
			transFloorZ[ny][nx] = -maxTransFloorZ;
		}
	}
}
private void initDrawSetup()
{
//	displaylist->floorDisplay(front,top,bottom,rightL,leftL);
//	displaylist->cubeDisplay(cube);	
	updateArenaVar();
}

public void initTextureSetup()
{
	gl.glEnable(GL10.GL_TEXTURE_2D);
/*	if( (floorTexID = texload->loadTexture("resource/floor.bmp")) == -1)
	{
		cout<<"texture2 load failed\n";
		// MessageBox(NULL,"Image file: texture not found","Zetadeck",MB_OK | MB_ICONERROR);
		// exit (0);
	}
	if( (cubeTexID  = texload->loadTexture("resource/block.bmp"))== -1)
	{
		cout<<"texture load failed\n";
		//MessageBox(NULL,"Image file: texture not found","Zetadeck",MB_OK | MB_ICONERROR);
		//exit (0);
	}
	if( (floorSideTexID = texload->loadTexture("resource/greenSide.bmp")) == -1)
	{
		cout<<"texture2 load failed\n";
		// MessageBox(NULL,"Image file: texture not found","Zetadeck",MB_OK | MB_ICONERROR);
		//   exit (0);
	}
	if( (destTextID = texload->loadTexture("resource/blueHole.bmp")) == -1)
	{
		cout<<"texture2 load failed\n";
		// MessageBox(NULL,"Image file: texture not found","Zetadeck",MB_OK | MB_ICONERROR);
		// exit (0);
	}
*/
}

public void initDraw()
{
	//displaylist = new displayLists();
	//texload     = new TexLoader();
	initDrawSetup();
	//initTextureSetup();
}

public void drawCube(int textID)
{
	//gl.glBindTexture(GL10.GL_TEXTURE_2D, textID);
	//glCallList(cube);
	cube.draw(gl);
	
}

/**************************************************************
1. the tiles are drawn  as it is shown in array arena[][][] for any arena designer. at simple way it will drawn apposite..
as what is in left bottom in array

*************************************************************/

public void drawFloor()
{
	
	for(int ny =0,nytrans=gameVar.noTiles_v-1; ny<gameVar.noTiles_v && nytrans>=0; ny++,nytrans--)
	{   
		for(int nx=0; nx<gameVar.noTiles_h; nx++)
		{
			if(gameVar.arena[Level_g][ny][nx] == 0 || gameVar.arena[Level_g][ny][nx] == 3)
				continue;

			gl.glPushMatrix();
			float transX = gameVar.side*(float)nx;
			float transY = gameVar.side*(float)nytrans;

			/*if(!isFloorAnimated)
			{
				transFloorZ[ny][nx] += animateSpeed[ny][nx];
				if(transFloorZ[ny][nx] >= drawFloor_dz)
					transFloorZ[ny][nx] = drawFloor_dz;

				gl.glRotatef(transFloorZ[ny][nx]*20,0,0,1);
				//  check for tiles with lowest speed to reach it's position    
				if(transFloorZ[minny][minnx] >= drawFloor_dz)
				{
					drawFloor_dz++;
					//isFloorAnimated made true in second time when  tiles with lower speed reaches 0
					// it probably hit in middle of loop, so update for other tiles will left over.
					if(drawFloor_dz >1)
						isFloorAnimated = true;
				}
			}*/
		//	gl.glBindTexture( GL10.GL_TEXTURE_2D, floorTexID);
	//	gl.glTranslatef(transX, transY, 0);//transFloorZ[ny][nx]);  //Error:transFloor=Null?
			gl.glTranslatef(transX, transY,transFloorZ[ny][nx]);
	//	square.draw(gl);
			cube.draw(gl);

			// drawing with texture for destination point, where block need to be put
	/*		if(arena[Level_g][ny][nx] == 3)
			{   
				glBindTexture( GL_TEXTURE_2D, destTextID);
				glTranslatef(0, 0, -side/2);
				glCallList(leftL);
				glCallList(rightL);
				glCallList(bottom);
				glCallList(top);
			}

			else
			{
				glBindTexture( GL_TEXTURE_2D, floorTexID);
				glTranslatef(0, 0, -side/2);
				glCallList(front);
				// drawing for other sides of tile where there is no tile
				glBindTexture( GL_TEXTURE_2D, floorSideTexID);
				if( nx > 0 && arena[Level_g][ny][nx -1] == 0 )
				{
					//leftdraw
					//glColor4f(0.0, 0.0, 1.0, 1.0);
					glCallList(leftL);
				}
				if( nx < noTiles_h-1 && arena[Level_g][ny][nx+1] == 0)
				{
					//rightdraw
					//glColor4f(0.0, 0.0, 1.0, 1.0);
					glCallList(rightL);
				}
				if( ny > 0 && arena[Level_g][ny-1][nx] == 0)
				{
					//topdraw
					//glColor4f(0.0, 0.0, 1.0, 1.0);
					glCallList(top);
				}
				if( ny < noTiles_v-1 && arena[Level_g][ny+1][nx] == 0)
				{
					//bottomdraw
					//glColor4f(0.0, 0.0, 1.0, 1.0);
					glCallList(bottom);
				}

			}
			*/
			gl.glPopMatrix();
		}
	}
}



}//end of class DrawObj