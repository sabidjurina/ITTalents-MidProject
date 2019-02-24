package NineGagProject;

import com.google.gson.annotations.Expose;

public abstract class PersonInformation {
	
	@Expose
	private String fullName;
	@Expose
	private String password;
	@Expose
	private String email;

	


	public PersonInformation(String fullName, String password, String email) throws InvalidDataException {
		if (Helper.isNameValid(fullName)) {
			this.fullName = fullName;
		} else {
			throw new InvalidDataException("Unable to create user!");
		}

		if (Helper.passwordValidator.isPasswordStrong(password)) {
			this.password = password;
		} else {
			throw new InvalidDataException("Unable to create user!");
		}

		Helper.EmailValidator validator = new Helper.EmailValidator();

		if (validator.validate(email) || email == null) { // check if there
															// already is such
															// email in the
															// database
			this.email = email;
		} else {
			throw new InvalidDataException("Unable to create user!");
		}
	}
	
	
	
	
	public boolean changePassword(String newPass, String reNewPass) {
		if (newPass.equals(reNewPass)) {
			Helper.passwordValidator passValidator = new Helper.passwordValidator();
			if (passValidator.isPasswordStrong(newPass)) {
				this.password = newPass;
				return true;
			} else {
				System.out.println("New password is weak!");
				return false;
			}
		} else {
			System.out.println("Passwords are not equal!");
			return false;
		}
	}
	
	protected String getFullName() {
		return fullName;
	}

	protected String getPassword() {
		return password;
	}

	protected String getEmail() {
		return email;
	}
	
	protected void setEmail(String email) {
		Helper.EmailValidator validator = new Helper.EmailValidator();

		if (validator.validate(email) || email == null) {
			this.email = email;
		}

	}

	void forgottenPass(String newPass) {
		this.password = newPass;
	}
	
	protected void setFullName(String fullName) {
		if(Helper.isNameValid(fullName)){
		this.fullName = fullName;
		}
	}

}
