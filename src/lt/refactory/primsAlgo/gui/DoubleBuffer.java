package lt.refactory.primsAlgo.gui;

import java.awt.*;

public class DoubleBuffer extends javax.swing.JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	class variables
	private int bufferWidth;
	private int bufferHeight;
	private Image bufferImage;
	private Graphics bufferGraphics;

	public DoubleBuffer(){
		super();
	}

	public void update(Graphics g){
		paint(g);
	}

	public void paint(Graphics g){
		//	checks the buffersize with the current panelsize
		//	or initialises the image with the first paint
		if(	bufferWidth!=getSize().width ||
			bufferHeight!=getSize().height ||
			bufferImage==null ||
			bufferGraphics==null)
				resetBuffer();

		if(bufferGraphics!=null){
			//	this clears the offscreen image, not the onscreen one
			bufferGraphics.clearRect(0,0,bufferWidth,bufferHeight);

			//	calls the paintbuffer method with the offscreen graphics as a param
			paintBuffer(bufferGraphics);

			//	we finally paint the offscreen image onto the onscreen image
			g.drawImage(bufferImage,0,0,this);
		}
	}

	private void resetBuffer(){
		// always keep track of the image size
		bufferWidth=getSize().width;
		bufferHeight=getSize().height;

		//	clean up the previous image
		if(bufferGraphics!=null){
			bufferGraphics.dispose();
			bufferGraphics=null;
		}
		if(bufferImage!=null){
			bufferImage.flush();
			bufferImage=null;
		}
		System.gc();

		//	create the new image with the size of the panel
		bufferImage=createImage(bufferWidth,bufferHeight);
		bufferGraphics=bufferImage.getGraphics();
	}

	public void paintBuffer(Graphics g){
		//	in classes extended from this one, add something to paint here!
		//	always remember, g is the offscreen graphics
	}
}