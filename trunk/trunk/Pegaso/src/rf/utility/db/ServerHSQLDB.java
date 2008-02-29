package rf.utility.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

import org.hsqldb.Server;

public class ServerHSQLDB {

    private Connection conn;

    private Statement st;

    private String url;

    private String user = "sa";

    private String password = "";

    private Server server;

    private boolean isNetwork = true;

    // nome del database
    private String dbName;

    public ServerHSQLDB() {
	this.dbName = "sa";
	this.st = null;
    }

    protected void setUp() {

	if (isNetwork) {
	    url = "jdbc:hsqldb:hsql://localhost/";
	    server = new Server();
	    server.setLogWriter(null);
	    server.setErrWriter(null);
	    server.start();
	} else {
	    url = "jdbc:hsqldb:file:test;sql.enforce_strict_size=true";
	}

	try {
	    Class.forName("org.hsqldb.jdbcDriver");
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println(this + ".setUp() error: " + e.getMessage());
	}
    }

    protected void tearDown() {

	if (isNetwork) {
	    server.stop();
	    server = null;
	}
    }

    public void startServer() {
	setUp();
	System.out.println("Server AVVIATO");
    }

    /**
     * Chiude la connessione attiva al DB
     */
    public void stopServer() {
	tearDown();
	System.out.println("Server FERMATO");
    }

    public String getDbName() {
	return dbName;
    }

    public void setDbName(String dbName) {
	this.dbName = dbName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getUser() {
	return user;
    }

    public void setUser(String user) {
	this.user = user;
    }

    public static void main(String... args) {
	ServerHSQLDB server = new ServerHSQLDB();
	server.startServer();
	System.out.println("premere INVIO per chiudere il SERVER");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	try {
	    br.readLine();
	} catch (IOException e) {

	    e.printStackTrace();
	}
	server.stopServer();
    }

}
