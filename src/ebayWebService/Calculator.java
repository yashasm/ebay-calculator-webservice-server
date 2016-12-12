package ebayWebService;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.json.JSONException;
import org.json.JSONObject;

public class Calculator {
	public String calculate(String expression) throws JSONException{
		
		String ans = "";
		
		try{
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			//Object result = engine.eval(expression);
			ans = (String) engine.eval(expression);
			
			//console.log("answer is "+ans);
		}
		catch(Exception e){
			//console.log("Error");	
			ans = "Invalid input";
		}
		if(ans == "Infinity"){
			ans = "Can not Divide by Zero";
		}
		JSONObject obj = new JSONObject();
		obj.put("answer", ans);
		
		return obj.toString();
	}
}
