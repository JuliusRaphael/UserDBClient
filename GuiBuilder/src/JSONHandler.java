import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JSONHandler {

	
	public static ArrayList<User> readJSON(){
		
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        	ArrayList<User> users = objectMapper.readValue(new URL("http://localhost:8081/users"), new TypeReference<ArrayList<User>>(){});
        	System.out.println(users.toString());
        	return users;
        	
    		
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

	}
}