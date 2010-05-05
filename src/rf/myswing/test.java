package rf.myswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class test
{
	public test()
	{
		final JFrame f;
		JButton b;
		
		f=new JFrame("test");
		b=new JButton("apri finestra");
		b.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						JFrameModale fm;
						fm=new JFrameModale("jframe modale",f);
						fm.setSize(300,300);
						fm.setModale();
					}
				}
				);
		
		f.getContentPane().add(b);
		f.setSize(300,300);
		f.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new test();
	}
}