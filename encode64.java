package com.test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import  java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.lang.Math; 
class encode64
{
	public static void main(String[] args)
	{
		//try {
		//String originalInput = "";
		//byte[] decodedBytes = Base64.getDecoder().decode(originalInput);
		//String decodedString = new String(decodedBytes);
		
			//Generate PROCESS DATE input parametes for last 7 and next 7 days for calling the schedule API.
			int i,j;
			
			String inDate = "2018-11-10";
			String dt ="";
			 try {	
			 for (i=0;i<7;i++) //last 7 days
			 { 
			   DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			   Calendar c = Calendar.getInstance();
			   c.setTime(sdf.parse(inDate));
			   c.add(Calendar.DATE, -1);
			   dt = sdf.format(c.getTime());
			   inDate = dt;
			   System.out.println(dt);
			   
			 }
			 inDate = "2018-11-10";
			for (j=0;j<7;j++) //next 7 days
			 { 
				System.out.println(inDate);
			   DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			   Calendar c = Calendar.getInstance();
			   c.setTime(sdf.parse(inDate));
			   c.add(Calendar.DATE, 1);
			   dt = sdf.format(c.getTime());
			   inDate = dt;
			   //System.out.println(dt);
			 }
					 
		 
		 
		}
		
	
		catch(Exception e)
		{
			String Error_String = "InvalidSession" ;
			
		}
    }
}