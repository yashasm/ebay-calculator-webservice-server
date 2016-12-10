package ebayWebService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import sql.MySqlConnection;

public class Bidding {
	
	public String getMyBidingHistory(String myMail){
		
		Connection conn = MySqlConnection.getConnection();
		PreparedStatement preparedStatement;
		

		String query1 = "SELECT * FROM bid_details where customerid = ?";
		
		try {
			preparedStatement = conn.prepareStatement(query1);
			preparedStatement.setString(1, myMail);								
			
			
			
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
	public String saveBid(int bidamount, int itemid, String itemname, String seller, String email){
		
		
		String insertSql = "Update itemdata set numberbids = numberbids + 1, currentbid = ? where itemid =?;INSERT into bid_details (itemid,itemname,itemowner,customerid,bidingamount) values (?,?,?,?,?);";
		int rs;
		Connection conn = MySqlConnection.getConnection();
		PreparedStatement preparedStatement;

		try{
		preparedStatement = conn.prepareStatement(insertSql);
		preparedStatement.setInt(1, bidamount);
		preparedStatement.setInt(2, itemid);
		preparedStatement.setInt(3, itemid);
		preparedStatement.setString(4, itemname);
		preparedStatement.setString(5, seller);
		preparedStatement.setString(5, email);
		preparedStatement.setInt(5, bidamount);
		
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
