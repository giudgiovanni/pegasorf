package rf.utility.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.DefaultListModel;

public class UtilityDBManager {

	class FileComparable implements Comparator<File> {

		public int compare(File f1, File f2) {
			Date d1 = new Date(f1.lastModified());
			Date d2 = new Date(f2.lastModified());
			return d1.compareTo(d2);
		}

	}

	// usate per selezionare il tipo di salvataggio
	final public static int UPDATE = 0;

	final public static int DELETE = 1;

	final public static int INSERT = 2;

	final public static int OPEN = 3;

	final public static int CLOSE = 4;

	private static UtilityDBManager db = null;

	private String pathCommand = null;

	private String cmdBackup = null;

	private String cmdRestore = null;

	private String nameDb;

	private Properties props = null;

	private String update = null;

	private String delete = null;

	private String insert = null;

	private String open = null;

	private String close = null;

	private String folderBackup;

	private String userDir;

	private String nFileBackup;

	private UtilityDBManager() throws FileNotFoundException, IOException {
		props = new Properties();
		// carichiamo il file di properties
		props.load(new FileReader("utilitydbconfig.properties"));

		// impostiamo tutti i campi
		this.nameDb = props.getProperty("nameDb");
		this.pathCommand = props.getProperty("pathCommand");
		this.cmdBackup = props.getProperty("cmdBackup");
		this.cmdRestore = props.getProperty("cmdRestore");
		this.folderBackup = props.getProperty("folderBackup");
		this.userDir = System.getProperty("user.dir");
		this.nFileBackup = props.getProperty("nFileBackup", "50");

		// se il valore non è presente usiamo no come default
		this.update = props.getProperty("update", "N");
		this.delete = props.getProperty("delete", "N");
		this.insert = props.getProperty("insert", "N");
		this.close = props.getProperty("close", "N");
		this.open = props.getProperty("open", "N");

		// creiamo se non esiste la cartella per i backup
		File f = new File(this.userDir+File.separator+folderBackup);
		if (!f.exists())
			f.mkdir();
	}

	public String getPathCommand() {
		return pathCommand;
	}

	public String getCmdBackup() {
		return cmdBackup;
	}

	public String getCmdRestore() {
		return cmdRestore;
	}

	public String getNameDb() {
		return nameDb;
	}

	public String getFolderBackup() {
		return folderBackup;
	}

	public String getUserDir() {
		return userDir;
	}

	public String getNFileBackup() {
		return nFileBackup;
	}

	public static UtilityDBManager getSingleInstance()
			throws FileNotFoundException, IOException {

		if (db == null) {
			db = new UtilityDBManager();
		}
		return db;
	}

	public void backupDataBase(int modalita) throws IOException {

		GregorianCalendar c = new GregorianCalendar();
		// formattiamo la data
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy_hhmmss");
		Date d = c.getTime();
		String time = format.format(d);
		// creazione del comando con tutti i parametri
		String cmd = pathCommand + cmdBackup + " \"" + userDir + "\\"
				+ folderBackup + time + "-" + nameDb + ".sql\" " + nameDb;
		Runtime r = Runtime.getRuntime();
		// controlla se una delle modalità è selezionate
		if (DELETE == modalita && delete.equals("S")) {
			r.exec(cmd);
		} else if (INSERT == modalita && insert.equals("S")) {
			r.exec(cmd);
		} else if (UPDATE == modalita && update.equals("S")) {
			r.exec(cmd);
		} else if (CLOSE == modalita && close.equals("S")) {
			r.exec(cmd);
		} else if (OPEN == modalita && open.equals("S")) {
			r.exec(cmd);
		}
		checkFileInFolder();
	}

	public void backupDataBase() throws IOException {
		GregorianCalendar c = new GregorianCalendar();
		// formattiamo la data
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy_hhmmss");
		Date d = c.getTime();
		String time = format.format(d);
		// creazione del comando con tutti i parametri
		String cmd = pathCommand + cmdBackup + "\"" + userDir + "\\"
				+ folderBackup + time + "-" + nameDb + ".sql\" " + nameDb;
		Runtime r = Runtime.getRuntime();
		r.exec(cmd);
		checkFileInFolder();
	}
	
	public void restoreDataBase(String pathRestore) throws IOException {
		String cmd = pathCommand + cmdRestore + " "+nameDb+" "+"\""+pathRestore+"\"";
		Runtime r = Runtime.getRuntime();
		r.exec(cmd);
	}

	private void checkFileInFolder() {
		File n[] = ordinaFileByDataCreazione();
		if (n.length > new Integer(this.nFileBackup).intValue()) {
			// numero file da cancellare
			int fDelete = n.length - new Integer(this.nFileBackup).intValue();
			for (int i = 0; i < fDelete; i++) {
				n[i].delete();
			}
		}
	}

	public File[] getAllFile() {
		File f = new File(this.userDir+File.separator+this.folderBackup);
		return f.listFiles();
	}

