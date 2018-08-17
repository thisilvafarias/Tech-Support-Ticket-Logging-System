
import java.awt.Color;
import java.awt.GraphicsConfiguration;
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
import javax.swing.JComboBox;
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


	public class Manager extends JFrame {
		
		
		JTextField id = null;
		JTextField FirstName = null;
		JTextField SecondName =  null;
      	JTextField Age = null;
      	JTextField Password = null;
      	JTextArea type = null;
      	
		
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
            
            JButton refresh = new JButton("Refresh");
            JButton quit = new JButton("Quit");
            
            DefaultTableModel model;
            JTable table;
            int closed;
            int opened;
            
		public  Manager(String name) throws SQLException {
			
			id = new JTextField(20);
			
			this.setTitle("Tickets opened and closed");
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
			
			Connection conn = null;
			Statement stmt = null;
			//Iniciati connection
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/TicketLoggingSystem?user=root&password=");
		    stmt = conn.createStatement();
		    
		    String query = ("SELECT COUNT(OpenOn)  FROM Ticket;");
			dbConn db = new dbConn(query);
			ResultSet rs = db.select();
			
			String openedString = null;
			
			
			   if (rs.next()) {
		           opened = rs.getInt(1);
		           System.out.println("Amount of opend tickets: " + opened);
		           openedString = String.valueOf(opened);
		           
		       }
			  
			   
			   
			String query2 = ("SELECT COUNT(ClosedOn)  FROM Ticket;");
			dbConn db2 = new dbConn(query2);	
			ResultSet rs2 = db2.select();
			
			
			String closedString = null;
			if (rs2.next()) {
				closed = rs2.getInt(1);
	            System.out.println("Amount of closed tickets: " + closed);
	            closedString = String.valueOf(closed);
			}
			
			System.out.println(closed);
			
			JLabel ticketLabel = new JLabel("Control of tickets");
			
			JLabel ticketOpenLabel = new JLabel("Tickets Open: ");
			JLabel ticketClosedLabel = new JLabel("Tickets Closed: ");
			//String opened = "XX";
			
			JLabel numberOfOpenLabel = new JLabel(openedString);
			JLabel numberOfClosedLabel = new JLabel(closedString);
			int costClosed = closed * 50;
			float costOpen = opened * 50;
			JLabel costClosedT = new JLabel("Total Cost of Closed Ticket: "+costClosed+"€");
			JLabel costOpenT = new JLabel("Total Cost of the ticket need to be close: "+costOpen+"€");
			
			JPanel tabletypeOfUser = new JPanel();
			tabletypeOfUser.setBackground(Color.LIGHT_GRAY);
			
			JPanel tableBtnPanel = new JPanel();
			tableBtnPanel.setBackground(Color.LIGHT_GRAY);
			tableBtnPanel.add(refresh);
			tableBtnPanel.add(quit);
			
			
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			gbc.gridx = 1;  // x = 0
			gbc.gridy = 0; //  y = 0
			gbc.gridwidth = 3;  //uses all width of the 0,0
			gbc.gridheight = 1; //uses all height of the 0,0
			gbc.fill = GridBagConstraints.BOTH; // Allows componet use whole space
			panel.add(ticketLabel, gbc);  // add label "Tickets"
			panel.setBackground(Color.LIGHT_GRAY);
			
			
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;  //uses all width of the 0,0
			      gbc.insets = new Insets(0,20,0,20); // top, left, bottom then right. (gap from the componate to border)
			      gbc.weightx = 1;     //grows on X if resized
			      gbc.weighty = 1;	//grows on y if resized
			      panel.add(ticketOpenLabel, gbc);
			      
			gbc.gridx = 2;
			gbc.gridy = 1;
			gbc.gridwidth = 2;  //uses all width of the 0,0
			      panel.add(numberOfOpenLabel, gbc);
			      
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 1;  //uses all width of the 0,0
			      panel.add(ticketClosedLabel, gbc);
			      
			gbc.gridx = 2;
			gbc.gridy = 2;
			gbc.gridwidth = 2;  //uses all width of the 0,0
			      panel.add(numberOfClosedLabel, gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 3;
			gbc.gridwidth = 2;  //uses all width of the 0,0
			      panel.add(costClosedT, gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 4;
			gbc.gridwidth = 2;  //uses all width of the 0,0
			panel.add(costOpenT, gbc);			
			
			gbc.gridy = 5; //  y = 2
			      gbc.weighty = 3;	//doesn't  grow on y if resized
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
	        refresh.addActionListener(Handler);
	        quit.addActionListener(Handler);
	        
	        refresh.setActionCommand("refresh");
	        quit.setActionCommand("quit");
	        
	        setSize(500,500);
			setVisible(true);
			this.setLocationRelativeTo(null);
				
	    	
	    	validate();
			repaint();
	//		String name2 = name;
			
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
                JOptionPane.showMessageDialog(Manager.this,
                         "This a Ticket System\n" +
                         "Developed by Thiago Farias\n" +
                         "College of Computer Technology\n" +
                         "December 2017.", "Ajuda", JOptionPane.PLAIN_MESSAGE);
					}
            
            // Buttons 
				else if (e.getActionCommand().equals("refresh")) {
            
        					setVisible(false);
        				    dispose();
        				    String name = null;
        					try {
								new Manager(name);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        					
        				}
				else if (e.getActionCommand().equals("quit")) {
					
					setVisible(false);
			        dispose();
			        // close window
             	System.out.println("quit");
             		if (e.getSource() == close){}
				}
			} // end of class ButtonHandler
		}
}
