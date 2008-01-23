/*
 * $Id: JDBCLoginService.java,v 1.7 2006/05/23 19:07:51 rbair Exp $
 *
 * Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package rf.myswing.security;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdesktop.swingx.auth.LoginService;

import rf.pegaso.db.DBManager;

/**
 * A login service for connecting to SQL based databases via JDBC
 *
 * @author rbair
 */
public class MyJDBCLoginService extends LoginService {


	private DBManager dbm;

	public MyJDBCLoginService(DBManager dbm) throws NoSuchAlgorithmException, SQLException {
		super();
		this.dbm = dbm;
		if(!existRootAdmin()){
			createRootAdmin();
		}
	}

	public boolean authenticate(String name, char[] password, String server)
			throws Exception {

		String pwd1=new String(password);
		String pwdRoot=getPasswordUser(name);
		if(pwd1.equals(pwdRoot))
			return true;
		return false;
	}

	private String getPasswordUser(String name) throws SQLException, IOException, NoSuchAlgorithmException {

		//prepariamo le query ed eseguiamola
		String query="select pwd from utenti where nome=?";
		PreparedStatement pst=dbm.getNewPreparedStatement(query);
		ResultSet rs=null;
		//impostiamo la query
		pst.setString(1, name);
		//eseguiamo e preleviamo il valore
		rs=pst.executeQuery();
		rs.next();
		String tmp=rs.getString(1);

		//decodifichiamo la password
		byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(tmp);
		tmp="";
		for(int i=0; i<dec.length; i++){
		tmp+=( char )dec[ i ];
		}
		return tmp;
	}

	private void createRootAdmin() throws NoSuchAlgorithmException, SQLException {
		// inseriamo l'utente root con la relativa
		// password di default che è sempre root
		// prepariamo prima la query di inserimento
		String insert="insert into utenti values (?,?,?,?,?,?,?)";
		PreparedStatement pst=dbm.getNewPreparedStatement(insert);

		//prepariamo la password convertendola con algoritmo
		// BASE64
		// Codifichiamo i byte in base64
		String pwd=new sun.misc.BASE64Encoder().encode("root".getBytes());

		//impostiamo ora i campi della query
		pst.setInt(1, dbm.getNewID("utenti", "idutente"));
		pst.setString(2, "root");
		pst.setString(3, pwd);
		pst.setInt(4, 1);
		pst.setInt(5, 1);
		pst.setInt(6, 1);
		pst.setString(7, "Amministratore sistema Pegaso");

		//effettuiamo la quesry di inserimento
		pst.execute();

		//chiudiamo le risorse
		pst.close();

	}

	public boolean existRootAdmin() throws SQLException{
		return existUser("root");

	}

	public boolean existUser(String user) throws SQLException{
		String query="select nome from utenti where nome=?";
		PreparedStatement pst=dbm.getNewPreparedStatement(query);
		ResultSet rs=null;
		pst.setString(1, user);
		rs=pst.executeQuery();
		rs.last();
		int n=rs.getRow();
		rs.close();
		pst.close();
		if(n>0)
			return true;
		return false;
	}
}
