import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;




public class RESTHandler {

	//Send create or update user to DB
	public static void postRequest(User u) throws IOException, InterruptedException {
		HttpURLConnection con;
		URL url;
		
		//Start measuring time
		long start = System.currentTimeMillis();
		
		//Check if its an update or create request and choose URL accordingly
		if(u.getId().equals("")){
			url = new URL ("http://localhost:8081/users");
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
		} else {
			url = new URL ("http://localhost:8081/users/"+u.getId());
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("PUT");
		}
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		//Create output stream - convert to JSON and write to outputstream using ObjectMapper 
		try(java.io.OutputStream os = con.getOutputStream()) {	
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(os, u);     
		}
		
		//Wait for respond code 
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "utf-8"))){
				    StringBuilder response = new StringBuilder();
				    String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				        response.append(responseLine.trim());
				    }
				    
				    //Stop measuring time and print time
		        	long stop = System.currentTimeMillis();
		        	System.out.println(stop - start + " millisecs");
		}
		
	}
	
	//Send delete request for user with UserID matching parameter String s
	public static void deleteRequest(String s) throws IOException{
		URL url = new URL("http://localhost:8081/users/"+s);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("DELETE");
		int responseCode = con.getResponseCode();
	}
	
	//Send request to read all users
	public static ArrayList<User> readJSON(){
        try {
        	//Start measuring time
        	long start = System.currentTimeMillis();
        	
        	//Send request to URL through ObjectMapper and parse JSON to ArrayList of User
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        	ArrayList<User> users = objectMapper.readValue(new URL("http://localhost:8081/users/"), new TypeReference<ArrayList<User>>(){});
        	
        	//Stop measuring and print time
        	long stop = System.currentTimeMillis();
        	System.out.println(stop - start + " millisecs");
        	
        	return users;
        		
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

	}

	
	//Send read request to URL given as String parameter
	public static ArrayList<User> readJSON(String string) {
        try {
        	//Start measuring time
        	long start = System.currentTimeMillis();
        	
        	//Send request to URL in string and parse answer from JSON to ArrayList of USER through objectmapper 
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        	ArrayList<User> users = objectMapper.readValue(new URL(string), new TypeReference<ArrayList<User>>(){});
        	
        	//Stop measuring and print time
        	long stop = System.currentTimeMillis();
        	System.out.println(stop - start + " millisecs");
        	
        	return users;
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
		
	}
	
	//Send read request for unique user with UserID as paramter string
	public static ArrayList<User> readIDJSON(String string) {
        try {
        	//Start measuring time
        	long start = System.currentTimeMillis();
        	
        	//Send request for user and put User alone in arrayList
        	ObjectMapper objectMapper = new ObjectMapper();
        	User u = objectMapper.readValue(new URL(string), User.class); 
        	ArrayList<User> users = new ArrayList<User>();
        	users.add(u);
        	
        	//Stop measuring time
        	long stop = System.currentTimeMillis();
        	System.out.println(stop - start + " millisecs");

        	return users;
        	
    		
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
		
	}
}