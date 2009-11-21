package rf.pegaso.gui.utility;


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
	
	

}
