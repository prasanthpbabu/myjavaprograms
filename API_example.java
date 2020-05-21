package com.test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

class Calabrio_api
{
	
public static void main(String[] args)   
{
 
String HTTPOUT, emp_id,i_Cookie;
String v_agentId = "31";
i_Cookie = "cookie";
String urlval = "https://calabriocloud.com/api/rest/org/common/person/" + v_agentId;
try {
URL url = new URL(urlval);
HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//conn.setReadTimeout(15*1000);
conn.setRequestProperty("Content-Type", "application/json");
conn.setRequestProperty("Accept", "application/json");
conn.setRequestProperty("Cookie", i_Cookie);
//conn.setUseCaches(true); 
conn.setRequestMethod("GET");
int responseCode = conn.getResponseCode();
if (responseCode == HttpURLConnection.HTTP_OK) { // success
// read the output from the server
Reader in2 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
StringBuilder sb2 = new StringBuilder();
for (int c; (c = in2.read()) >= 0;)
sb2.append((char)c);
HTTPOUT=sb2.toString();
JSONObject jsonobj = new JSONObject(HTTPOUT);
emp_id = jsonobj.getString("employeeId");
System.out.println(emp_id);
System.out.println(HTTPOUT);
}
else { System.out.println("Connection error:"+ responseCode);}
}
catch (Exception e)
 {
    System.out.println( "Error Message is" + e);

}
}
}