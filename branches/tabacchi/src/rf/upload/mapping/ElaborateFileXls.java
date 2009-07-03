package rf.upload.mapping;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xml.sax.SAXException;

import rf.pegaso.db.tabelle.Articolo;
import rf.utility.db.DBManager;

public class ElaborateFileXls {
	
	private DBManager dbm;
	
	public ElaborateFileXls(){
		dbm = DBManager.getIstanceSingleton();
	}
	
	public void loadContingent(File xlsFile) throws IOException, SAXException, Exception {

		XlsReader<MapTariffari> con = new XlsReader<MapTariffari>();
		Date current = new Date();

		con.startReading(xlsFile, "Mapping.xml");
		List<MapTariffari> rows = con.getRows();
		
		for ( MapTariffari row : rows ){
			
			if ( row.getCategoria() == null && row.getCodice() == null
					&& row.getDenominazione() == null && Double.isNaN(row.getPrezzoAlKilo())
					&& Double.isNaN(row.getPrezzoAlPezzo()) && row.getTipoConfezione() == null ){
				// Saltiamo l'eventuale riga vuota
			}
			else{
				Articolo a = new Articolo();
				a.setIdArticolo(dbm.getNewID("articolo", "idArticolo"));
				a.setDataInserimento(new java.sql.Date(new Date().getTime()));
				a.setDescrizione(row.getDenominazione());
				a.setIva(0);
				a.setPrezzoDettaglio(row.getPrezzoAlPezzo());
				a.setScortaMinima(20);
				a.setNote(row.getTipoConfezione());
				
				System.out.println("Settare il codice a barre, codice fornitore, id fornitore, id reparto, um");
				
				a.insertArticolo();
				
//				this.codBarre = rs.getString("codBarre");
//				this.codFornitore = rs.getString("codFornitore");
//				
//				this.idFornitore = rs.getInt("idFornitore");
//				this.idReparto = rs.getInt("idReparto");
//				this.um = rs.getInt("um");
			}
				
		}

	}

}
