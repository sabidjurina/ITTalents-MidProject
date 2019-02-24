package NineGagProject;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.google.gson.annotations.Expose;

public class Post{
	
	private static final int MAX_HOURS_FOR_COMMENTS_IN_FRESH = 2;
	private static final int POINTS_FOR_HOT_COMMENTS = 5;

	private User user;
	@Expose
	private String photo;
	@Expose
	private String description;
	@Expose
	private boolean isSensitive;
	@Expose
	private LocalDateTime postDate;
	@Expose
	private int points;
	@Expose
	private int upvotes;
	@Expose
	private String section;

	@Expose
	private Set<String> tags;
	@Expose
	private List<Comment> comments;
	
	private Set<Comment> freshComments;
	private Set<Comment> hotComments;
	
	

	public Post(User user, String photo, String description) throws NotLoggedInException, InvalidDataException {

		if (user.isLoggedIn()) {
			this.postDate = LocalDateTime.now();

			NineGag site = NineGag.giveNineGag();

			this.user = user;
			if (Helper.isStringValid(photo)) {
				this.photo = photo;
			}
			if (Helper.isStringValid(description)) {
				this.description = description;
			}
			this.comments = new ArrayList<Comment>();
			this.tags = new TreeSet<String>();
			this.freshComments = new TreeSet<Comment>(new Comparator<Comment>() {

				@Override
				public int compare(Comment comm1, Comment comm2) {
					return comm1.getDate().compareTo(comm2.getDate());
				}
			});
			//TODO ???
			this.hotComments = new TreeSet<Comment>(new Comparator<Comment>() {

				@Override
				public int compare(Comment comm1, Comment comm2) {
					return comm1.getPoints() - comm2.getPoints();
				}
				});
		} else {
			throw new NotLoggedInException("Not logged in user!");
		}
		//this.showPost();
	}

	public Post(User user, String photo, String description, String section, boolean isSensitive) throws NotLoggedInException, InvalidSectionException, InvalidDataException {
		this(user, photo, description);
		if(PostStorage.givePostStorage().isValidSection(section)) {
			this.section = section;
		} else {
			throw new InvalidSectionException("Invalid section given!");
		}
		
		this.isSensitive = isSensitive;
	}

	public void addComment(String content) {
		if (Helper.isStringValid(content)) {
			try {
				this.comments.add(new Comment(content));
			} catch (InvalidDataException e) {
				System.out.println("Invalid content for comment!");
			}
		}
	}
	public void addComment(Comment comment) {
		if (comment != null) {
			this.comments.add(comment);
		}
	}
	
	public boolean checkIfCommentHasReplies(Comment c) {
		if(c != null && this.comments.contains(c)) {
			List<Comment> replies = c.getReplies();
			if(!replies.isEmpty()) {
				return true;
			}
		}
		
		return false;
	}
	
	public Comment checkIfPostHasThisReply(Comment comment) {
		Comment parent = null;
		if(comment != null) {
			for(Comment c : comments) {
				List<Comment> replies = c.getReplies();
				if(replies.contains(comment)) {
					parent = c;	
				}
				
			}	
		}
		return parent;
		
	}
	
	
	
	void putCommentsInFresh() {
		if(!comments.isEmpty()) {
			for(Iterator<Comment> it = comments.iterator(); it.hasNext();) {
				Comment c = it.next();
				long hours = Duration.between(c.getDate(), LocalTime.now()).toHours();
				if (hours >= 0 && hours <= MAX_HOURS_FOR_COMMENTS_IN_FRESH) {
					this.freshComments.add(c);
				}
			}
		} else {
			System.out.println("There are no comments to put in fresh");
		}
	}
	
	void putCommentsInHot() {
		if(!comments.isEmpty()) {
			for(Iterator<Comment> it = comments.iterator(); it.hasNext();) {
				Comment c = it.next();
				if (c.getPoints() > POINTS_FOR_HOT_COMMENTS) {
					long hours = Duration.between(c.getDate(), LocalTime.now()).toHours();
					if (hours >= 0 && hours <= MAX_HOURS_FOR_COMMENTS_IN_FRESH) {
						this.hotComments.add(c);
					}
				}
			} 
		}else {
			System.out.println("There are no comments to put in hot");
		}
	}

	void increasePoints() {
		this.points++;
		this.upvotes++;
	}

	void decreasePoints() {
		if (this.points >= 0) {
			this.points--;
		}
	}
	

	public void showPost() {
		System.out.println("-----------------------------------");
		System.out.println("Full name: " + this.user.getFullName());
		System.out.println("Descripion: " + this.description);
		System.out.println("Photo: " + this.photo);
		System.out.println("Points: " + this.points);
		System.out.println("Uploaded on: " + this.postDate);
		System.out.println("-----------------------------------");
	}

	public void listAllCommentsForAPost() {
		for (Comment c : this.comments) {
			c.printComment();// da izvadq metod, koito mi printi komentara.
			c.printAllReplies();
		}
	}
	
	public List<Comment> getCommentsCollection() {
		List<Comment> copy = new ArrayList<Comment>(comments);

		return copy;
	}

	public void addTagsToPost(String... tags) {
		for (String tag : tags) {
			if (Helper.isStringValid(tag)) {
				this.tags.add(tag);
			}
		}
	}
	
	public Set<String> getAllTags() {
		Set<String> allTagsForAPost = new HashSet<String>();
		for(String tag : tags) {
			allTagsForAPost.add(tag);
		}
		return allTagsForAPost;
		
	}
	
	public void deleteComment(Comment comment) {
		if(comment != null) {
			if(this.comments.contains(comment)) {
				this.comments.remove(comment);
				comment = null;
				return;
			}
		}
	}
	
	public boolean containsComment(Comment comment) {
		if(comment != null) {
			if(this.comments.contains(comment)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPostTaggedWith(String tag) {
		tag = tag.toLowerCase();
		if(Helper.isStringValid(tag)) {
			for(String t : this.tags) {
				if(t.toLowerCase().contains(tag)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean doesDescriptionContainsSearch(String search) {
		search = search.toLowerCase();
		if(Helper.isStringValid(search)) {
			if(this.description.toLowerCase().contains(search)) {
				return true;
			}
		}
		return false;
	}
	
	public int getUpvotes() {
		return upvotes;
	}
	

	protected int getPoints() {
		return points;
	}
	
	protected LocalDateTime getPostDate() {
		return postDate;
	}

	public String getSection() {
		return section;
	}

	@Override
	public String toString() {
		return "Post photo= " + photo;
	}

	public String getDescription() {
		return description;
	}
	void setUser(User u){
		this.user = u;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	protected String getPhoto(){
		return this.photo;
	}

	public User getUser() {
		return user;
	}

	



}
