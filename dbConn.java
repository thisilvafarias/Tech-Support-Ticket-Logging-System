import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConn {
	
	String query;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public dbConn(String query) {
		
		this.query = query;
		
		try {
			
			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		}catch(Exception e ){}
		
		try {
    	    conn =
    	    //   DriverManager.getConnection("jdbc:mysql://127.0.0.1/TicketLoggingSystem?zeroDateTimeBehavior=convertToNull");
    	       DriverManager.getConnection("jdbc:mysql://127.0.0.1/TicketLoggingSystem?user=root&password=");
    	    							//	jdbc:mysql://localhost:3306/db?zeroDateTimeBehavior=convertToNull
    	    //	DriverManager.getConnection("jdbc:mysql://127.0.0.1/TicketLoggingSystem?user=root&password=");
    	    // Do something with the Connection
    	    stmt = conn.createStatement();
    	    
		}
		catch (SQLException ex) {
    	    // handle any errors
    	    System.out.println("SQLException: " + ex.getMessage());
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
    	}
	}

	public ResultSet select() {
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void insert() {
		try {
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
