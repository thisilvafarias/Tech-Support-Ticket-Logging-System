

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JMenu;
	import javax.swing.JMenuBar;
	import javax.swing.JMenuItem;
	import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


	public class editTicketWindow extends JFrame {
		
	    JMenuBar MenuBar = new JMenuBar();

        JMenu file = new JMenu("File");
            JMenuItem close = new JMenuItem("Close");

        JMenu Edit = new JMenu("Edit");
            JMenuItem Copy = new JMenuItem("Copy");
            JMenuItem Paste = new JMenuItem("Paste");
            JMenuItem Cut = new JMenuItem("Cut");

        JMenu Help = new JMenu("Help");
            JMenuItem ShowHelp = new JMenuItem("Show Help");
            JMenuItem About= new JMenuItem("About");
            
            
            JPanel Panel = new JPanel();
            JPanel Panel_A = new JPanel();
            JPanel Panel_B = new JPanel();
            
            JButton sav = new JButton("Save");
            JButton quit = new JButton("Quit");
            
            DefaultTableModel model;
            JTable table;
            String textOnData;
            
            JTextArea text = null;
            String tk = null;
            
		public editTicketWindow(String TicketNumber) {
			
			
			// Menu Bar
			this.setJMenuBar(MenuBar);
			
			file.add(close);
	        
	        Edit.add(Copy);
	        Edit.add(Paste);
	        Edit.add(Cut);
	        
	        Help.add(ShowHelp);
	        Help.addSeparator();
	        Help.add(About);
	        
	        MenuBar.add(file);
	        MenuBar.add(Edit);
	        MenuBar.add(Help);     
	       
	        // PANEL

			JPanel panel = new JPanel(new GridBagLayout());
			this.getContentPane().add(panel);
			
			//creating jtable
			
			
			//Initial of connection 
			//String ticketNumber = TicketNumber;
			String query = "SELECT t.*, c.* FROM Ticket t left JOIN Customers c ON t.TicketNumber=c.id where TicketNumber = '"+TicketNumber+"';";
			
			dbConn db = new dbConn(query);
			ResultSet rs = db.select();
	    	
			try {
	    	    //title of attributes
	    	    model = new DefaultTableModel(new String[]{"Ticket Number", "First Name", "Second Name", "Status"}, 0);
	    	    while(rs.next())
	        	{
	        	    String tn = rs.getString("TicketNumber");
	        	    String fn = rs.getString("FirstName");
	        	    String sn = rs.getString("SecondName");
	        	    String st = rs.getString("Status");
	        	    model.addRow(new Object[]{tn, fn, sn, st}); //collumns
	        	 
	        	  textOnData = rs.getString("Description");
	        	}
	    	    

			table = new JTable();
			table.setModel(model);
			JScrollPane tableScrollPane = new JScrollPane(table);
			//final of jtable
			//final jtable
			
			text = new JTextArea(textOnData);
			
			JLabel ticketLabel = new JLabel("Ticket Number " + TicketNumber);
			JLabel descriptionTicket = new JLabel("Description");
			
			JPanel tableBtnPanel = new JPanel();
			tableBtnPanel.setBackground(Color.LIGHT_GRAY);
			tableBtnPanel.add(sav);
			tableBtnPanel.add(quit);
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			gbc.gridx = 0;  // x = 0
			gbc.gridy = 0; //  y = 0
			gbc.gridwidth = 1;  //uses all width of the 0,0
			gbc.gridheight = 1; //uses all height of the 0,0
			panel.add(ticketLabel, gbc);  // add label "Tickets"
			gbc.insets = new Insets(20,0,20,0); 
			panel.setBackground(Color.LIGHT_GRAY);
			
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.BOTH; // Allows componet use whole space
	        gbc.insets = new Insets(0,20,0,20); // top, left, bottom then right. (gap from the componate to border)
	        gbc.weightx = 1;     //grows on X if resized
	        gbc.weighty = 1;	//grows on y if resized
	        panel.add(tableScrollPane, gbc);//add table of tickets
	        
			gbc.gridx = 0;  // x = 0
			gbc.gridy = 2; //  y = 0
			gbc.fill = GridBagConstraints.VERTICAL;
			gbc.insets = new Insets(4,0,0,0); 
			panel.add(descriptionTicket, gbc);  // add label "Tickets"
			
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.fill = GridBagConstraints.BOTH; // Allows componet use whole space
			gbc.insets = new Insets(0,20,0,20); 
			gbc.weightx = 1;     //grows on X f resized
	        gbc.weighty = 7;	//grows on y f resized
			panel.add(text, gbc);//add table of tickets
			
			
			gbc.gridx = 0;  // x = 0
			gbc.gridy = 4; //  y = 2
			gbc.gridwidth = 1;  //uses all width of the 0,0
			gbc.gridheight = 1; //uses all height of the 0,0
	        gbc.weightx = 0;     //doesn't grow on X if resized
	        gbc.weighty = 3;	//doesn't  grow on y if resized
	        gbc.insets = new Insets(0,20,0,20); 
			panel.add(tableBtnPanel, gbc);  //Add table with buttons "edit" and "New Ticket" 
	    	
	    	validate();
			repaint();
			
			ButtonHandler Handler = new ButtonHandler();
	        About.addActionListener(Handler);
	        ShowHelp.addActionListener(Handler);
	        close.addActionListener(Handler);
	        Copy.addActionListener(Handler);
	        Paste.addActionListener(Handler);
	        Cut.addActionListener(Handler);	 
	        sav.addActionListener(Handler);
	        quit.addActionListener(Handler);
	        
	        sav.setActionCommand("sav");
	        quit.setActionCommand("quit");
	        
	        setSize(500,500);
			setVisible(true);
			this.setLocationRelativeTo(null);
			text.requestFocusInWindow();
				
			}catch (SQLException ex) {
	    	    // handle any errors
	    	    System.out.println("SQLException: " + ex.getMessage());
	    	    System.out.println("SQLState: " + ex.getSQLState());
	    	    System.out.println("VendorError: " + ex.getErrorCode());
	    	}
	    	
	    	validate();
			repaint();
			
			tk = new String(TicketNumber);
	}
		
		private class ButtonHandler implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == close){}
				
				else if (e.getSource() == Copy){}
				else if (e.getSource() == Paste){}
				else if (e.getSource() == Cut){}
				else if (e.getSource() == About){}
           
				else if (e.getSource() == ShowHelp){
                JOptionPane.showMessageDialog(editTicketWindow.this,
                         "This a Ticket System\n" +
                         "Developed by Thiago Farias\n" +
                         "College of Computer Technology\n" +
                         "December 2017.", "Ajuda", JOptionPane.PLAIN_MESSAGE);
					}
            
            // Buttons 
				else if (e.getActionCommand().equals("sav")) {
            	System.out.println("The ticket "+tk+" was successfully saved");
            	Connection conn = null;
            	Statement stmt = null;
        				try {
        					String Description = text.getText();
        					String nameT =null;
        					
        					 conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/TicketLoggingSystem?user=root&password=");
        				     stmt = conn.createStatement();
        				    
        					//stmt.execute("UPDATE Ticket SET Description = '"+Description+"', where TicketNumber = '"+tk+"';");
        					stmt.execute("UPDATE Ticket SET Description ='"+Description+"' where TicketNumber = '"+tk+"';");
        					
        					JOptionPane.showMessageDialog(Panel, "New ticket was successfully saved");
        					
        					setVisible(false);
        				    dispose();
        					newTicket classe = new newTicket(nameT);
        					String name = classe.getName();
        					new TechSupport(name);
        					
        				}
        				catch(SQLException ex) {
        					System.out.println("SQLException: " + ex.getMessage());
        					System.out.println("SQL State: " + ex.getSQLState());
        					System.out.println("VendorError: " + ex.getErrorCode());
        				}
					}
				else if (e.getActionCommand().equals("quit")) {
             	System.out.println("quit");
					}
				
		}
	} // end of class ButtonHandler
}

