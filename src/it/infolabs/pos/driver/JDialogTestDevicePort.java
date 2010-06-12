package it.infolabs.pos.driver;

import it.infolabs.pos.PosException;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextArea;

import rf.utility.gui.UtilGUI;

public class JDialogTestDevicePort extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextArea txtResult = null;

	/**
	 * This method initializes txtResult	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtResult() {
		if (txtResult == null) {
			txtResult = new JTextArea();
		}
		return txtResult;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param owner
	 */
	public JDialogTestDevicePort(Frame owner) {
		super(owner,true);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(683, 294);
		this.setTitle("Risultati Test");
		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);
		test();
	}

	private void test() {
		RCHDriver driver=new RCHDriver();
		List<String> elenco=driver.getAllCommPort();
		Iterator<String> it=elenco.iterator();
		StringBuffer sb=new StringBuffer();
		while(it.hasNext()){
			sb.append(txtResult.getText());
			sb.append(it.next());
			sb.append("\n");
			txtResult.setText(sb.toString());
			
			
		}
		
		
		try {
			sb.append("---------- APERTURA DEVICE PORT START -----------------\n");
			driver.openDeviceConnection();
			sb.append("---------- APERTURA DEVICE PORT OK --------------\n");
			txtResult.setText(sb.toString());
			sb.append("-------------------------------------------------\n");
			sb.append("---------- CUT DEVICE START ---------------------------\n");
			driver.cutTicket();
			sb.append("---------- CUT DEVICE OK ------------------------\n");
			txtResult.setText(sb.toString());
			sb.append("-------------------------------------------------\n");
			sb.append("---------- CHIUSURA DEVICE PORT START -----------------\n");
			driver.closeDeviceConnection();
			sb.append("---------- CHIUSURA DEVICE PORT OK --------------\n");
			txtResult.setText(sb.toString());


		} catch (PosException e) {
			sb.append(e.getMessage());
			txtResult.setText(sb.toString());
			e.printStackTrace();
		}
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getTxtResult(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
