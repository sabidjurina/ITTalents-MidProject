package NineGagProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class UserStorage { //class to store users
	
	
	private Map<String, User> users; // String - mail, User -user
	private static UserStorage storage;
	
	
	private UserStorage() throws InvalidDataException{
		this.users = new HashMap<String, User>();

		
	}
	//TODO mnooogo typ nachin
	public void setUserNames() {
		for (Map.Entry<String, User> en : users.entrySet()) {
			User us = en.getValue();
			us.setUserName();
		}
	}
	
//	private void addAdminToSite() throws InvalidDataException {
//		PersonInformation admin = Admin.giveAdmin();
//		//this.users.put(admin.getEmail(), admin);
//	}
	
	public void toJson() throws InvalidDataException {

		//addAdminToSite();
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setPrettyPrinting()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
				.create();
		
		File jsonStorage = new File("src\\NineGagProject\\jsonStorage.json");
		if (!jsonStorage.exists()) {
			try {
				jsonStorage.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String jsonUserStorage = gson.toJson(new HashMap(this.users));
		
		try (PrintWriter pw = new PrintWriter(jsonStorage)) {
		pw.println(jsonUserStorage);
		System.out.println("Done");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void loadJson(String fileName) throws IOException {
		String jsonString = readWithBufferedReader(fileName);
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setPrettyPrinting()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
				.create();
		
		Type type = new TypeToken<Map<String, User>>(){}.getType();
		Map<String, User> myMap = gson.fromJson(jsonString, type);
		this.users.putAll(myMap);	

		this.loadPosts();
		PostStorage.givePostStorage().relocatePosts();
	}
	
	public void loadPosts(){
		for(Entry<String,User> user : this.users.entrySet()){
				PostStorage.givePostStorage().addPostForUserWhenLoading(( user.getValue()).getPosts());
		
		}
	}
	

	
	private String readWithBufferedReader(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		String content = stringBuilder.toString();
		
		return content;
	}

	
	public static UserStorage giveUserStorage() throws InvalidDataException{
		if(UserStorage.storage == null){
			UserStorage.storage = new UserStorage();
		}
		
		return UserStorage.storage;
	}
	
	Map<String, User> getCopyOfUsers(){
		return Collections.unmodifiableMap(users);
	}
	
	// User methods - add,check, etc:

	void printAllUsers() { // print for check purpose
		for (Map.Entry<String, User> en : users.entrySet()) {
			User us = en.getValue();
			System.out.println("areee");
			us.printUserInformation();
		}
	}
	
	public void printCollection() {
		System.out.println(this.users);
	}

	 void addUserToSite(User user) {
		 if(user != null) {
			synchronized (this.users) {
				if (users.containsKey(user.getEmail())) {
					System.out.println("User with this email already exists");
					return;
				}
				users.put(user.getEmail(), user);
			}
		 }
	}
	 
	 void deleteUser(User user) {
		 if(user != null) {
			synchronized (this.users) {
				if (users.containsKey(user.getEmail())) {
					System.out.println("Deleting user");
					this.users.remove(user.getEmail());
					user = null;
					return;
				}
			}
		 }
	}

	public boolean checkIfUserExists(String email) { // email check
			if (users.containsKey(email)) {
				return true;
			}
			return false;
	}

	boolean checkIfPasswordIsCorrect(String email, String pass) { // pass check
		if (this.checkIfUserExists(email)) {
			User u = (User)users.get(email);
			if (u.getPassword().equals(pass)) {
				return true;
			}
		}
		return false;
	}

	public User getUserFromStorage(String email){
		return (User) this.users.get(email);
	}
	

	
}
