package net.scandroidz.weblayer;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class WebLayerUtil {

	public static String session;
	private static HttpClient httpclient = new DefaultHttpClient();
	
	public static void refreshSession(){
	    HttpGet httpGet = new HttpGet("http://172.16.1.11:8888/api/auth?username=user&password=useruser");
	    try {
	        // Execute HTTP Post Request
	    	if(!validSession(session)) { 
		        HttpResponse response = httpclient.execute(httpGet);
		        HttpEntity responseEntity = response.getEntity();
		        if(responseEntity!=null) {
		            JSONObject responseJSON = new JSONObject(EntityUtils.toString(responseEntity));
		            session = (String) responseJSON.get("sid");
		        } 
	    	}
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean validSession(String sid) {
		HttpGet httpGet = new HttpGet("http://172.16.1.11:8888/api/validate?sid="+sid);
		try {
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httpGet);
	        HttpEntity responseEntity = response.getEntity();
	        if(responseEntity!=null) {
	        	String result = EntityUtils.toString(responseEntity);
	            if(result.equals("\"valid session\"")) {
	            	return true;
	            }
	        }
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } catch (ParseException e) {
			e.printStackTrace();
		}
		return false; 
	}

	public static void scanBarcode(String contents) {
	    HttpGet httpGet = new HttpGet("http://172.16.1.11:8888/api/scan?sid="+session+"&barcode="+contents);

	    try {
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httpGet);
	        HttpEntity responseEntity = response.getEntity();
	        if(responseEntity!=null) {
	        	String result = EntityUtils.toString(responseEntity);
	            //JSONObject responseJSON = new JSONObject(result);
	            System.out.println(result);
	        }
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } catch (ParseException e) {
			e.printStackTrace();
		} 
	}
	
}
