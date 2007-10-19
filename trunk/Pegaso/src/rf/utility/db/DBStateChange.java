package rf.utility.db;

/**
 * @author hunterbit
 */
public interface DBStateChange {
	public String getTableName();

	public void stateChange();

	public void stateChange(DBEvent dbe);

}
