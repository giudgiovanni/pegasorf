package rf.pegaso.db.tabelle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;

public class DettaglioArticolo extends Component{

	private static final long serialVersionUID = 1L;
	private DBManager dbm;
	private Image imgdefault;
	//campi del db
	private int idArticolo;
	private String descrizione;
	private String descRidotta;
	private Blob imgBlob;
	private Blob imgNDispBlob;
	
	public DettaglioArticolo(){
		dbm = DBManager.getIstanceSingleton();
	}
	
	public static void main (String [] args){
		DettaglioArticolo de = new DettaglioArticolo();
		de.creaImg("resource/nuovo.png", "test");
		de.setDescrizione("Immagine di prova");
		de.setIdArticolo(9);
		try {
			de.insert();
		} catch (IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void creaImg(String url, String descBreve){
		this.descRidotta = descBreve;
		Image img = Toolkit.getDefaultToolkit().getImage(url);
//		int imageWidth = img.getWidth( this );
//        int imageHeight = img.getHeight( this );
//        //Dimension dim = getSize();
//
//
//        // this does not complete the job, just starts it.
//        // We are notified of progress through our Component ImageObserver interface.
//        Graphics g = img.getGraphics();
//        g.drawImage ( img,                            /* Image to draw */
//                      100, //( dim.width - imageWidth ) / 2,   /* x */
//                      100, //( dim.height - imageHeight ) / 2, /* y */
//                      imageWidth,                       /* width */
//                      imageHeight,                      /* height */
//                      this );           
        img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        //crea img con testo breve
        Image imgText = getImageText(img, descBreve);
        //qui bisogna convertire l'imgText creata in blob per salvarla nel db
        byte[] buffer = null;
        try{
        	BufferedImage bufImage = new BufferedImage(imgText.getWidth(null), imgText.getHeight(null), BufferedImage.TYPE_INT_RGB);
        	ByteArrayOutputStream bos = new ByteArrayOutputStream();
        	ImageIO.write(bufImage, "png", bos);
        	buffer = bos.toByteArray();
        	//imgBlob = new Blob();
        	imgBlob.setBytes(0, buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //crea img non disponibile e salva in blob
        //byte[] buffer = null;
        try{
        	BufferedImage bufImage = new BufferedImage(imgText.getWidth(null), imgText.getHeight(null), BufferedImage.TYPE_INT_RGB);
        	ByteArrayOutputStream bos = new ByteArrayOutputStream();
        	ImageIO.write(bufImage, "png", bos);
        	buffer = bos.toByteArray();
        	imgNDispBlob.setBytes(1, buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	/**
	 * 
	 * @param img
	 * @param text
	 * @return Un'immagine composta dall'immagine passata come parametro con il testo scritto sospra
	 */
	 private Image getImageText(Image img, String text) {
         
	        //img = getImageSimple(img);
	        
	        BufferedImage imgtext = new BufferedImage(100, 100,  BufferedImage.TYPE_INT_ARGB);
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

//	        Point2D center = new Point2D.Float(imgtext.getWidth() / 2, label.getHeight());
//	        float radius = imgtext.getWidth() / 3;
//	        float[] dist = {0.1f, 1.0f};
//	        Color[] colors = {c2, c1};        
//	        Paint gpaint = new RadialGradientPaint(center, radius, dist, colors);
	        Paint gpaint = new GradientPaint(new Point(0,0), c1, new Point(label.getWidth() / 2, 0), c2, true);
	        
	        g2d.drawImage(img, 0, 0, null);
	        g2d.translate(0, imgtext.getHeight() - label.getHeight());
	        g2d.setPaint(gpaint);            
	        g2d.fillRect(0 , 0, imgtext.getWidth(), label.getHeight());    
	        label.paint(g2d);
	            
	        g2d.dispose();
	        
	        return imgtext;    
	    }
	
//	 private Image createImage(Image img) {
////		 MaskFilter filter = new MaskFilter(Color.WHITE);
////		 ImageProducer prod = new FilteredImageSource(img.getSource(), filter);
////		 img = Toolkit.getDefaultToolkit().createImage(prod);
//
//		 int targetw;
//		 int targeth;
//
//		 double scalex = (double) width / (double) img.getWidth(null);
//		 double scaley = (double) height / (double) img.getHeight(null);
//		 if (scalex < scaley) {
//			 targetw = width;
//			 targeth = (int) (img.getHeight(null) * scalex);
//		 } else {
//			 targetw = (int) (img.getWidth(null) * scaley);
//			 targeth = (int) height;
//		 }
//
//		 int midw = img.getWidth(null);
//		 int midh = img.getHeight(null);
//		 BufferedImage midimg = null;
//		 Graphics2D g2d = null;
//
//		 Image previmg = img;
//		 int prevw = img.getWidth(null);
//		 int prevh = img.getHeight(null);
//
//		 do {
//			 if (midw > targetw) {
//				 midw /= 2;
//				 if (midw < targetw) {
//					 midw = targetw;
//				 }
//			 } else {
//				 midw = targetw;
//			 }
//			 if (midh > targeth) {
//				 midh /= 2;
//				 if (midh < targeth) {
//					 midh = targeth;
//				 }
//			 } else {
//				 midh = targeth;
//			 }
//			 if (midimg == null) {
//				 midimg = new BufferedImage(midw, midh, BufferedImage.TYPE_INT_ARGB);
//				 g2d = midimg.createGraphics();
//				 g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//			 }
//			 g2d.drawImage(previmg, 0, 0, midw, midh, 0, 0, prevw, prevh, null);
//			 prevw = midw;
//			 prevh = midh;
//			 previmg = midimg;
//		 } while (midw != targetw || midh != targeth);
//
//		 g2d.dispose();
//
//		 if (width != midimg.getWidth() || height != midimg.getHeight()) {
//			 midimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//			 int x = (width > targetw) ? (width - targetw) / 2 : 0;
//			 int y = (height > targeth) ? (height - targeth) / 2 : 0;
//			 g2d = midimg.createGraphics();
//			 g2d.drawImage(previmg, x, y, x + targetw, y + targeth,
//					 0, 0, targetw, targeth, null);
//			 g2d.dispose();
//			 previmg = midimg;
//		 } 
//		 return previmg;           
//	 } 
	
	public void caricaDati(int id, boolean disp) throws SQLException {
		try{
			Statement st = null;
			ResultSet rs = null;
			BufferedInputStream is;
			String query = "select * from dettaglio_articolo where iddettaglio=" + id;
			st = dbm.getNewStatement();
			rs = st.executeQuery(query);
			if ( rs.next() ){
				this.descrizione = rs.getString("descrizione");
				this.descRidotta = rs.getString("descrizione_ridotta");
				//this.imgBlob = rs.getBlob("img_originale");
				//this.imgNDispBlob = rs.getBlob("img_non_disp");
				this.idArticolo = id;
			}
			else{
				query = "select * from dettaglio_articolo where iddettaglio=9";
				st = dbm.getNewStatement();
				rs = st.executeQuery(query);
				rs.next();
				this.descrizione = rs.getString("descrizione");
				this.descRidotta = rs.getString("descrizione_ridotta");
				//this.imgBlob = rs.getBlob("img_originale");
				//this.imgNDispBlob = rs.getBlob("img_non_disp");
				this.idArticolo = id;
			}
			if (st != null)
				st.close();
//			if ( disp )
//				is = new BufferedInputStream(imgBlob.getBinaryStream());
//			else
//				is = new BufferedInputStream(imgNDispBlob.getBinaryStream());
//			imgdefault = ImageIO.read(is);
				
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	
	/**
	 * Questo metodo cancella un dettaglioArticolo dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 * 
	 * @param idArticolo
	 *            è il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int delete(int idArticolo) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idArticolo <= 0)
			throw new IDNonValido();
		delete = "DELETE FROM dettaglio_articolo WHERE iddettaglio=" + idArticolo;

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
	
	/**
	 * Il seguente metodo inserisce un nuovo dettaglio nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'inserimento della riga
	 */
	public int insert() throws IDNonValido {

		if (idArticolo <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String insert = "insert into dettaglio_articolo values (?,?,?,?,?)";

		pst = dbm.getNewPreparedStatement(insert);
		try {
			pst.setInt(1, idArticolo);
			pst.setString(2, descrizione);
			pst.setString(3, descRidotta);
			pst.setBlob(4, imgBlob);
			pst.setBlob(5, imgNDispBlob);

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
	public int update() throws IDNonValido {

		if (idArticolo <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE dettaglio_articolo SET iddettaglio=?,"
				+ "descrizione=?, descrizione_ridotta=?, img_originale=?, img_non_disp=? WHERE iddettaglio=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idArticolo);
			pst.setString(2, this.descrizione);
			pst.setString(3, this.descRidotta);
			pst.setBlob(4, this.imgBlob);
			pst.setBlob(5, this.imgNDispBlob);
			pst.setInt(6, idArticolo);

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

	public DBManager getDbm() {
		return dbm;
	}

	public void setDbm(DBManager dbm) {
		this.dbm = dbm;
	}

	public Image getImgdefault() {
		return imgdefault;
	}

	public void setImgdefault(Image imgdefault) {
		this.imgdefault = imgdefault;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescRidotta() {
		return descRidotta;
	}

	public void setDescRidotta(String descRidotta) {
		this.descRidotta = descRidotta;
	}

	public Blob getImgBlob() {
		return imgBlob;
	}

	public void setImgBlob(Blob imgBlob) {
		this.imgBlob = imgBlob;
	}

	public Blob getImgNDispBlob() {
		return imgNDispBlob;
	}

	public void setImgNDispBlob(Blob imgNDispBlob) {
		this.imgNDispBlob = imgNDispBlob;
	}
}
