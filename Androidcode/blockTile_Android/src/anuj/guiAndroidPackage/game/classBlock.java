package anuj.guiAndroidPackage.game;

import javax.microedition.khronos.opengles.GL10;

public class classBlock {
	public enum enumOrientation {
		alongX,alongY,alongZ
	}
	private GL10 gl;
	float iStartFall;
	enumOrientation orientation;
	float theta;
	float deltaDheta;
	//int cube;
	DrawObj drawobj;
	//SoundAL = new soundal();
	int click;
	int cubeTexID;
	float iTransX;
	float iTransY;
    int fallenum;
	private float iDropLength;
private int rotDetha;
private int temproty;
private float transzy;
private boolean isAnimated;
private int temprot;
private float transz;
int ilevel;


public classBlock(GL10 gl,DrawObj tdrawobj)//,SoundAL tsoundal)
{
	this.gl=gl;
	orientation=enumOrientation.alongZ;
	iStartFall=15;
	theta=0;
	deltaDheta=10;
	click=gameVar.STARTANIMATION;
	drawobj = tdrawobj;
	ilevel = drawobj.getLevel();
	iTransX = gameVar.arenaInitPos[ilevel][0]*gameVar.side;
	iTransY = gameVar.arenaInitPos[ilevel][1]*gameVar.side;
	iDropLength = 0.0f;
	//soundal=tsoundal;
	fallenum = 0;
	
	rotDetha = 90;
	temproty = 90;
	transzy = 0.0f;
	// to reduce the so many check every time
    isAnimated = false;
    temprot = 90;
    transz=0.0f;
}

public void updateLevel()
{
	iDropLength = 0.0f;
	ilevel = drawobj.getLevel();
	iTransX = gameVar.arenaInitPos[ilevel][0]*gameVar.side;
	iTransY = gameVar.arenaInitPos[ilevel][1]*gameVar.side;
	orientation = enumOrientation.alongZ;
}


public void drawBlock(enumOrientation localBlockState)
{
	gl.glPushMatrix();
	switch(localBlockState)
	{
	case alongX :
		gl.glTranslatef(0,0,gameVar.side/2);
		gl.glScalef(2,1,1);
		break;
	case alongY  :
		gl.glTranslatef(0,0,gameVar.side/2);
		gl.glScalef(1,2,1);
		break;
	case alongZ :
		gl.glTranslatef(0,0,gameVar.side);
		gl.glScalef(1,1,2);
		break;
	default :
		break;
	}
	drawCube();
	gl.glPopMatrix();
}


void checkBlock()
{
	float tx = iTransX/gameVar.side;
	float ty = iTransY/gameVar.side;
	if(tx-(int)tx == 0 && ty-(int)ty== 0 && gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)ty][(int)tx] == 3)
	{
		click=gameVar.FINISH;
		//alSourcePlay(soundal->Source[2]); 
	}
	else
		click = gameVar.NOCLICK;
}

void animateDropBlock()
{
	if(iDropLength >= -gameVar.side*2)
	{
		gl.glTranslatef(iTransX,iTransY,iDropLength);
		iDropLength -= .5;
		drawBlock(enumOrientation.alongZ);
		click=gameVar.FINISH;
	}
	else
	{
		click = gameVar.LEVEL_COMPLETE;
	}  
}


