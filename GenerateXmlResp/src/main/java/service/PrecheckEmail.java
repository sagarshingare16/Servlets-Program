package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.JdbcUtil;

public class PrecheckEmail {
	
	public static String precheckEmail() {
		StringBuilder xmlBuilder = new StringBuilder();

        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuilder.append("<users>");
        
        try(Connection connection = JdbcUtil.getConnection() ){
        	
        	String sql = "SELECT id, uname, email, upassword, ucity FROM users";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
            	int id = rs.getInt("id");
                String uname = rs.getString("uname");
                String email = rs.getString("email");
                String upassword = rs.getString("upassword");
                String ucity = rs.getString("ucity");
                
                xmlBuilder.append("<user>");
                xmlBuilder.append("<id>").append(id).append("</id>");
                xmlBuilder.append("<uname>").append(uname).append("</uname>");
                xmlBuilder.append("<email>").append(email).append("</email>");
                xmlBuilder.append("<upassword>").append(upassword).append("</upassword>");
                xmlBuilder.append("<ucity>").append(ucity).append("</ucity>");
                xmlBuilder.append("</user>");
            }
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "<error>Unable to fetch data</error>";
		}
        xmlBuilder.append("</users>");
        return xmlBuilder.toString();
	}
}
