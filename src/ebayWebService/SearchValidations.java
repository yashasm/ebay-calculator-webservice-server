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
public class SearchValidations {
	public String searchItem(String query){
		
		Connection conn = MySqlConnection.getConnection();
		PreparedStatement preparedStatement;
		

		String query1 = "SELECT * FROM itemdata where  itemid = ?";
		try {
			preparedStatement = conn.prepareStatement(query1);
			preparedStatement.setString(1, query);
						
			
			
			
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
	
	public String searchData(String query){
		Connection conn = MySqlConnection.getConnection();
		PreparedStatement preparedStatement;
		

		String query1 = "SELECT itemid,itemname,itemprice,itemavailable,itemsold,itemauction,itemstartingbid,numberbids FROM itemdata where  onsale = 1 && (itemname like '%?%' or category like '%?%' or itemowner like '%?%')";
		
		try {
			preparedStatement = conn.prepareStatement(query1);
			preparedStatement.setString(1, query);
			preparedStatement.setString(2, query);
			preparedStatement.setString(3, query);
						
			
			
			
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
	
	
	public String getMyPurchaseHistory(String email){
		String query1 = "SELECT itemname,itemprice,quantity,itemowner,orderid,shippingaddress,orderprice FROM purchase_history where customerid = ?";

		try {
			Connection conn = MySqlConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(query1);
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
	
	
	public String getMyCollectionData(String email){
		String collection = "SELECT itemid,itemname,itemprice,itemavailable,itemsold,currentbid,numberbids,itemauction FROM itemdata where  itemowner = ?";

		try {
			Connection conn = MySqlConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(collection);
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
