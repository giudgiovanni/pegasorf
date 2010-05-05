package rf.pegaso.db.tabelle;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;

public class ImmagineArticolo extends Component{

	private static final long serialVersionUID = 1L;
	private DBManager dbm;
	private byte [] file;
	private String nome;
	private String estenzione;
	private Articolo articolo;
	
	public ImmagineArticolo(Articolo articolo){
		dbm = DBManager.getIstanceSingleton();
		this.articolo = articolo;
	}
	
	public void caricaDati(){
		try{
			Statement st = null;
			ResultSet rs = null;
			String query = "select * from immagine_articolo where articolo =" + articolo.getIdArticolo();
			st = dbm.getNewStatement();
			rs = st.executeQuery(query);
			if ( rs.next() ){
				this.nome = rs.getString("nome");
				this.file = rs.getBytes("file");
			}
			else{
				query = "select * from immagine_articolo where id = 0";
				st = dbm.getNewStatement();
				rs = st.executeQuery(query);
				rs.next();
				this.nome = rs.getString("nome");
				this.file = rs.getBytes("file");
			}
			if (st != null)
				st.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean inserisci() throws IDNonValido{
		if (articolo == null)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String insert = "insert into immagine_articolo values (?,?,?,?,?)";

		pst = dbm.getNewPreparedStatement(insert);
		try {
			pst.setInt(1, dbm.getNewID("immagine_articolo", "id"));
			pst.setString(2, nome);
			pst.setString(3, estenzione);
			pst.setBytes(4, file);
			pst.setInt(5, articolo.getIdArticolo());

			ok = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			ok = -1;
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				ok = -1;
			}
		}
		dbm.notifyDBStateChange();
		if ( ok < 0 )
			return false;
		return true;
	}
	
	public boolean cancella(int idImmagineArticolo) throws IDNonValido{
		String delete = "";
		Statement st = dbm.getNewStatement();
		int ok = 0;
		if (idImmagineArticolo <= 0)
			throw new IDNonValido();
		delete = "DELETE FROM immagine_articolo WHERE id=" + idImmagineArticolo;

		try {
			ok = st.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
			ok = -1;
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
				ok = -1;
			}
		}
		dbm.notifyDBStateChange();
		if ( ok < 0 )
			return false;
		return true;
	}
	
	public boolean aggiorna(){
		return true;
	}
	
	public void loadImageFromURL(String strUrl){
	    int intPos;
	    String strFileExt;

	    //guess file extension
	    intPos = strUrl.lastIndexOf(".");
	    if (intPos >= 0){
	       estenzione = strUrl.substring(intPos + 1);
	    }else{
	        //assign default jpg extension
	    	estenzione = "jpg";
	    }
	    try {

	        //load the image from the Internet
	        ImageIcon objImageIcon = new ImageIcon(strUrl);

	        //wait the loading of the image
	        MediaTracker objMediaTracker = new MediaTracker(this);
	        objMediaTracker.addImage(objImageIcon.getImage(), 0);
	        objMediaTracker.waitForID(0, 5000);

	        //convert the image
	        BufferedImage objBI = new BufferedImage(objImageIcon.getIconWidth(), objImageIcon.getIconHeight(),
	                BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2 = objBI.createGraphics();
	        g2.drawImage(objImageIcon.getImage(), 0, 0, null);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(objBI, estenzione, baos);

	        file = baos.toByteArray();

	    } catch (MalformedURLException ex) {
//	        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
//	        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (InterruptedException ex) {
//	        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstenzione() {
		return estenzione;
	}

	public void setEstenzione(String estenzione) {
		this.estenzione = estenzione;
	} 
}
