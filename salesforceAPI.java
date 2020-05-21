package com.test;
import java.io.*;
import java.net.*;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.*;
class salesforceAPI 
{
	private static final String CLIENTID = "clientid";
    private static final String CLIENTSECRET = "secret";
    private static final String USERID = "user@.com";
    private static final String PASSWORD = "password";
    private static final String userSSO = "sso";
	public static void main(String[] args)   {
		
		JSONObject json;
		String accessToken;
		String json_resp;
		try {
			Properties systemProperties = System.getProperties();
			//systemProperties.setProperty( "http.proxyHost", "http://127.0.0.1" );
			//systemProperties.setProperty( "http.proxyPort", "9000" );
			//systemProperties.setProperty( "https.proxyHost", "https://127.0.0.1" );
			//systemProperties.setProperty( "https.proxyPort", "9000" );
			JFrame frame = new JFrame();
		    Object output = JOptionPane.showInputDialog(frame, "Enter the API Object:");
		    String sObject = (String) output;
			String sUrl = "client_id="+ CLIENTID + "&client_secret=" + CLIENTSECRET + "&username=" + USERID + "&password=" + PASSWORD ;
			URL url = new URL("https://my.salesforce.com/services/oauth2/token?grant_type=password&"+sUrl); 
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setDoOutput(true);
	        //con.setReadTimeout(15*1000);
	        con.setRequestProperty("Content-Type", "application/json");
	        con.setRequestProperty("Accept", "application/json");
	        con.setRequestMethod("POST");
	        //System.out.println(con.getResponseCode());
	        //System.out.println(sUrl);
	        DataOutputStream wr= new DataOutputStream(con.getOutputStream());
	        wr.flush();
	        wr.close();
	        Reader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    StringBuilder sb = new StringBuilder();
	        for (int c; (c = in.read()) >= 0;)
		    sb.append((char)c);
		    String HTTPOUT = sb.toString();
			//System.out.println(HTTPOUT);
			json = new JSONObject(HTTPOUT);
			accessToken = json.getString("access_token");
			String cookie = "Bearer "+ accessToken;
			//System.out.println(cookie);
			String baseURL = "https://my.salesforce.com/services/data/v44.0/sobjects/"+sObject+"/describe";
			json_resp = getAttributes(baseURL, cookie);
			System.out.println(json_resp);
			//parseJsonAttributes(json_resp,sObject);
			System.out.println(sObject+ " Metadata File has been generated and saved");
			System.exit(0);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	static String getAttributes(String url,String cookie)
	  {
		HttpURLConnection conn;
		URL urlval;
		String json="";
		try {
			urlval = new URL(url); 
			conn = (HttpURLConnection)urlval.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", cookie);
			//conn.setUseCaches(true); 
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			//System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				Reader in2 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb2 = new StringBuilder();

				for (int c; (c = in2.read()) >= 0;)
				sb2.append((char)c);
			//System.out.println(sb2.toString());
			json = sb2.toString();
			
	} else { }
		}
		catch (Exception e)
		 {
		    e.printStackTrace();
		 }
		return json;
}
	
	static void parseJsonAttributes(String json_resp,String sObject)
	{
		String sline ="";
		String jdata;
		JSONArray  jsonArray = null;
		JSONObject jobj;
		jdata = json_resp;
		String outFile = "C:\\Users\\pan698\\"+ sObject + "_meta.csv";
		try (PrintWriter writer = new PrintWriter(new File(outFile))){
        StringBuilder sb = new StringBuilder();
        sb.append("API_Name");
        sb.append(',');
        sb.append("Label_Name");
        sb.append(',');
        sb.append("Nillable");
        sb.append(',');
        sb.append("Datatype");
        sb.append(',');
        sb.append("Length");
        sb.append(',');
        sb.append("Scale");
        sb.append('\n');
		  if(!jdata.equals("{}")) {
			 jobj = new JSONObject(jdata);
			 jsonArray= jobj.getJSONArray("fields");
		}
		   if(!jsonArray.get(1).equals("null")) {
						
			 for (int i = 0, size = jsonArray.length(); i < size; i++)
			    {
			      JSONObject objectInArray = jsonArray.getJSONObject(i);
			      
			       	  sline =  objectInArray.getString("name") + "," + objectInArray.getString("label") + "," + objectInArray.getString("nillable")+","+objectInArray.getString("type") +","+ objectInArray.getString("length")+ "," + objectInArray.getString("scale");
				      sb.append(sline);
				      sb.append('\n');
			      }
				    
			 	writer.write(sb.toString());
			 	writer.close();

		        System.out.println("done!");
		   }
		
		}
			catch (Exception e)
			{
				System.out.println("InvalidSession") ;
			}
	}
	
}

