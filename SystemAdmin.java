
import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


	public class SystemAdmin extends JFrame implements ListSelectionListener {
		
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
            
            JButton btnNew = new JButton("New User");
            JButton rst = new JButton("Reset Password");
            JButton sc = new JButton("Search User");
            JButton cl = new JButton("Reset Password");
            
            DefaultTableModel model;
            static JTable table;
            JScrollPane tableScrollPane;

			public String nameofTech;
            
            
         //Start constructor   
		public SystemAdmin(String name) throws SQLException {
			
			nameofTech = name;
			setTitle("Admin by "+ name);
			
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
			panel.setBackground(Color.LIGHT_GRAY);
			this.getContentPane().add(panel);
			Statement stmt = null;
			
			//Initial of connection 		
			String query = "SELECT * FROM Login;";
			
			
			dbConn db = new dbConn(query);
			ResultSet rs = db.select();
	    	
			try {
	    	    //title of attributes
	    	    model = new DefaultTableModel(new String[]{"User ID", "First Name", "Second Name", "type"}, 0);
	    	    while(rs.next())
	        	{
	        	    String ui = rs.getString("userID");
	        	    String fn = rs.getString("name");
	        	    String sn = rs.getString("secondName");
	        	    String ty = rs.getString("type");
	        	    model.addRow(new Object[]{ui, fn, sn, ty}); //collumns
	        	}
	    	    

			table = new JTable();
			table.setModel(model);
			tableScrollPane = new JScrollPane(table);
			//table mouse click
			table.getSelectionModel().addListSelectionListener(this);
			//refreshing table
			
			
			    
			//final of jtable
			
			JLabel ticketLabel = new JLabel("Tickets");
			
			
			JPanel tableBtnPanel = new JPanel();
			tableBtnPanel.setBackground(Color.LIGHT_GRAY);
			tableBtnPanel.add(btnNew);
			tableBtnPanel.add(rst);
			
			JPanel detailsPanel = new JPanel(new GridBagLayout());
			detailsPanel.setBackground(Color.LIGHT_GRAY);

			GridBagConstraints gbc = new GridBagConstraints();
			

			gbc.gridx = 0;  // x = 0
			gbc.gridy = 0; //  y = 0
			panel.add(ticketLabel, gbc);  // add label "Tickets"
			
			gbc.gridx = 1;  // x = 0
			
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;  //uses all width of the 0,0
			gbc.gridheight = 1; //uses all height of the 0,0
			gbc.fill = GridBagConstraints.BOTH; // Allows componet use whole space
	        gbc.insets = new Insets(0,20,0,0); // top, left, bottom then right. (gap from the componate to border)
	        gbc.weightx = 1;     //grows on X f resized
	        gbc.weighty = 1;	//grows on y f resized
			panel.add(tableScrollPane, gbc);//add table of tickets
			
			gbc.gridx = 0;  // x = 0
			gbc.gridy = 2; //  y = 2
			gbc.gridwidth = 1;  //uses all width of the 0,0
			gbc.gridheight = 1; //uses all height of the 0,0
	        gbc.weightx = 0;     //doesn't grow on X if resized
	        gbc.weighty = 0;	//doesn't  grow on y if resized
			panel.add(tableBtnPanel, gbc);  //Add table with buttons "edit" and "New Ticket" 
			
			gbc.gridx = 1;
			gbc.gridy = 1;
	        gbc.insets = new Insets(0,0,0,0); // top, left, bottom then right. (gap from the componate to border)
			panel.add(detailsPanel, gbc);
					
		        gbc.gridx = 0; 
		        gbc.gridy = 0;
				detailsPanel.add(sc, gbc);
				
				gbc.gridx = 0;
				gbc.gridy = 1;
				//detailsPanel.add(cl, gbc);
				
				
	    	} 
	    	catch (SQLException ex) {
	    	    // handle any errors
	    	    System.out.println("SQLException: " + ex.getMessage());
	    	    System.out.println("SQLState: " + ex.getSQLState());
	    	    System.out.println("VendorError: " + ex.getErrorCode());
	    	}
	    	
	    	validate();
			repaint();
			
		ButtonHandler Handler = new ButtonHandler();
        About.addActionListener(Handler);
        ShowHelp.addActionListener(Handler);
        close.addActionListener(Handler);
        Copy.addActionListener(Handler);
        Paste.addActionListener(Handler);
        Cut.addActionListener(Handler);	 
        btnNew.addActionListener(Handler);
        rst.addActionListener(Handler);
        sc.addActionListener(Handler);
        cl.addActionListener(Handler);
        
        btnNew.setActionCommand("newTicket");
        rst.setActionCommand("Reset");
        sc.setActionCommand("search");
        cl.setActionCommand("resetTicket");
        
        setSize(1300,500);
		setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
			
		}
		
		
		private class ButtonHandler implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == close){
	            	
					Object[] options = {"Save",
			                			"Cancel",
			                			"Don't Save"};
					
						int n = JOptionPane.showOptionDialog(Panel, "Do you want to save the changes made to the document?"
						+ "",
						"Closing Ticket...",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE,
						null,
						options,
						options[2]);
						
						
						if(n == 0){
							System.out.println("Saved");
							System.exit(0);
						}
						else if(n == 2){
							System.out.println("Closed without save");
							System.exit(0);
						}
						
		            }
				
				else if (e.getSource() == Copy){}
				else if (e.getSource() == Paste){}
				else if (e.getSource() == Cut){}
				else if (e.getSource() == About){}
           
				else if (e.getSource() == ShowHelp){
                JOptionPane.showMessageDialog(SystemAdmin.this,
                         "This a Ticket System\n" +
                         "Developed by Thiago Farias\n" +
                         "College of Computer Technology\n" +
                         "December 2017.", "Ajuda", JOptionPane.PLAIN_MESSAGE);
					}
            
            // Buttons 
				else if (e.getActionCommand().equals("newTicket")) {
					System.out.println("Create a new User");
            		new newUser();
            		setVisible(false);
			        dispose();
            	
					}
				else if (e.getActionCommand().equals("Reset")) {
					System.out.println("Reset Password");
            		new resetPasswordSearch();
            		
            	
					}
				else if (e.getActionCommand().equals("search")) {
					System.out.println("Search user by number");
					new searchUser();
					
					}
				else if (e.getActionCommand().equals("closeTicket")) {
             	System.out.println("Close this ticket");
             	new closeTicketSearch();
             	
					}
				
		}
	} // end of class ButtonHandler

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()){
			
			String tktNumber = (String) table.getValueAt(table.getSelectedRow(), 0);
			System.out.println(tktNumber);
			new Ticket(tktNumber);
				
			 }
		}
}
