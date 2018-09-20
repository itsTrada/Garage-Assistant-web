package database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import Model.Member;

public class DatabaseHandler {
    private static DatabaseHandler handler = null;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/garagemanagement?useUnicode=true&characterEncoding=UTF-8";
    private static final String USR = "root";
    private static final String PWD = "toor";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    private DatabaseHandler() {
        createConnection();      
    }
    
    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler; //reuse if already existed
    }
    
    void createConnection() { //create the connection between app & database using JDBC
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();//install driver
            conn = DriverManager.getConnection(DB_URL, USR, PWD);
        } catch (Exception e) {//another app is running
            System.exit(0);//close the newer app
        }
    }
    
    public boolean handleLogin(Member member) {
    	boolean flag = false;
    	
    	String loginStmt = "SELECT * FROM MEMBER WHERE email = ? AND password = ?";
    	try {
			PreparedStatement stmt = conn.prepareStatement(loginStmt);
			
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			
			ResultSet rs = stmt.executeQuery();
            if ( rs.next() ) {
            	flag = true;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return flag;
    }
    
    public boolean isEmailExisted(Member member) {
    	boolean flag = true;
    	
    	String checkEmailStmt = "SELECT * FROM MEMBER WHERE email = ?";
    	try {
			PreparedStatement stmt = conn.prepareStatement(checkEmailStmt);
			
			stmt.setString(1, member.getEmail());
			
			ResultSet rs = stmt.executeQuery();
            if ( rs.next() ) {
            	flag = false;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return flag;
    }
    
    public boolean isIdMemberExisted(Member member) {
    	boolean flag = true;
    	
    	String checkIdStmt = "SELECT * FROM MEMBER WHERE idMember = ?";
    	try {
			PreparedStatement stmt = conn.prepareStatement(checkIdStmt);
			
			stmt.setString(1, member.getEmail());
			
			ResultSet rs = stmt.executeQuery();
            if ( rs.next() ) {
            	flag = false;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return flag;
    }
    
    public static boolean validateEmailAddress(String emailID) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(emailID).matches();
    }

}
