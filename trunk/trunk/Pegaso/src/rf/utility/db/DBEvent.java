package rf.utility.db;

/**
 * @author Hunter
 * 
 */
public class DBEvent {
	private int nCamera;
	private String tabella;

	public DBEvent() {
		this.tabella = new String();
		this.nCamera = -1;
	}

	public DBEvent(String tabella, int nCamera) {
		this.tabella = tabella;
		this.nCamera = nCamera;
	}

	public int getNCamera() {
		return nCamera;
	}

	public String getTabella() {
		return tabella;
	}

	public void setNCamera(int camera) {
		nCamera = camera;
	}

	public void setTabella(String tabella) {
		this.tabella = tabella;
	}

}
