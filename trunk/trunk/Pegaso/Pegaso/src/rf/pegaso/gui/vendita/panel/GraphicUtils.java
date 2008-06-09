package rf.pegaso.gui.vendita.panel;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * <code>GraphicsUtils</code> class defines common user-interface related
 * utility functions.
 */
public final class GraphicUtils {
    protected final static Component component = new Component() {
    };
    protected final static MediaTracker tracker = new MediaTracker(component);

    private static Hashtable imageCache = new Hashtable();

    private GraphicUtils() {
    }


    public byte[] encode(BufferedImage image) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", out);
        return out.toByteArray();
    }

    public BufferedImage decode(byte[] data) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(data));
    }



    public static ImageIcon createImageIcon(Image image) {
        if (image == null) {
            return null;
        }

        synchronized (tracker) {
            tracker.addImage(image, 0);
            try {
                tracker.waitForID(0, 0);
            } catch (InterruptedException e) {
                System.out.println("INTERRUPTED while loading Image");
            }
            tracker.removeImage(image, 0);
        }

        return new ImageIcon(image);
    }

    /**
     * Loads an {@link Image} named <code>imageName</code> as a resource
     * relative to the Class <code>cls</code>. If the <code>Image</code>
     * can not be loaded, then <code>null</code> is returned. Images loaded
     * here will be added to an internal cache based upon the full {@link URL}
     * to their location. <p/>
     * <em>This method replaces legacy code from JDeveloper 3.x and earlier.</em>
     *
     * @see Class#getResource(String)
     * @see Toolkit#createImage(URL)
     */
    public static Image loadFromResource(String imageName, Class cls) {
        try {
            final URL url = cls.getResource(imageName);
            if (url == null) {
                return null;
            }
            Image image = (Image) imageCache.get(url.toString());
            if (image == null) {
                image = Toolkit.getDefaultToolkit().createImage(url);
                imageCache.put(url.toString(), image);
            }
            return image;
        } catch (Exception e) {

        }
        return null;
    }

    public static Image toImage(BufferedImage bufferedImage) {
        return Toolkit.getDefaultToolkit().createImage(
                bufferedImage.getSource());
    }

    public static BufferedImage convert(Image im) throws InterruptedException,
            IOException {
        load(im);
        BufferedImage bi = new BufferedImage(im.getWidth(null), im
                .getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }

    public static void load(Image image) throws InterruptedException,
            IOException {
        MediaTracker tracker = new MediaTracker(new Label()); // any component
        // will do
        tracker.addImage(image, 0);
        tracker.waitForID(0);
        if (tracker.isErrorID(0))
            throw new IOException("error loading image");
    }

    public static byte[] getBytesFromImage(Image image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(convert(image), "PNG", baos);
        } catch (IOException e) {

        } catch (InterruptedException e) {

        }
        byte[] bytes = baos.toByteArray();

        return bytes;
    }

    /**
     * Returns a scaled down image if the height or width is smaller than the
     * image size.
     *
     * @param icon
     *            the image icon.
     * @param newHeight
     *            the preferred height.
     * @param newWidth
     *            the preferred width.
     * @return the icon.
     */
    public static ImageIcon scaleImageIcon(ImageIcon icon, int newHeight,
            int newWidth) {
        Image img = icon.getImage();
        int height = icon.getIconHeight();
        int width = icon.getIconWidth();

        if (height > newHeight) {
            height = newHeight;
        }

        if (width > newWidth) {
            width = newWidth;
        }
        img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    /**
     *
     *
     *
     *
     *
     *
     *
     * Returns a scaled down image if the height or width is smaller than the
     * image size.
     *
     * @param icon
     *            the image icon.
     * @param newHeight
     *            the preferred height.
     * @param newWidth
     *            the preferred width.
     * @return the icon.
     */
    public static ImageIcon scale(ImageIcon icon, int newHeight, int newWidth) {
        Image img = icon.getImage();
        int height = icon.getIconHeight();
        int width = icon.getIconWidth();
        height = newHeight;
        width = newWidth;
        img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static BufferedImage getBufferedImage(Icon icon) {
        BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon
                .getIconHeight(), BufferedImage.OPAQUE);
        Graphics bg = bi.getGraphics();
        ImageIcon i = (ImageIcon) icon;
        bg.drawImage(i.getImage(), 0, 0, null);
        bg.dispose();
        return bi;
    }
}
