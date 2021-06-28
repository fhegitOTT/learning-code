package com.netcom.nkestate.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	private static String readString(InputStream inStream) throws Exception{
		
		StringBuffer sb = new StringBuffer("");
		BufferedReader reader = null;
		
		try {
			
			reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

			String str = null;

			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}
	
	public static String sendGet(String strURL) throws Exception{
		
		HttpURLConnection connection = null;
		InputStream responseInputStream = null;
		String responseString = "";
		
		try{
			URL url = new URL(strURL);
			connection = ((HttpURLConnection) url.openConnection());
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
	
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.connect();
			
			responseInputStream = connection.getInputStream();
			
			responseString = readString(responseInputStream);
		}
		finally{
			if (responseInputStream != null){
				try {
					responseInputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (connection != null){
				connection.disconnect();
				connection = null;
			}
		}
		
		
		return responseString;
	}

	public static String sendPost(String strURL, String postData) throws Exception{
		
		HttpURLConnection connection = null;
		InputStream postInputStream = null;
		InputStream responseInputStream = null;
		OutputStream os = null;
		String responseString = "";

		try {
			URL url = new URL(strURL);
			connection = ((HttpURLConnection) url.openConnection());
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
	
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
		

			if ((postData != null) && (!postData.equals(""))) {
				postInputStream = new ByteArrayInputStream(postData.getBytes("UTF-8"));

				os = connection.getOutputStream();
				int length = 5000;
				byte[] bytes = new byte[length];
				int bytesRead = 0;

				while ((bytesRead = postInputStream.read(bytes, 0, length)) > 0) {
					os.write(bytes, 0, bytesRead);
				}

				os.flush();
			}

			responseInputStream = connection.getInputStream();
			responseString = readString(responseInputStream);
		} 
		finally {
			if (responseInputStream != null){
				try {
					responseInputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (os != null) {
				try {
					os.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (postInputStream != null) {
				try {
					postInputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (connection != null){
				connection.disconnect();
				connection = null;
			}
		}

		return responseString;
	}


	public static byte[] sendPostForMap(String strURL,String postData) throws Exception {

		HttpURLConnection connection = null;
		InputStream postInputStream = null;
		InputStream responseInputStream = null;
		OutputStream os = null;
		String responseString = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream(2048 * 3);
		try {
			URL url = new URL(strURL);
			connection = ((HttpURLConnection) url.openConnection());
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");


			if ( (postData != null) && (!postData.equals(""))) {
				postInputStream = new ByteArrayInputStream(postData.getBytes("UTF-8"));

				os = connection.getOutputStream();
				int length = 5000;
				byte[] bytes = new byte[length];
				int bytesRead = 0;

				while ( (bytesRead = postInputStream.read(bytes, 0, length)) > 0) {
					os.write(bytes, 0, bytesRead);
				}

				os.flush();
			}

			responseInputStream = connection.getInputStream();
			for (int len; (len = responseInputStream.read()) != -1;)
				baos.write(len);
			baos.flush();

			return baos.toByteArray();
		} finally {
			if (responseInputStream != null) {
				try {
					responseInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (postInputStream != null) {
				try {
					postInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				connection.disconnect();
				connection = null;
			}
		}

	}

}
