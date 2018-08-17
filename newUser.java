
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


	public class newUser extends JFrame {
		
		
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
            
            JButton sav = new JButton("Save");
            JButton quit = new JButton("Quit");
            
            DefaultTableModel model;
            JTable table;
            
            String[] messageStrings = {"Tech", "Admin", "Manager"};
			 JComboBox cmbMessageList = new JComboBox(messageStrings);
           
            
		public newUser() {
			
			id = new JTextField(20);
			
			FirstName = new JTextField(20);
			/*FirstName.setBounds(100, 10, 160, 25);
			this.add(FirstName);*/
			SecondName = new JTextField(20);
			/*SecondName.setBounds(100, 10, 160, 25);
			this.add(SecondName);*/
			Age = new JTextField(20);
			/*Age.setBounds(100, 10, 160, 25);
			this.add(Age);*/
			Password = new JTextField(20);
			
			
			this.setTitle("Adding a new user");
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
	       
	        cmbMessageList.setSelectedItem(1);
			
			
			
	        // PANEL
			JPanel panel = new JPanel(new GridBagLayout());
			this.getContentPane().add(panel);
			
			//creating jtable
			
			//Initial of connection 
			//String ticketNumber = TicketNumber;
	    	    
			
			JLabel ticketLabel = new JLabel("Adding a new User");
			
			JLabel firstnameLabel = new JLabel("First Name: ");
			JLabel secondnameLabel = new JLabel("Second Name: ");
			JLabel ageLabel = new JLabel("Age: ");
			JLabel passwordLabel = new JLabel("Password: ");
			
			JLabel typeOfUser = new JLabel("Type of User");
			
			JPanel tabletypeOfUser = new JPanel();
			tabletypeOfUser.add(cmbMessageList);
			tabletypeOfUser.setBackground(Color.LIGHT_GRAY);
			
			JPanel tableBtnPanel = new JPanel();
			tableBtnPanel.setBackground(Color.LIGHT_GRAY);
			tableBtnPanel.add(sav);
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
	        panel.add(firstnameLabel, gbc);
	        
			gbc.gridx = 2;
			gbc.gridy = 1;
			gbc.gridwidth = 2;  //uses all width of the 0,0
	        panel.add(FirstName, gbc);
	        
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 1;  //uses all width of the 0,0
	        panel.add(secondnameLabel, gbc);
	        
			gbc.gridx = 2;
			gbc.gridy = 2;
			gbc.gridwidth = 2;  //uses all width of the 0,0
	        panel.add(SecondName, gbc);
	        
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 1;  //uses all width of the 0,0
	        panel.add(ageLabel, gbc);
	        
			gbc.gridx = 2;
			gbc.gridy = 3;
			gbc.gridwidth = 2;  //uses all width of the 0,0
	        panel.add(Age, gbc);
	        
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.gridwidth = 1;  //uses all width of the 0,0
	        panel.add(passwordLabel, gbc);
	        
	        gbc.gridx = 2;
			gbc.gridy = 4;
			gbc.gridwidth = 2;  //uses all width of the 0,0
	        panel.add(Password, gbc);
	        
			gbc.gridx = 1;  // x = 0
			gbc.gridy = 5; //  y = 0
			gbc.gridwidth = 3;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(4,0,0,0); 
			panel.add(typeOfUser, gbc);  // add label "Type of User"
			
			gbc.gridx = 1;  // x = 0
			gbc.gridy = 6; //  y = 2
	        gbc.weighty = 3;	//doesn't  grow on y if resized
	        gbc.insets = new Insets(0,0,0,0); 
			panel.add(tabletypeOfUser, gbc);  //Add table with buttons "edit" and "New Ticket" 
			
			gbc.gridy = 7; //  y = 2
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
	        sav.addActionListener(Handler);
	        quit.addActionListener(Handler);
	        
	        sav.setActionCommand("sav");
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
                JOptionPane.showMessageDialog(newUser.this,
                         "This a Ticket System\n" +
                         "Developed by Thiago Farias\n" +
                         "College of Computer Technology\n" +
                         "December 2017.", "Ajuda", JOptionPane.PLAIN_MESSAGE);
					}
            
            // Buttons 
				else if (e.getActionCommand().equals("sav")) {
            	Connection conn = null;
            	Statement stmt = null;
        				try {
        					
        					java.util.Date dt = new java.util.Date();
        					java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        					String currentTime = sdf.format(dt);
        					
        					String FirstNameString = FirstName.getText();
        					String SecondNameString = SecondName.getText();
        					String AgeString = Age.getText();
        					String PasswordString = Password.getText();
        				//	String OpenOn = currentTime;
        					
							String replyDB = null;
        					String selectedBook = replyDB;
        					
        					JComboBox msg = cmbMessageList;
        			     	String user = (String)msg.getSelectedItem();
        			     	System.out.println("You seleted the user type as: " + user);
        				
        					 conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/TicketLoggingSystem?user=root&password=");
        				     stmt = conn.createStatement();
        				    
         					stmt.execute("INSERT INTO  Login (`name`,`password`,`type`,`SecondName`,`Age`) VALUES ('"+FirstNameString+"','"+PasswordString+"','"+user+"','"+SecondNameString+"','"+AgeString+"');");
							System.out.println("2 - You seleted the book: " + selectedBook);
        					JOptionPane.showMessageDialog(Panel, "New user was successfully inserted");
        					
        					setVisible(false);
        				    dispose();
        				    String name;
							newUser classe = new newUser();
							name = (String)classe.getName();
        					new SystemAdmin(selectedBook);
        					
        				}
        				catch(SQLException ex) {
        					System.out.println("SQLException: " + ex.getMessage());
        					System.out.println("SQL State: " + ex.getSQLState());
        					System.out.println("VendorError: " + ex.getErrorCode());
        				}
					}
				else if (e.getActionCommand().equals("quit")) {
					setVisible(false);
			        dispose();
			        // close window
             	System.out.println("quit");
             	try {
					new SystemAdmin(getName());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
			} // end of class ButtonHandler
		}
}
