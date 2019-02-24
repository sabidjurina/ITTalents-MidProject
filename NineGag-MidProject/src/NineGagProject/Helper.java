package NineGagProject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Helper {
	private static final int MAX_COMMENT_LENGTH = 1000;
	private static final int MIN_PASS_LENGTH = 8;
	
	public static class FormattedDateMatcher{
		 
	    private static Pattern DATE_PATTERN = Pattern.compile(
	      "^\\d{4}-\\d{2}-\\d{2}$");
	 
	   
	    public boolean matches(String date) {
	        return DATE_PATTERN.matcher(date).matches();
	    }
	}

	public static class EmailValidator {

		private Pattern pattern;
		private Matcher matcher;

		private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		public EmailValidator() {
			pattern = Pattern.compile(EMAIL_PATTERN);
		}

		public boolean validate(final String hex) {
			if (hex != null) {
				matcher = pattern.matcher(hex);
				return matcher.matches();
			}
			return false;

		}
	}
	
	
		public static boolean isStringValid(String str) {
			if (str != null && !(str.trim().equals("")) && str.trim().length() > 0) {
				return true;
			}
			return false;
		}
		
		public static boolean isNameValid(String fullName) {
			if (isStringValid(fullName)) {
				if (fullName.contains(" ")) {
					String firstName = fullName.substring(0, fullName.indexOf(' '));
					String lastName = fullName.substring(fullName.indexOf(' '));
					if (isStringValid(firstName) && isStringValid(lastName)) {
						return true;
					}
				}
			}
			return false;
		}

	public static class passwordValidator {
		
		private static boolean checkIfThereIsSmallLetter(String pass) {
			for (int index = 0; index < pass.length(); index++) {
				if (Character.isLowerCase(pass.charAt(index))) {
					return true;
				}
			}
			return false;
		}

		private static boolean checkIfThereIsCapitalLetter(String pass) {
			for (int index = 0; index < pass.length(); index++) {
				if (Character.isUpperCase(pass.charAt(index))) {
					return true;
				}
			}
			return false;
		}

		private static boolean checkIfThereIsDigit(String pass) {
			for (int index = 0; index < pass.length(); index++) {
				if (Character.isDigit(pass.charAt(index))) {
					return true;
				}
			}
			return false;
		}

		private static boolean checkLength(String pass) {
			return pass.length() >= MIN_PASS_LENGTH;
		}

		static boolean isPasswordStrong(String pass) {
			return pass != null && checkIfThereIsCapitalLetter(pass) && checkIfThereIsDigit(pass)
					&& checkIfThereIsSmallLetter(pass) && checkLength(pass);
		}
	}

	public static int randomNumber(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
	
	public static String userNameMaker(String email) {
		String userName = email.substring(0, email.indexOf('@'));
		
		Queue<Character> newUserNameChars = new LinkedList<Character>();
		int length = userName.length();
		for(int index = 0 ; index < length; index++) {
			char currentChar = userName.charAt(index);
			if(Character.isLetterOrDigit(currentChar)) {
				newUserNameChars.offer(currentChar);
			}
		}
		
		StringBuilder newUserName = new StringBuilder();
		for(Character c : newUserNameChars) {
			newUserName.append(c);
		}
		
		return newUserName.toString();
	}
	
	public static boolean maxCommentLength(String content) {
		if(content.length() > MAX_COMMENT_LENGTH) {
			return false;
		}
		return true;
	}

}