void onClickUpdown(enumOrientation currentOrientation,enumOrientation finalOrientation,int upORdown,float animateRotFrom,float finalTransTo)
{
	int upPlus_downMinus;
	if(upORdown==gameVar.UP) {upPlus_downMinus=1;} else {upPlus_downMinus=-1;}

	if(!isAnimated)
	{
		float nx = iTransX/gameVar.side;
		float ny = (iTransY+upPlus_downMinus*finalTransTo)/gameVar.side;
		int ly =  (int)(ny-0.5);
		int hy  = (int)(ny+0.5);
		if( (ny == (int)ny && gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)ny][(int)nx] == 0) /*z free or y free*/
			|| (ly < 0 && gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)hy][(int)nx] == 0)
			|| (hy > gameVar.noTiles_v-1 && gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)ly][(int)nx] == 0) )
		{
			rotDetha = 410;
			fallenum = 1;
			isAnimated = true;
		}
		else if(ny != (int)ny && (gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)hy][(int)nx]==0 || gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)ly][(int)nx]==0))/*?*/
		{
			rotDetha = 410;
			fallenum = 2;
			isAnimated = true;
		}
	}

	if(theta<rotDetha)
	{
		gl.glPushMatrix();
		gl.glTranslatef(iTransX,iTransY,0);/*till this axis willl be at centre of oldbase position of block*/
		if(fallenum ==0)
			temproty = 90;
		else
			temproty = 70;
		if(theta > temproty )
		{
		//	alSourcePlay(soundal->Source[3]);
			gl.glTranslatef(0,upPlus_downMinus*animateRotFrom,0);
			transzy=transzy-1;
			gl.glTranslatef(0,0,transzy);
			gl.glRotatef(-upPlus_downMinus*theta,1,0,0);
			gl.glTranslatef(0,-upPlus_downMinus*animateRotFrom,0);
		}

		else if(theta <= temproty )
		{
			gl.glTranslatef(0,upPlus_downMinus*animateRotFrom,0);
			gl.glRotatef(-upPlus_downMinus*theta,1,0,0);
			gl.glTranslatef(0,-upPlus_downMinus*animateRotFrom,0);	
		}
		drawBlock(currentOrientation);
		gl.glPopMatrix();
		theta+=deltaDheta;
	}

	else if(theta>=rotDetha)
	{
		rotDetha = 90;
		temproty = 90;
		transzy = 0.0f;
		theta=0;
		isAnimated = false;
		if(fallenum != 0)
		{
			fallenum = -1;
			click =gameVar.LEVEL_COMPLETE;
		}
		else
		{
		//	alSourcePlay(soundal->Source[1]); 
			iTransY += upPlus_downMinus*finalTransTo;
			gl.glTranslatef(iTransX,iTransY,0);
			drawBlock(finalOrientation);
			orientation=finalOrientation;
			click=gameVar.NOCLICK;
			checkBlock();
		}
	}
}


void onClickRightLeft(enumOrientation currentOrientation,enumOrientation finalOrientation,int rightORleft,float animateRotFrom,float finalTransTo)
{
	int rightPlus_leftMinus=0;
	if(rightORleft==gameVar.RIGHT) {rightPlus_leftMinus=1;} else {rightPlus_leftMinus=-1;}
	
     // to reduce the so many check every time
	if(!isAnimated)
	{
		float nx = (iTransX+rightPlus_leftMinus*finalTransTo)/gameVar.side;
		float ny = iTransY/gameVar.side;
		int lx =  (int)(nx-0.5);
		int hx  = (int)(nx+0.5);
		if( (nx == (int)nx) && (gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)ny][(int)nx] == 0) /*z free or y free*/
			|| lx < 0 && gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)ny][hx] == 0
			|| hx > gameVar.noTiles_h-1 && gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)ny][lx] == 0)
		{
			rotDetha = 410;
			fallenum = 1;
			isAnimated = true;
		}
		else if(nx != (int)nx && (gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)ny][hx]==0 || gameVar.arena[ilevel][gameVar.noTiles_v-1-(int)ny][lx]==0))
		{
			rotDetha = 410;
			fallenum = 2;
			isAnimated = true;
		}
	}

	if(theta<rotDetha)/*if it would be falling ,only then it will keep on entering in this loop even after completion of change of position*/
	{    
		gl.glPushMatrix();
		gl.glTranslatef(iTransX,iTransY,0);/*till this axis willl be at centre of oldbase position of block*/
		if(fallenum ==0)
			temprot = 90;
		else
			temprot = 70;
		float dx=0;
		int dang = 0;
		if(fallenum ==2)
			dx = -0;
		if(theta > temprot)
		{
			//alSourcePlay(soundal->Source[3]);
			/**only when animation of falling failng is dne*/
			if(fallenum ==2)
				dang = -10;
			gl.glTranslatef(rightPlus_leftMinus*(animateRotFrom+dx),0,0);
			gl.glTranslatef(0,0,transz--);
			gl.glRotatef(rightPlus_leftMinus*(theta-dang),0,1,0);
			gl.glTranslatef(-rightPlus_leftMinus*(animateRotFrom-dx),0,0);
		}
		if(theta <= temprot)
		{
			gl.glTranslatef(rightPlus_leftMinus*animateRotFrom,0,0);
			gl.glRotatef(rightPlus_leftMinus*theta,0,1,0);
			gl.glTranslatef(-rightPlus_leftMinus*animateRotFrom,0,0);
		}

		drawBlock(currentOrientation);
		gl.glPopMatrix();
		theta+=deltaDheta;
	}

	else if(theta>=rotDetha)
	{
		isAnimated = false;
		rotDetha = 90;
		temprot = 90;
		transz = 0.0f;
		theta=0;
		if(fallenum != 0)
		{
			fallenum = -1;
			click =gameVar.LEVEL_COMPLETE;
		}
		/*in case of non falling , final position after rotation animation*/
		else
		{
			iTransX += rightPlus_leftMinus*finalTransTo;
			gl.glTranslatef(iTransX,iTransY,0);
			drawBlock(finalOrientation);
			orientation=finalOrientation;
			//alSourcePlay(soundal->Source[1]); 
			click=gameVar.NOCLICK;
			checkBlock();
		}
	}
}

