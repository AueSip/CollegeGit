package week1package ;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class GuiFrame  extends JFrame implements ActionListener{
	static JTextField textfield;
	static JButton button;
	static JLabel label;
	static String inputText;
	
	GuiFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.pack();
		this.setVisible(true);
		label = new JLabel("");
		textfield = new JTextField();
		button = new JButton("Submit");
		textfield.setPreferredSize(new Dimension(150, 60));
		this.add(label);
		this.add(textfield);
		this.add(button);
		button.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==button) {
			textfield.getText();
			
		}
	
		
		
	}
}
