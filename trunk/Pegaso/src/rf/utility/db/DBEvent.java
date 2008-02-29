/**
 *
 */
package rf.utility.db;

/**
 * @author Hunter
 *
 */
public class DBEvent {
    private String tabella;
    private int nCamera;

    public DBEvent(String tabella, int nCamera) {
	this.tabella = tabella;
	this.nCamera = nCamera;
    }

    public DBEvent() {
	this.tabella = new String();
	this.nCamera = -1;
    }

    public int getNCamera() {
	return nCamera;
    }

    public void setNCamera(int camera) {
	nCamera = camera;
    }

    public String getTabella() {
	return tabella;
    }

    public void setTabella(String tabella) {
	this.tabella = tabella;
    }

}
