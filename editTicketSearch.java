import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class editTicketSearch extends JFrame {
	static JTextField ticketNumber = null;
	
	public editTicketSearch() {
		
		setSize(300, 150);
		setVisible(true);
		this.setLocationRelativeTo(null);
		
		setLayout(new GridLayout(1, 1));
		
		//creating panels
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		add(top);
		//Final - creating panels
		
		JLabel titleLabel = new JLabel("Insert the ticket number, please.");
		top.add(titleLabel);
		
		ticketNumber = new JTextField(20);
		top.add(ticketNumber);
		
		JButton sc = new JButton("Search");
		sc.setActionCommand("search");
		top.add(sc);
		
		validate();
		repaint();
		
		ButtonHandler Handler = new ButtonHandler();
		sc.addActionListener(Handler);
		 
	}
		
		private class ButtonHandler implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getActionCommand().equals("search")) {
					System.out.println("Create a new Ticket");
					
						String tktNumber = editTicketSearch.ticketNumber.getText();
					
						new editTicketWindow(tktNumber);
						setVisible(false);
				        dispose();
            	
					}
			}
		}
}