public int placeBlock(int tclick)
{
	click=tclick;
	switch(click)
	{
	case gameVar.STARTANIMATION:
		if(iStartFall>=-0.0)
		{
			gl.glTranslatef(iTransX,iTransY,iStartFall--);
		}
		else
		{
			gl.glTranslatef(iTransX,iTransY,0);
			click=gameVar.NOCLICK;
		}
		drawBlock(enumOrientation.alongZ);			
		break;
	case gameVar.NOCLICK:
		switch(orientation)
		{
		case alongX:
			gl.glTranslatef(iTransX,iTransY,0);
			drawBlock(enumOrientation.alongX);  break;
		case alongY:
			gl.glTranslatef(iTransX,iTransY,0);
			drawBlock(enumOrientation.alongY); break;
		case alongZ:
			gl.glTranslatef(iTransX,iTransY,0);
			drawBlock(enumOrientation.alongZ); break;
		default: break;
		}
		break;
	case gameVar.UP:  
	case gameVar.DOWN:
		switch(orientation)
		{
		case alongX:
			onClickUpdown(enumOrientation.alongX,enumOrientation.alongX,click,gameVar.side/2,gameVar.side);
			break;
		case alongY:
			onClickUpdown(enumOrientation.alongY,enumOrientation.alongZ,click,gameVar.side,3*gameVar.side/2);
			break;
		case alongZ:
			onClickUpdown(enumOrientation.alongZ,enumOrientation.alongY,click,gameVar.side/2,3*gameVar.side/2);
			break;
		default: break;
		}
		break;

	case gameVar.RIGHT:  
	case gameVar.LEFT:
		switch(orientation)
		{
		case alongX:
			onClickRightLeft(enumOrientation.alongX,enumOrientation.alongZ,click,gameVar.side,3*gameVar.side/2);
			break;
		case alongY:
			onClickRightLeft(enumOrientation.alongY,enumOrientation.alongY,click,gameVar.side/2,gameVar.side);
			break;
		case alongZ:
			onClickRightLeft(enumOrientation.alongZ,enumOrientation.alongX,click,gameVar.side/2,3*gameVar.side/2);
			break;
		default: break;
		}
		break;
		//case FAIL:
		//	animateFallBlock();
		//	break;
	case gameVar.FINISH:
		animateDropBlock();
		break;
	default: break;
	}/*end of switch "click"*/
	return click;
}


void drawCube()
{
	drawobj.drawCube(1);
}



}//end of classBlock