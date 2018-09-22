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

import model.Member;

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
    
    public Member handleLogin(Member member) {
    	Member logedIn = new Member();
    	
    	String loginStmt = "SELECT * FROM MEMBER WHERE email = ? AND password = ?";
    	try {
			PreparedStatement stmt = conn.prepareStatement(loginStmt);
			
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			
			ResultSet rs = stmt.executeQuery();
            if ( rs.next() ) {
            	logedIn.setName(rs.getString("name"));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return logedIn;
    }
    
    public boolean isEmailExisted(String email) {
    	boolean flag = true;
    	
    	String checkEmailStmt = "SELECT * FROM MEMBER WHERE email = ?";
    	try {
			PreparedStatement stmt = conn.prepareStatement(checkEmailStmt);
			
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
            if ( rs.next() ) {
            	flag = false;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return flag;
    }
    
    public boolean isIdMemberExisted(String id) {
    	boolean flag = true;
    	
    	String checkIdStmt = "SELECT * FROM MEMBER WHERE idMember = ?";
    	try {
			PreparedStatement stmt = conn.prepareStatement(checkIdStmt);
			
			stmt.setString(1, id);
			
			ResultSet rs = stmt.executeQuery();
            if ( rs.next() ) {
            	flag = false;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return flag;
    }
    
    public Member handleRegister(Member member) {
    	Member registered = new Member();
    	
    	String signupStmt = "INSERT INTO MEMBER (idMember, name, mobile, email , password) VALUES ( ?, ?, ?, ?, ?)";
    	try {
			PreparedStatement stmt = conn.prepareStatement(signupStmt);
			
			stmt.setString(1, member.getId());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getMobile());
			stmt.setString(4, member.getEmail());
			stmt.setString(5, member.getPassword());
			
			stmt.executeUpdate();
			registered.setName(member.getName());

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return registered;
    }

}
