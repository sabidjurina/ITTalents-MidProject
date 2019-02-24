package NineGagProject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import com.sun.xml.internal.txw2.output.ResultFactory;

//"Awesome", "Basketball", "Car", "Comic", "Cosplay", "Countryballs", 
//"Classical Art Memes", "DIY & Crafts", "Drawing & Illistration", "Fan Art",
//"Food & Drinks", "Football", "Fortnite", "Gaming", "GIF", "Girl", "Girly Things",
//"Guy", "History", "Horror", "Home Design", "K-Pop", "League of Legends", "LEGO",
//"Movie & TV", "Music", "NFK - Not For Kids", "Overwatch", "PC Master Race", "Pic Of The Day",
//"Pokemon", "Politics", "Relationship", "PUBG", "Roast Me", "Savage", "Satisfying",
//"School", "Sci-Tech", "Star Wars", "Superhero", "Surreal Memes",
//Travel","Timely", "Video", "Warhammer", "Wallpaper", "WTF", "Dark Humor", Anime&Manga", "Ask 9GAG",

public class PostStorage {
	private static final int MAX_HOURS_FOR_POSTS_IN_HOT = 2;
	private static final int MAX_HOURS_FOR_POSTS_IN_FRESH = 2;
	private static final int MAX_HOURS_FOR_POSTS_IN_TRENDING = 2;
	private static final int POINTS_FOR_HOT_POSTS = 2;
	private static final int UPVOTES_FOR_TRENDING = 2;
	private static final int MIN_POINTS_FOR_HOT_POST = 2;

	private static PostStorage singleton;

	private Set<Post> posts; // TODO ???

	private Set<Post> hotPosts; // posts with points over given constant and are
								// uploaded in the last 2 hours
	private Set<Post> fresh; // posts that are uploaded in the last 2 hours
	private Set<Post> trending; // posts that are most upvoted in the past few
								// hours??
	private Map<String, Set<Post>> sectionOfPosts; // String section, tree set
													// of posts

	private ArrayList<String> allSections;

	public PostStorage() {
		this.fresh = new TreeSet<Post>((post1, post2) -> post1.getPostDate().compareTo(post2.getPostDate()));
		this.posts = new TreeSet<Post>(new Comparator<Post>() {

			@Override
			public int compare(Post o1, Post o2) {
				if (o1.getPostDate().equals(o2.getPostDate())) {
					return o1.hashCode() - o2.hashCode();
				}
				return o1.getPostDate().compareTo(o2.getPostDate());
			}

		});
		this.hotPosts = new TreeSet<Post>((post1, post2) -> post1.getPoints() - post2.getPoints());
		this.trending = new TreeSet<Post>((post1, post2) -> post1.getPoints() - post2.getPoints());
		this.sectionOfPosts = new HashMap<String, Set<Post>>(); // ???

		allSections = new ArrayList<String>();
		allSections.add("Funny");
		allSections.add("Animals");
		allSections.add("Sport");

		this.sectionOfPosts.put("Funny", new HashSet<Post>());
		this.sectionOfPosts.put("Sport", new HashSet<Post>());
		this.sectionOfPosts.put("Animals", new HashSet<Post>());
	}

	public static PostStorage givePostStorage() {
		if (PostStorage.singleton == null) {
			PostStorage.singleton = new PostStorage();
		}
		return singleton;
	}

	// posts methods and distributing


	void addMeme(Post newPost) throws InvalidDataException {
		if (newPost != null && this.isValidSection(newPost.getSection())) {
			this.posts.add(newPost);
			if (this.sectionOfPosts.containsKey(newPost.getSection())) {
				Set<Post> newPosts = this.sectionOfPosts.get(newPost.getSection());
				newPosts.add(newPost);
			} else {
				Set<Post> newPosts = new TreeSet<Post>(
						(post1, post2) -> post1.getPostDate().compareTo(post2.getPostDate()));
				newPosts.add(newPost);
				this.sectionOfPosts.put(newPost.getSection(), newPosts);
			}
			NineGag.giveNineGag().putInAllTags(newPost);

		} else {
			System.out.println("Given section is invalid");
		}
	}
	
		//	Set<String> getAllTags(){
		//		Set<String> tags = new HashSet<String>();
		//		for (Iterator<Post> it = posts.iterator(); it.hasNext();) {
		//			Post p = it.next();
		//			tags.addAll(p.getAllTags());
		//		}
		//		return tags;
		//	}
	
	public void addNewSection(String section) {
		if(Helper.isStringValid(section)) {
			this.allSections.add(section);
		}
	}
	
	public void deleteSection(String section) {
		if(Helper.isStringValid(section) && this.allSections.contains(section)) {
			this.allSections.remove(section);
		}
	}
	
	public void showAllSections() {
		System.out.println(this.allSections);
	}

	// Set<String> getAllTags(){
	// Set<String> tags = new HashSet<String>();
	// for (Iterator<Post> it = posts.iterator(); it.hasNext();) {
	// Post p = it.next();
	// tags.addAll(p.getAllTags());
	// }
	// return tags;
	// }

