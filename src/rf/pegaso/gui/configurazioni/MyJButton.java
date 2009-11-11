package rf.pegaso.gui.configurazioni;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyJButton extends JButton implements PropertyChangeListener{

    public MyJButton(){
	super();
    }
    public void propertyChange(PropertyChangeEvent evt) {

	if(evt.getSource() instanceof JTextField || evt.getSource() instanceof JPasswordField){
	    if(evt.getPropertyName().equalsIgnoreCase("text")){
		this.setEnabled(true);
	    }
	}
    }

}