	public String[] elencoFileBackup() {
		File n[] = ordinaFileByDataCreazione();
		String files[] = new String[n.length];
		for (int i = 0; i < n.length; i++) {
			files[i] = n[i].getName();
		}
		return files;
	}

	public File[] ordinaFileByDataCreazione() {
		// creiamo il comparable per ordinare
		// gli oggetti in base alla data di creazione
		File[] f = getAllFile();
		Arrays.sort(f, new FileComparable());
		return f;
	}

	public void restoreBackup(String nome, DefaultListModel l)
			throws IOException, SQLException {
		
		//disattiviamo il drop delle tabelle perchè
		//già incluso nei file di backup creati da postgres
		//dropAllTable();
		
		
		// creaimo il path con il nome del file
		// per effettuare il ripristino dei dati
		String f = userDir + File.separator + folderBackup + nome;
		restore(f, l);

	}

	private void restore(String nome, DefaultListModel l) throws IOException,
			SQLException {
// effettuate delle modifiche sotto il codice nuovo
		// che va bene per il nuovo tipo di backup

//		Statement st = DBManager.getIstanceSingleton().getNewStatement();
//		StringBuffer sb = new StringBuffer();
//
//		// prepariamo la lettura del file
//		File f = new File(nome);
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				new FileInputStream(f)));
//		String line = "";
//		while ((line = br.readLine()) != null && !line.equals("\n")) {
//			if ((line.length() > 0 && line.charAt(0) != '-')
//					&& !(line.substring(0, 3).equalsIgnoreCase("set"))
//					&& !line
//							.equalsIgnoreCase("CREATE PROCEDURAL LANGUAGE plpgsql;")) {
//				// se il comando SQL risiede su unica riga lo eseguiamo
//				// direttamente altrimenti saltiamo avanti per controllare
//				// le altre righe.
//				if (line.charAt(line.length() - 1) == ';') {
//					st.execute(line.substring(0, line.length() - 1));
//					if (l != null)
//						l.addElement(line);
//				} else {
//					// da qui gestiamo gli altri comandi
//					// suddivisi su più righe.
//					sb = new StringBuffer();
//					// inseriamo la prima riga
//					sb.append(" " + line);
//
//					// e poi leggiamo gli altri fino a quando
//					// non troviamo il ; come carattere
//					boolean fine = false;
//					while (!fine) {
//						line = br.readLine();
//						if (line.charAt(line.length() - 1) == ';'
//								|| line == null) {
//							fine = true;
//							sb.append(" " + line);
//							st.execute(sb.toString().substring(0,
//									sb.toString().length() - 1));
//							if (l != null)
//								l.addElement(line);
//						} else {
//							sb.append(" ");
//							sb.append(line);
//						}
//					}
//				}
//			}
//		}

		Statement st = DBManager.getIstanceSingleton().getNewStatement();
		StringBuffer sb = new StringBuffer();

		// prepariamo la lettura del file
		File f = new File(nome);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(f)));
		String line = "";
		while ((line = br.readLine()) != null && !line.equals("\n")) {
			if ((line.length() > 0 && line.charAt(0) != '-')
					&& !(line.substring(0, 3).equalsIgnoreCase("set"))) {
				// se il comando SQL risiede su unica riga lo eseguiamo
				// direttamente altrimenti saltiamo avanti per controllare
				// le altre righe.
				if (line.charAt(line.length() - 1) == ';') {
					st.execute(line.substring(0, line.length() - 1));
					if (l != null)
						l.addElement(line);
				} else {
					// da qui gestiamo gli altri comandi
					// suddivisi su più righe.
					sb = new StringBuffer();
					// inseriamo la prima riga
					sb.append(" " + line);

					// e poi leggiamo gli altri fino a quando
					// non troviamo il ; come carattere
					boolean fine = false;
					while (!fine) {
						line = br.readLine();
						if (line.charAt(line.length() - 1) == ';'
								|| line == null) {
							fine = true;
							sb.append(" " + line);
							st.execute(sb.toString().substring(0,
									sb.toString().length() - 1));
							if (l != null)
								l.addElement(line);
						} else {
							sb.append(" ");
							sb.append(line);
						}//fine if...else
					}//fine while
				}//fine if...else
			}//fine if
		}//fine while
	}

	public void restoreBackup(String nome) throws IOException, SQLException {
		restore(nome, null);

	}

	private void dropAllTable() {
		DBManager dbm = DBManager.getIstanceSingleton();
		ResultSet rs = null;
		Statement pst = dbm.getNewStatement();
		try {
			DatabaseMetaData dmd = dbm.getConnessione().getMetaData();
			String types[] = { "TABLE" };
			rs = dmd.getTables(null, null, null, types);
			String temp = null;
			while (rs.next()) {
				temp = rs.getString("TABLE_NAME");
				String q = "drop table " + temp + " cascade";
				System.out.println(q);
				pst.execute(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void removeDataBase() {
		dropAllTable();
		
	}

	
}
