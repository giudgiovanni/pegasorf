package rf.pegaso.gui.utility;

import it.infolabs.hibernate.ImmagineArticolo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class UtilityImage {

	public static ImageIcon resizeImage( ImageIcon fromStream,
			int widthConstraint, int heightConstraint) {

		int imgWidth = fromStream.getIconWidth();
		int imgHeight = fromStream.getIconHeight();
		ImageIcon adjustedImg;

		if ( imgWidth > widthConstraint | imgHeight >
		heightConstraint ) {
			if ( imgWidth > imgHeight )	{
				// Create a resizing ratio.
				double ratio = (double) imgWidth / (double)
				widthConstraint;
				int newHeight = (int) ( (double) imgHeight / ratio );

				// use Image.getScaledInstance( w, h, 
				// constant), where constant is a constant
				// pulled from the Image class indicating how
				// process the image; smooth image, fast
				// processing, etc.
				adjustedImg = new ImageIcon(
						fromStream.getImage().getScaledInstance(
								widthConstraint, newHeight,
								Image.SCALE_SMOOTH )
				);
			}
			else {
				// Create a resizing ratio.
				double ratio = (double) imgHeight / (double)
				heightConstraint;
				int newWidth = (int) ( (double) imgWidth / ratio );
				adjustedImg = new ImageIcon(
						fromStream.getImage().getScaledInstance( newWidth,
								heightConstraint, Image.SCALE_SMOOTH )
				);
			}

			// return the adjusted ImageIcon object.
			return adjustedImg;
		}
		else {
			// Assure the resources from the adjustedImg object
			// are released and then return the original ImageIcon
			// object if the submitted image's width and height
			// already fell within the given constraints.
			adjustedImg = null;
			return fromStream;    
		}
	}
	
	public static void loadImageFromURL(String strUrl, ImmagineArticolo imgArticolo, JFrame padre){
	    int intPos;

	    //guess file extension
	    intPos = strUrl.lastIndexOf(".");
	    if (intPos >= 0){
	       imgArticolo.setEstensione(strUrl.substring(intPos + 1));
	    }else{
	        //assign default jpg extension
	    	 imgArticolo.setEstensione("jpg");
	    }
	    try {

	        //load the image from the Internet
	        ImageIcon objImageIcon = new ImageIcon(strUrl);

	        //wait the loading of the image
	        MediaTracker objMediaTracker = new MediaTracker(padre);
	        objMediaTracker.addImage(objImageIcon.getImage(), 0);
	        objMediaTracker.waitForID(0, 5000);

	        //convert the image
	        BufferedImage objBI = new BufferedImage(objImageIcon.getIconWidth(), objImageIcon.getIconHeight(),
	                BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2 = objBI.createGraphics();
	        g2.drawImage(objImageIcon.getImage(), 0, 0, null);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(objBI,  imgArticolo.getEstensione(), baos);

	        imgArticolo.setFile(baos.toByteArray());

	    } catch (MalformedURLException ex) {
//	        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
//	        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (InterruptedException ex) {
//	        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

}
