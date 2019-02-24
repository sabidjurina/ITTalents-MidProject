package NineGagProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gson.Gson;

public class NineGag {

	private static final int MAX_NUMBER_OF_RANDOM_TAGS = 8;

	private static NineGag singleton;

	private Set<String> allTags;
	private UserStorage usersStorage;
	private PostStorage postsStorage;

	private NineGag() throws InvalidDataException {
		this.allTags = new HashSet<String>();
		this.usersStorage = UserStorage.giveUserStorage();
		this.postsStorage = PostStorage.givePostStorage();


	}

	public static NineGag giveNineGag() throws InvalidDataException {
		if (NineGag.singleton == null) {
			NineGag.singleton = new NineGag();
		}
		return singleton;
	}

	public UserStorage getUserStorage() {
		return this.usersStorage;
	}

	public PostStorage getPostStorage() {
		return this.postsStorage;
	}

	public void forgotPassword(String email) {

		// TODO check if this user email is in the data base
		// TODO verify the user??? secret question or sth like that ???

		final String username = "mid.project.nine@gmail.com"; // enter your mail
																// id
		final String password = "nine*123";// enter ur password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mid.project.nine@gmail.com")); // same
																				// email
																				// id
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));// whome
																							// u
																							// have
																							// to
																							// send
																							// mails
																							// that
																							// person
																							// id
			message.setSubject("Forgot password resetting");
			String newPass = makePassword(8);
			message.setText("Your password is " + newPass);

			Transport.send(message);

			User user = usersStorage.getUserFromStorage(email);
			user.forgottenPass(newPass);
			System.out.println(user.getPassword());

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	private static String makePassword(int length) {
		String password = "";

		for (int i = 0; i < length - 3; i++) {
			password += randomCharacter("abcdefghijklmnopqrstuvwxyz");
		}
		String randomDigit = randomCharacter("0123456789");
		password = insertAtRandom(password, randomDigit);
		String randomCharacter = randomCharacter("*&-+=.#!$%");
		password = insertAtRandom(password, randomCharacter);
		String upperCase = randomCharacter("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		password = insertAtRandom(password, upperCase);

		return password.toString();
	}

	private static String randomCharacter(String characters) {
		int n = characters.length();
		int index = (int) (n * Math.random());
		return characters.substring(index, index + 1);
	}

	private static String insertAtRandom(String str, String toInsert) {
		int n = str.length();
		int index = (int) ((n + 1) * Math.random());
		return str.substring(0, index) + toInsert + str.substring(index);
	}

	void putInAllTags(Post p) {
		this.allTags.addAll(p.getAllTags());
	}

	void showAllTags() {
		for (String tag : allTags) {
			System.out.print(tag + " ");
		}
	}

	// public void showAllSections() {
	// int length = sections.length;
	// for(int index = 0 ; index < length; index++) {
	// System.out.println(sections[index]);
	// }
	// }

	void showSomeRandomTagsToChooseFrom() {
		List<String> allTagstoGetRandom = new ArrayList<String>();
		allTagstoGetRandom.addAll(allTags);
		System.out.println(allTagstoGetRandom);
		Set<String> uniqueRandomTags = new HashSet<String>();

		int randomNumber = (allTagstoGetRandom.size() < MAX_NUMBER_OF_RANDOM_TAGS)
				? (int) (allTagstoGetRandom.size() * Math.random())
				: new Random().nextInt(MAX_NUMBER_OF_RANDOM_TAGS) + 1;

		System.out.println("The random tags are: ");
		while (uniqueRandomTags.size() < randomNumber) {
			int randomIndex = (int) (allTagstoGetRandom.size() * Math.random());
			uniqueRandomTags.add((allTagstoGetRandom.get(randomIndex)));
		}
		System.out.println(uniqueRandomTags);
	}

}
