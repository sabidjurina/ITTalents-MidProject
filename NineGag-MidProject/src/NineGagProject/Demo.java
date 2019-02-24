 package NineGagProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import com.google.gson.Gson;


import NineGagProject.Settings.Genders;

public class Demo {
	
	
	public static void main(String[] args) throws InvalidDataException, InterruptedException, NotLoggedInException, InvalidSectionException, IOException {
//
//		User usi = new User("Ivan m","Cska1948","ivan@abv.bg");
//		
//		Post post1 = usi.createAPost("photo1", "Mega qkata snimka, brat!", "Animals", false);
//		post1.addTagsToPost("snimka","qko","fun");
//		usi.createAPost("photo2", "Malko tupa snimka, brat!", "Funny", true).addTagsToPost("tag1","tag10");
//		User usi2 = new User("Ivan m2","Cska19482","ivan2@abv.bg");
//		Post post2 = usi2.createAPost("photo12", "Mega qkata snimka, brat!22", "Animals", false);
//		post1.addTagsToPost("snimka22","qko22","fun22");
//		User usi3 = new User("Ivan m3","Cska19483","ivan3@abv.bg");
//
//		
//		UserStorage storage = UserStorage.giveUserStorage();
//		storage.addUserToSite(usi);
//		storage.addUserToSite(usi2);
//		storage.addUserToSite(usi3);
//		
//		
//		Comment c1 = usi2.writeAComment(post1, "usi2 comment");
//		usi3.writeAComment(post1, "usi3 comment");
//		Comment c = usi.writeAComment(post2, "commenting this");
//		Comment reply = usi2.writeAReply(c, "this is my reply");
//		 


//		usi.putNotifications();
//		usi.showMyNotifications();

//		storage.toJson();
		
//		storage.loadJson("src\\NineGagProject\\jsonStorage.json");
//		storage.setUserNames();
//		storage.printAllUsers();

//		PostStorage postStorage = PostStorage.givePostStorage();
//		Admin admin  = Admin.giveAdmin();
//		admin.addSection("new section");
//		postStorage.showAllSections();
//		admin.deleteSection("new section");
//		postStorage.showAllSections();
		
//		
//		admin.deleteComment(reply, usi2, post2);
//		post1.listAllCommentsForAPost();
//		post2.listAllCommentsForAPost();
		
//		admin.deleteUser(usi3);
//		storage.printAllUsers();
//		admin.deletePost(post1);

//		postStorage.showAllPosts();
		
		//usi.searching("tup");
		
//		usi.addSectionToFavourites("Funny", "Animals", "Ask 9GAG");
//		usi.showMyFavouriteSections();
//		
//		
//		NineGag.giveNineGag();

		MenuFor9gag m = new MenuFor9gag();
		m.main();
		
		
		
//		//ninegag.showAllSections();
//		ninegag.showPostsAccordingToSections();
		
//	Ads ad = new Ads();
//	ad.start();
//		 

		
		//MenuFor9gag.main();
		
	}

}
