package NineGagProject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Comment {
	@Expose
	private LocalDateTime date;
	@Expose
	private String content;
	@Expose
	private int points;
	@Expose
	private List<Comment> replies;
	private User user;
	
	
	public Comment(String content) throws InvalidDataException {
		if(Helper.isStringValid(content) && Helper.maxCommentLength(content)) {
			this.content = content;
			this.date = LocalDateTime.now();
			this.replies = new ArrayList<Comment>();
		} else {
			throw new InvalidDataException();
		}
	
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void printComment() {
		System.out.println("Content: " + this.content);
		System.out.println("Points: " + this.points);
	}
	
	public void printAllReplies() { 
		Iterator<Comment> it = this.replies.iterator();
		int points = 0;
		while(it.hasNext()) {
			Comment c = it.next();
			c.printComment();
			c.printAllReplies();
		}
		
	}
	
	public List<Comment> getReplies(){
		List<Comment> copy  = new ArrayList<Comment>(replies);
		return copy;
	}
	
	//TODO ne trqbva li user-a da trie komentara a ne samiq komentar da trie sebe si
	protected void removeRepplie(Comment c) { //zaradi composite patterna se nalaga edin vid rekursivno da obikalqme komentarite i da gi triem;
		if(c != null) {
			if(this.replies.contains(c)) {
				Iterator<Comment> it = c.replies.iterator();
				this.replies.remove(c);
					while(it.hasNext()) {
						Comment com = it.next();
						c.removeRepplie(com);
					}
				}
		}
		
	}
	
	public boolean containsReply(Comment c) {
		if(c != null) {
			if(this.replies.contains(c)) {
				return true;
			}
		}
		return false;
	}
	
	
	protected void addReplie(Comment c) {
		if(c != null) {
			this.replies.add(c);
		}
		
	}
	
	protected LocalDateTime getDate() {
		return date;
	}
	
	protected void rateUp() {
		this.points++;
	}
	
	protected void rateDown() {
		if(this.points >= 0) {
			this.points--;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