	public void addPostForUserWhenLoading(Set<Post> posts) {
		// System.out.println(this.posts.size() + " ima tolkova posta");
		for (Post p : posts) {
			this.posts.add(p);
		}
	}

	public Post[] getPosts() {
		ArrayList<Post> ps = new ArrayList<>();

		for (Post p : this.posts) {
			ps.add(p);
		}
		Post[] pz = new Post[ps.size()];
		pz = ps.toArray(pz);
		return pz;
	}

	public Post[] getHotPosts() {
		ArrayList<Post> ps = new ArrayList<>();

		for (Post p : this.hotPosts) {
			ps.add(p);
		}
		Post[] pz = new Post[ps.size()];
		pz = ps.toArray(pz);
		return pz;
	}

	public Post[] getTrendingPosts() {
		ArrayList<Post> ps = new ArrayList<>();

		for (Post p : this.trending) {
			ps.add(p);
		}
		Post[] pz = new Post[ps.size()];
		pz = ps.toArray(pz);
		return pz;
	}

	public Post[] getFreshPosts() {
		ArrayList<Post> ps = new ArrayList<>();

		for (Post p : this.fresh) {
			ps.add(p);
		}
		Post[] pz = new Post[ps.size()];
		pz = ps.toArray(pz);
		return pz;
	}

	void relocatePosts() {
		for (Post p : this.posts) {
			this.sectionOfPosts.get(p.getSection()).add(p);
		}
	}

	public Post[] getSection(String section) {
		ArrayList<Post> ps = new ArrayList<>();

		for (Post p : this.sectionOfPosts.get(section)) {
			ps.add(p);
		}
		Post[] pz = new Post[ps.size()];
		pz = ps.toArray(pz);
		return pz;
	}

	public boolean isValidSection(String section) {
		boolean isValid = false;
		if(Helper.isStringValid(section)) {
			int length = this.allSections.size();
			for(int index = 0 ; index < length; index++) {
				if(allSections.get(index).equalsIgnoreCase(section)) {
					isValid = true; 
					break;
				}
			}
		}
		return isValid;
	}

	public void showPostsAccordingToSections() {
		for (Map.Entry<String, Set<Post>> entry : sectionOfPosts.entrySet()) {
			System.out.println("Section: " + entry.getKey());
			Set<Post> currPosts = entry.getValue();
			for (Post p : currPosts) {
				System.out.println("Post: " + p);
			}
		}
	}

	void putInHot() {
		for (Iterator<Post> it = posts.iterator(); it.hasNext();) {
			Post p = it.next();
			if (p.getPoints() > POINTS_FOR_HOT_POSTS) {
				long hours = Duration.between(p.getPostDate(), LocalDateTime.now()).toHours();
//				if (hours > MAX_HOURS_FOR_POSTS_IN_HOT) {
//					return;
//				}
				if (hours >= 0 && p.getPoints() >= MIN_POINTS_FOR_HOT_POST) {
					this.hotPosts.add(p);
				}
			}
		}
	}

	void showHotPosts() {
		System.out.println("The hot posts are: ");
		for (Post p : hotPosts) {
			System.out.println(p);
		}
	}

	void putInFresh() {
		for (Iterator<Post> it = posts.iterator(); it.hasNext();) {
				
			Post p = it.next();
			long hours = Duration.between(p.getPostDate(), LocalDateTime.now()).toHours();

			;
//			if (hours > MAX_HOURS_FOR_POSTS_IN_FRESH) {
//				return;
//			}
			if (hours >= 0 && hours < MAX_HOURS_FOR_POSTS_IN_FRESH ) {
				this.fresh.add(p);
			}
		}
	}

	void showFreshPosts() {
		System.out.println("The fresh posts are: ");
		for (Post p : fresh) {
			System.out.println(p);
		}
	}

	void putInTrending() {
		for (Iterator<Post> it = posts.iterator(); it.hasNext();) {
			Post p = it.next();
			long hours = Duration.between(p.getPostDate(), LocalDateTime.now()).toHours();
			if (hours >= 0 && hours <= MAX_HOURS_FOR_POSTS_IN_TRENDING && 
					p.getCommentsCollection().size() >= 1 && p.getUpvotes() >= UPVOTES_FOR_TRENDING) {
				this.trending.add(p);
			}
		}
	}

	public Post[] giveSearchedPosts(String search) {

		List<Post> resultsFromSearch = new ArrayList<Post>();

		for (Post p : this.posts) {
			if (p.doesDescriptionContainsSearch(search) || p.isPostTaggedWith(search)) {
				resultsFromSearch.add(p);
			}
		}
		Post[] result = new Post[resultsFromSearch.size()];
		int index = 0;
		for (Post p : resultsFromSearch) {
			result[index] = p;
			index++;
		}
		return result;
	}
	
	public void deletePost(Post post) {
		if(post != null) {
			if(this.posts.contains(post)) {
				//System.out.println("Deleting this post: " + post);
				this.posts.remove(post);
				User user = post.getUser();
				user.deleteMyPost(post);
			}
		}
	}
	
	public void showAllPosts() {
		for(Post p : posts) {
			System.out.println(p);
		}
	}

}
