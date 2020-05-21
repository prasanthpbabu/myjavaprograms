package com.test;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.*;
import java.net.URL;
import java.util.*;

public class donwloadFileRest {

    public static void main(String[] args) {
        String url = "URL";
        
        try {
            //downloadUsingNIO(url, "C:\Users\pan698\Downloads\sitemap.xml");
            
            downloadUsingStream(url, "C:\\Downloads\\file.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  private static void downloadUsingStream(String urlStr, String file) throws IOException{
	    
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        ByteArrayOutputStream baous = new ByteArrayOutputStream();
        //byte[] buffer = new byte[1024];
        int count=0;
        int temp = bis.read();
        while(temp != -1)
        {
            //System.out.write(buffer, 0, count);
        	baous.write((byte) temp);
        	temp = bis.read();
        }
        String CSV = baous.toString("UTF-8");
        Scanner scanner = new Scanner(CSV);
        while (scanner.hasNextLine()) {
        	String line = scanner.nextLine();
            count =count+1; 
            if(count>1)
            {	
            	String[] values = line.split(",");
            	for (int i = 0; i < values.length; i++) {
        		System.out.println(values[i].toString());
        	}
            	}
       }
        fis.close();
        bis.close();
        scanner.close();
    }

    /*private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }*/

}
