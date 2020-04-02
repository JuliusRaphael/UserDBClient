import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.portable.OutputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;




public class RESTHandler {

	public static void postRequest(User u) throws IOException, InterruptedException {
		HttpURLConnection con;
		URL url;
		
		
		if(u.getId().equals("")){
			url = new URL ("http://localhost:8081/users");
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
		} else {
			System.out.println("i update");
			url = new URL ("http://localhost:8081/users/"+u.getId());
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("PUT");
		}
		
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		try(java.io.OutputStream os = con.getOutputStream()) {
			
			ObjectMapper objectMapper = new ObjectMapper();
			System.out.println(objectMapper.writeValueAsString(u));
			objectMapper.writeValue(os, u);     
		}
		
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "utf-8"))){
				    StringBuilder response = new StringBuilder();
				    String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				        response.append(responseLine.trim());
				    }
				    
				    System.out.println(response.toString());
		}
		
	}
	
	public static void deleteRequest(String s) throws IOException{
	
		URL url = new URL("http://localhost:8081/users/"+s);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("DELETE");
		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
	}
	
	public static ArrayList<User> readJSON(){
		
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        	ArrayList<User> users = objectMapper.readValue(new URL("http://localhost:8081/users/"), new TypeReference<ArrayList<User>>(){});
        	System.out.println(users.toString());
        	return users;
        	
    		
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

	}
}