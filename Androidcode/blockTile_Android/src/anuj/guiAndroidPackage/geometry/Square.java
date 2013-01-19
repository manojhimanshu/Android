package anuj.guiAndroidPackage.geometry;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Square {
	// Our vertices.
	private int one = 65536;
	private int side = 65536;
    private int half = side / 2;
	private float vertices[] = {
		      -half,  half, 0.0f,  // 0, Top Left
		      -half, -half, 0.0f,  // 1, Bottom Left
		       half, -half, 0.0f,  // 2, Bottom Right
		       half,  half, 0.0f,  // 3, Top Right
		};
	
	// The order we like to connect them.
	private short[] indices = { 0, 1, 2, 0, 2, 3 };
//	final short one=1;
	//The texture coordinate
	private float texCoords[] = {
			0, one,
			one, one,
			0, 0,
			one, 0};
	
	// Our vertex buffer.
	private FloatBuffer vertexBuffer;

	// Our index buffer.
	private ShortBuffer indexBuffer;
	
	// Our texture buffr.
	 private FloatBuffer mTextureBuffer;
	
	public Square() {
		// a float is 4 bytes, therefore we multiply the number if 
		// vertices with 4.
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		// short is 2 bytes, therefore we multiply the number if 
		// vertices with 2.
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
		
		
		 ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4);
	      tbb.order(ByteOrder.nativeOrder());
	      mTextureBuffer = tbb.asFloatBuffer();
	      mTextureBuffer.put(texCoords);
	      mTextureBuffer.position(0);
	      
	      
	}
	
	/**
	 * This function draws our square on screen.
	 * @param gl
	 */
	public void draw(GL10 gl) {
		// Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CCW);
		// Enable face culling.
		gl.glEnable(GL10.GL_CULL_FACE);
		// What faces to remove with the face culling.
		gl.glCullFace(GL10.GL_BACK);
		gl.glColor4f(1, 3, 1, 1);
	
		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, vertexBuffer);
	    gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, mTextureBuffer);
		
	//	gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, 
		//		GL10.GL_UNSIGNED_SHORT, indexBuffer);
		

	      gl.glNormal3f(0, 0, 1);
	      gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

	    
		// Disable the vertices buffer.
/////////////gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// Disable face culling.
		gl.glDisable(GL10.GL_CULL_FACE);
	}
	
}
