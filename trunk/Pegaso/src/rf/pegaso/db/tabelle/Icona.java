/**
 * 
 */
package rf.pegaso.db.tabelle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;

/**
 * @author Hunter
 * 
 */
public class Icona {

	private DBManager dbm;
	private int idArticolo;
	private String url;
	private String descrizione;
	private int width;
	private int height;
	private Blob blob;
	private Image imgdefault;

	public Icona() {
		this.dbm=DBManager.getIstanceSingleton(); 
	}
	
	public Icona(int width, int height){
		this(width, height, null);
    }
	
    public Icona(int width, int height, Image imgdef) {
        this.width = width;
        this.height = height;
        if (imgdef == null) {
            imgdefault = null;
        } else {
            imgdefault = createImage(imgdef);
        }       
	}

	/**
	 * @param idUm2
	 * @throws SQLException
	 */
	public void caricaDati(int id) throws SQLException {
		try{
			Statement st = null;
			ResultSet rs = null;
			BufferedInputStream is;
			String query = "select * from icone where idarticolo=" + id;
			st = dbm.getNewStatement();
			rs = st.executeQuery(query);
			if ( rs.next() ){
				this.url = rs.getString("url");
				this.blob = rs.getBlob("blob");
				this.descrizione = rs.getString("descrizione");
				this.height = rs.getInt("hight");
				this.width = rs.getInt("width");
				this.idArticolo = id;
			}
			else{
				query = "select * from icone where idarticolo=0";
				st = dbm.getNewStatement();
				rs = st.executeQuery(query);
				rs.next();
				this.url = rs.getString("url");
				this.blob = rs.getBlob("blob");
				this.descrizione = rs.getString("descrizione");
				this.height = rs.getInt("hight");
				this.width = rs.getInt("width");
				this.idArticolo = id;
			}
			if (st != null)
				st.close();
			if( blob != null ){
				is = new BufferedInputStream(blob.getBinaryStream());
				imgdefault = ImageIO.read(is);
			}
			else if (!(url.equals("") || url.equals(null)) ){
				//verificare eventuale errore caricamento img
				imgdefault = ImageIO.read(getClass().getClassLoader().getResourceAsStream(url));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * Questo metodo cancella una icona dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 * 
	 * @param idArticolo
	 *            è il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int deleteIcona(int idArticolo) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idArticolo <= 0)
			throw new IDNonValido();
		delete = "DELETE FROM icona WHERE idarticolo=" + idArticolo;

		try {
			cancellati = st.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbm.notifyDBStateChange();
		return cancellati;
	}

	public DBManager getDBManager() {
		return dbm;
	}

	/**
	 * Il seguente metodo inserisce una nuova icona nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idCausale è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertIcona() throws IDNonValido {

		idArticolo = dbm.getNewID("icona", "idarticolo");
		if (idArticolo <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into icone values (?,?,?,?,?,?)";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idArticolo);
			pst.setString(2, url);
			pst.setBlob(3, blob);
			pst.setString(4, descrizione);
			pst.setInt(5, width);
			pst.setInt(6, height);

			ok = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbm.notifyDBStateChange();
		return ok;
	}

	public void setDBManager(DBManager dbm) {
		this.dbm = dbm;
	}

	/**
	 * Il seguente metodo aggiorna l'icona nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idCausale è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int updateIcona() throws IDNonValido {

		if (idArticolo <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE icona SET idarticolo=?,"
				+ "url=?, blob=?, descrizione=?, width=?, hight=? WHERE idarticolo=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idArticolo);
			pst.setString(2, url);
			pst.setBlob(3, this.blob);
			pst.setString(4, descrizione);
			pst.setInt(5, width);
			pst.setInt(6, height);
			pst.setInt(7, idArticolo);

			ok = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbm.notifyDBStateChange();
		return ok;
	}
	
	public Image getImageSimple(Image img) {
		   
        if (img == null) {
            return imgdefault;
        } else {
            return createImage(img);
        }     
    }      
    
    public Image getImageText(Image img, String text) {
                
        img = getImageSimple(img);
        
        BufferedImage imgtext = new BufferedImage(img.getWidth(null), img.getHeight(null),  BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imgtext.createGraphics();
                
        // The text        
        JLabel label = new JLabel();
        label.setOpaque(false);
        label.setText(text);
        //label.setText("<html><center>Line1<br>Line2");
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);            
        Dimension d = label.getPreferredSize();
        label.setBounds(0, 0, imgtext.getWidth(), d.height);  
        
        // The background
        Color c1 = new Color(0xff, 0xff, 0xff, 0x40);
        Color c2 = new Color(0xff, 0xff, 0xff, 0xd0);

//        Point2D center = new Point2D.Float(imgtext.getWidth() / 2, label.getHeight());
//        float radius = imgtext.getWidth() / 3;
//        float[] dist = {0.1f, 1.0f};
//        Color[] colors = {c2, c1};        
//        Paint gpaint = new RadialGradientPaint(center, radius, dist, colors);
        Paint gpaint = new GradientPaint(new Point(0,0), c1, new Point(label.getWidth() / 2, 0), c2, true);
        
        g2d.drawImage(img, 0, 0, null);
        g2d.translate(0, imgtext.getHeight() - label.getHeight());
        g2d.setPaint(gpaint);            
        g2d.fillRect(0 , 0, imgtext.getWidth(), label.getHeight());    
        label.paint(g2d);
            
        g2d.dispose();
        
        return imgtext;    
    }
    
    private Image createImage(Image img) {
//            MaskFilter filter = new MaskFilter(Color.WHITE);
//            ImageProducer prod = new FilteredImageSource(img.getSource(), filter);
//            img = Toolkit.getDefaultToolkit().createImage(prod);
            
        int targetw;
        int targeth;

        double scalex = (double) width / (double) img.getWidth(null);
        double scaley = (double) height / (double) img.getHeight(null);
        if (scalex < scaley) {
            targetw = width;
            targeth = (int) (img.getHeight(null) * scalex);
        } else {
            targetw = (int) (img.getWidth(null) * scaley);
            targeth = (int) height;
        }

        int midw = img.getWidth(null);
        int midh = img.getHeight(null);
        BufferedImage midimg = null;
        Graphics2D g2d = null;

        Image previmg = img;
        int prevw = img.getWidth(null);
        int prevh = img.getHeight(null);

        do {
            if (midw > targetw) {
                midw /= 2;
                if (midw < targetw) {
                    midw = targetw;
                }
            } else {
                midw = targetw;
            }
            if (midh > targeth) {
                midh /= 2;
                if (midh < targeth) {
                    midh = targeth;
                }
            } else {
                midh = targeth;
            }
            if (midimg == null) {
                midimg = new BufferedImage(midw, midh, BufferedImage.TYPE_INT_ARGB);
                g2d = midimg.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            }
            g2d.drawImage(previmg, 0, 0, midw, midh, 0, 0, prevw, prevh, null);
            prevw = midw;
            prevh = midh;
            previmg = midimg;
        } while (midw != targetw || midh != targeth);

        g2d.dispose();

        if (width != midimg.getWidth() || height != midimg.getHeight()) {
            midimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            int x = (width > targetw) ? (width - targetw) / 2 : 0;
            int y = (height > targeth) ? (height - targeth) / 2 : 0;
            g2d = midimg.createGraphics();
            g2d.drawImage(previmg, x, y, x + targetw, y + targeth,
                                   0, 0, targetw, targeth, null);
            g2d.dispose();
            previmg = midimg;
        } 
        return previmg;           
    } 


	public int getIdArticolo() {
		return idArticolo;
	}


	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHight() {
		return height;
	}


	public void setHight(int hight) {
		this.height = hight;
	}


	public Blob getBlob() {
		return blob;
	}


	public void setBlob(Blob blob) {
		this.blob = blob;
	}

	public Image getImgdefault() {
		return imgdefault;
	}

	public void setImgdefault(Image imgdefault) {
		this.imgdefault = imgdefault;
	}
}
