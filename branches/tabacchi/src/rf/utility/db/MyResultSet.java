/**
 * 
 */
package rf.utility.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author Hunter
 * 
 */
public class MyResultSet {
	private int numCol;
	private int numRighe;
	private Object o[][];
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	public MyResultSet(ResultSet rs) throws SQLException {
		this.rs = rs;
		rsmd = rs.getMetaData();
		this.rs.last();
		this.numRighe = rs.getRow();
		rs.beforeFirst();
		numCol = rsmd.getColumnCount();
		o = new Object[numRighe][numCol];
		for (int i = 0; rs.next(); i++) {
			for (int k = 0; k < numCol; k++) {
				o[i][k] = rs.getObject(k);
			}
		}
	}

	public Object[][] getAllObject() {
		return o;
	}

	public Object getObject(int r, int c) {
		return o[r][c];
	}

}
