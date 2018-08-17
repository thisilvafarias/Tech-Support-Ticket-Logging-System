import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Login extends JFrame implements ActionListener {
	
	JTextField id = null;
	JTextField password = null;
	
	public static void main(String[] args) {
		new Login();
	}
	
	//Default constructor for this class
	public Login(){
		
		//Setting attributes for the frame
		setSize(300,150);
		setVisible(true);
		setTitle("Ticket Logging System");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		JLabel userLabel = new JLabel("User ID");
		userLabel.setBounds(10, 10, 80, 25);
		this.add(userLabel);
		
		//Adding text field
		id = new JTextField(20);
		id.setBounds(100, 10, 160, 25);
		this.add(id);
		id.requestFocusInWindow();
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		this.add(passwordLabel);
		
		//Adding text field
		password = new JTextField(20);
		password.setBounds(100, 40, 160, 25);
		this.add(password);
		
		//Adding button
		JButton login = new JButton("Login!");
		login.setBounds(110, 80, 80, 25);
		login.addActionListener(this);
		login.setActionCommand("login");
		this.add(login);
		
		this.validate();
		this.repaint();
		
	}
	
	//This is the action listener. In here we indicate what to do when different buttons are pressed.
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// if you want to set a label for each of the buttons
		// and then redirect the user to a different part of the program
		// you can use the getActionCommand to check which button
		// has sent the request
		if(e.getActionCommand().equals("login")){
			
			// When button login is pressed, we call this method.
			loginWithDatabase();
		}
			
	}

	//This is the method that we call when the login button is pressed
	public void loginWithDatabase(){
		
    	try {
    	    String idEntered = id.getText();
    	    String passwordEntered = password.getText();
    	    
    	    String query = "select * from Login where userID = '"+idEntered+"' and password = '"+passwordEntered+"'";
    	    dbConn db = new dbConn(query);
    		ResultSet rs = db.select();
    	    

    	    if(!rs.next()) { //if it is empty, show a dialog saying the user doen't exists.
    	    	JOptionPane.showMessageDialog(this, "That user doen't exist");
    	    	
    	    }
    	    else { //If we have in did one result
    	    	//We can evaluate the type of the user
    	    	if (rs.getString("type").equals("Admin")) { // If it is admin(is a type in the db), create a new instance of the class admin
    	    		new SystemAdmin(rs.getString("name"));
    	    		setVisible(false);
			        dispose();
    	    		
    	    	}
    	    	else if (rs.getString("type").equals("Manager")) { // if it is manager(is a type in the db), create a new instance of the class Manager
    	    		new Manager(rs.getString("name"));
    	    		setVisible(false);
			        dispose();
    	    		
    	    	}
    	    	else if (rs.getString("type").equals("Tech")) { // if it is tech(is a type in the db), create a new instance of the class TechSupport
    	    		new TechSupport(rs.getString("name"));
    	    		setVisible(false);
			        dispose();
    	    		
    	    	}
    	    	
    	    }

    	} catch (SQLException ex) {
    	    // handle any errors
    	    System.out.println("SQLException: " + ex.getMessage());
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
    	}
    	
   
    	
    	
	}

}
