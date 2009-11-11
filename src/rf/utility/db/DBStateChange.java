/**
 * 
 */

package rf.utility.db;

/**
 * @author hunterbit
 */
public interface DBStateChange {
    public void stateChange();

    public void rowStateChange(RowEvent re);

    public String getNomeTabella();

    public void stateChange(DBEvent dbe);

}
