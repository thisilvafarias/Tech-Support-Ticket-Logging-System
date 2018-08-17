import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class resetPasswordSearch extends JFrame {
	static JTextField userNumber = null;
	
	public resetPasswordSearch() {
		
		setSize(300, 150);
		setVisible(true);
		this.setLocationRelativeTo(null);
		
		setLayout(new GridLayout(1, 1));
		
		//creating panels
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		add(top);
		//Final - creating panels
		
		JLabel titleLabel = new JLabel("Insert the user ID to be reset, please.");
		top.add(titleLabel);
		
		userNumber = new JTextField(20);
		top.add(userNumber);
		
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
					
						String userNumber = resetPasswordSearch.userNumber.getText();
					
						Component top = null;
						JOptionPane.showMessageDialog(top,"The Password was successfully Reseted");
    					
    					setVisible(false);
    				    dispose();
    				    Connection conn = null;
    	            	Statement stmt = null;
    	        				try {
    	        					String firstPassword = userNumber;
    	        					String nameT =null;
    	        					
    	        					 conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/TicketLoggingSystem?user=root&password=");
    	        				     stmt = conn.createStatement();
    	        				    
    	        					//stmt.execute("UPDATE Ticket SET Description = '"+Description+"', where TicketNumber = '"+tk+"';");
    	        					stmt.execute("UPDATE Login SET password ='"+firstPassword+"' where UserID = '"+userNumber+"';");
    					
    	        				}
    	        				catch(SQLException ex) {
    	        					System.out.println("SQLException: " + ex.getMessage());
    	        					System.out.println("SQL State: " + ex.getSQLState());
    	        					System.out.println("VendorError: " + ex.getErrorCode());
    	        				}
    						}
			}
		}
}