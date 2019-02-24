package NineGagProject;

import com.google.gson.annotations.Expose;

public class Admin extends PersonInformation{
	
	
	private UserStorage userStorage;
	
	private PostStorage postStorage;
	
	
	private static Admin singleton;
	
	public static Admin giveAdmin() throws InvalidDataException {
		if (Admin.singleton == null) {
			Admin.singleton = new Admin();
		}
		return singleton;
	}

	private  Admin() throws InvalidDataException {
		super("Admin admin", "Admin*123", "admin@abv.bg");
		userStorage = UserStorage.giveUserStorage();
		postStorage = PostStorage.givePostStorage();
	
	}
	
	public void deleteUser(User user) {
		userStorage.deleteUser(user);
	}
	
	public void deletePost(Post post) {
		postStorage.deletePost(post);
	}
	
	public void deleteComment(Comment c, User u, Post p) {
		u.deleteCommentFromMyPost(c, p);
		u.deleteMyCommentOnOtherPosts(c, p);
	}
	
	public void addSection(String section) {
		postStorage.addNewSection(section);
	}
	
	public void deleteSection(String section) {
		postStorage.deleteSection(section);
	}
	
	

}
