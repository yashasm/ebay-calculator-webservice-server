package ebayWebService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import sql.MySqlConnection;

public class Accounts {
	public String setAccountDetails(String email, String firstname, String lastname, String phone, String ebayHandle,String birthday, String address, String cardNumber, String expiry, String cvv){
		int rs;
		Connection conn = MySqlConnection.getConnection();
		PreparedStatement preparedStatement;
		

		String query1 = "UPDATE userdata SET firstname = ?,lastname = ?, phone = ?,ebayHandle = ?,birthday = ?, address = ?,cardnumber = ?, expiry = ?,cvv = ? WHERE email = ?";
		
		try {
			preparedStatement = conn.prepareStatement(query1);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, ebayHandle);
			preparedStatement.setString(5, birthday);
			preparedStatement.setString(6, address);
			preparedStatement.setString(7, cardNumber);
			preparedStatement.setString(8, expiry);
			preparedStatement.setString(9, cvv);
			preparedStatement.setString(10, email);
			
			
									
			
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
	
	
	public String getAccountDetails(String email){
		
		Connection conn = MySqlConnection.getConnection();
		PreparedStatement preparedStatement;
		

		String accountQuery = "SELECT firstname,lastname,phone,handle,birthday,address,cardnumber,expiry,cvv FROM userdata where email = ?";
		
		try {
			preparedStatement = conn.prepareStatement(accountQuery);
			preparedStatement.setString(1, email);								
			
			
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.last();
			if(rs.getRow()>0){
				JSONObject obj = new JSONObject();
				try {
					
					obj.put("statusCode", 200);
					obj.put("result", rs);					 
					 
					 
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
			return "401";
		}
		return "401";



	}
}
