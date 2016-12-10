package ebayWebService;

import javax.jws.WebService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import sql.MySqlConnection;

/*import javax.ejb.Stateful;
import javax.ejb.Stateless;*/
import javax.jws.WebService;

import org.json.JSONException;
import org.json.JSONObject;



@WebService
public class validation {
	public String validate(String username, String password){
		System.out.println("started validation");
		System.out.println(username);
		System.out.println(password);
		
		Connection conn = MySqlConnection.getConnection();
		PreparedStatement preparedStatement;
		String query1 = "SELECT firstname,lastloggedin FROM userdata where email = ? and password = ?";
		try {
			preparedStatement = conn.prepareStatement(query1);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			rs.last();
			if(rs.getRow()>0){
				JSONObject obj = new JSONObject();
				try {
					
					obj.put("statusCode", 200);
					obj.put("id", rs.getString("email"));
					 obj.put("firstname", rs.getString("firstname"));
					 
					 
					 return obj.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
			else{
				JSONObject obj = new JSONObject();
				try {
					
					obj.put("statusCode", 401);
					
					 
					 return obj.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "401";
				
	}
	
	public String signup(String username, String password, String firstname, String lastname, String phone){
		int rs;
		Connection conn = MySqlConnection.getConnection();
		PreparedStatement preparedStatement;
		String query1 = "INSERT into userdata (username,password,firstname,lastname,phone) values(?,?,?,?,?)";
		try{
		preparedStatement = conn.prepareStatement(query1);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, firstname);
		preparedStatement.setString(4, lastname);
		preparedStatement.setString(5, phone);
		
		rs = preparedStatement.executeUpdate();
		
		if(rs > 0){
			return "200";
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		
		e.printStackTrace();
		return "401";
	}
		
		
		
		return "401";
	}
}
