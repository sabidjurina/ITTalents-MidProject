package NineGagProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.glass.ui.Menu;

import javafx.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuForLoggedUsers9gag {

	private JFrame frame;
	private JTextField searchField;
	private User user;

	/**
	 * Launch the application.
	 */

	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuForLoggedUsers9gag window = new MenuForLoggedUsers9gag(user);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	// protected void setUser(User us){
	// this.user = us;
	// }

	/**
	 * Create the application.
	 */

	public MenuForLoggedUsers9gag() {
		initialize();
	}

	/**
	 * @wbp.parser.constructor
	 */
	public MenuForLoggedUsers9gag(User us) {
		this.user = us;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { // startova stranica na 9gag

		Border emptyBorder = BorderFactory.createEmptyBorder();

		frame = new JFrame();
		frame.getContentPane().setForeground(Color.GRAY);
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 10));
		frame.setBounds(200, 200, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlText);
		panel.setBounds(0, 0, 484, 38);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		searchField = new JTextField();
		searchField.setBounds(139, 14, 124, 20);
		panel.add(searchField);
		searchField.setColumns(10);

		JButton btnUpload = new JButton("+ Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				CreateAPost cr = new CreateAPost(user);
				cr.main();
				frame.setVisible(false);
			}
		});
		btnUpload.setBackground(SystemColor.textHighlight);
		btnUpload.setForeground(SystemColor.text);
		btnUpload.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpload.setBounds(387, 11, 87, 23);
		panel.add(btnUpload);
		btnUpload.setBorder(emptyBorder);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.controlText);
		menuBar.setBounds(273, 14, 97, 21);
		panel.add(menuBar);
		menuBar.setBorder(emptyBorder);

		JMenu mnNewMenu = new JMenu("    Profile     ");
		mnNewMenu.setBackground(SystemColor.desktop);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(mnNewMenu);

		JButton btnMyProfile = new JButton("My profile");
		mnNewMenu.add(btnMyProfile);
		// btnMyProfile.setBorder(emptyBorder);
		JButton btnSettings = new JButton("Settings   ");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				SettingsWindow sw = new SettingsWindow(user);
				sw.main();
			}
		});
		mnNewMenu.add(btnSettings);
		// btnSettings.setBorder(emptyBorder);
		JButton btnLogout = new JButton("Logout     ");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				MenuFor9gag.main();
				frame.setVisible(false);

			}
		});
		mnNewMenu.add(btnLogout);
		// btnLogout.setBorder(emptyBorder);

		JLabel lblNewLabel = new JLabel("9gag");
		lblNewLabel.setBounds(0, 0, 93, 31);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBackground(Color.WHITE);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				PostStorage.givePostStorage().giveSearchedPosts(searchField.getText());
			}
		});
		btnNewButton.setBounds(60, 14, 69, 20);
		panel.add(btnNewButton);
		btnNewButton.setForeground(SystemColor.textInactiveText);
		btnNewButton.setBackground(SystemColor.desktop);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 38, 87, 423);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblSections = new JLabel("Sections");
		lblSections.setForeground(Color.LIGHT_GRAY);
		lblSections.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSections.setBounds(10, 75, 93, 27);
		panel_1.add(lblSections);


		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setForeground(SystemColor.textHighlight);
		scrollBar.setBounds(457, 42, 17, 408);
		frame.getContentPane().add(scrollBar);

		PostWindow[] posts = new PostWindow[PostStorage.givePostStorage().getPosts().length];
		DefaultListModel listModel = new DefaultListModel();

		for (int index = 0; index < posts.length; index++) {
			posts[index] = new PostWindow(PostStorage.givePostStorage().getPosts()[index],this.user);
			listModel.addElement(posts[index]);
		}

		JList list = new JList();
		list.setModel(listModel);
		list.setBounds(103, 55, 351, 406);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(103, 42, 352, 419);
		scrollPane.setViewportView(list);
		frame.getContentPane().add(scrollPane);

		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int n = list.getSelectedIndex();
				if(n != -1){
				posts[n].main();
				}
			}
		});
		btnSelect.setForeground(SystemColor.text);
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSelect.setBorder(emptyBorder);
		btnSelect.setBackground(SystemColor.textHighlight);
		btnSelect.setBounds(0, 274, 89, 23);
		panel_1.add(btnSelect);
		
		
		JButton btnFunny = new JButton("Funny");
		btnFunny.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getSection("Funny").length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getSection("Funny")[index],user);
						
						listModel.addElement(posts[index]);
					}
					list.setModel(listModel);
					list.setBounds(103, 55, 351, 406);
					scrollPane.setBounds(103, 42, 352, 419);
					scrollPane.setViewportView(list);
					frame.getContentPane().add(scrollPane);
				}
			}
		});
		btnFunny.setBackground(SystemColor.menu);
		btnFunny.setBounds(0, 127, 89, 23);
		panel_1.add(btnFunny);
		btnFunny.setBorder(emptyBorder);

		JButton btnAnimals = new JButton("Animals");
		btnAnimals.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getSection("Animals").length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getSection("Animals")[index],user);
						listModel.addElement(posts[index]);
					}
					list.setModel(listModel);
					list.setBounds(103, 55, 351, 406);
					scrollPane.setBounds(103, 42, 352, 419);
					scrollPane.setViewportView(list);
					frame.getContentPane().add(scrollPane);
				}
			}
		});
		btnAnimals.setBackground(SystemColor.menu);
		btnAnimals.setBounds(0, 106, 89, 23);
		panel_1.add(btnAnimals);
		btnAnimals.setBorder(emptyBorder);

		JButton btnSport = new JButton("Sport");
		btnSport.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getSection("Sport").length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getSection("Sport")[index],user);
						listModel.addElement(posts[index]);
					}
					list.setModel(listModel);
					list.setBounds(103, 55, 351, 406);
					scrollPane.setBounds(103, 42, 352, 419);
					scrollPane.setViewportView(list);
					frame.getContentPane().add(scrollPane);
				}
			}
		});
		btnSport.setBackground(SystemColor.menu);
		btnSport.setBounds(0, 147, 89, 23);
		panel_1.add(btnSport);
		btnSport.setBorder(emptyBorder);
	
		
		JButton btnHot = new JButton("Hot");
		btnHot.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getHotPosts().length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getHotPosts()[index],user);
						
						listModel.addElement(posts[index]);
					}
					list.setModel(listModel);
					list.setBounds(103, 55, 351, 406);
					scrollPane.setBounds(103, 42, 352, 419);
					scrollPane.setViewportView(list);
					frame.getContentPane().add(scrollPane);
				}
			}
		});
		btnHot.setBackground(SystemColor.menu);
		btnHot.setBounds(0, 0, 89, 23);
		panel_1.add(btnHot);
		btnHot.setBorder(emptyBorder);
		btnHot.setBorder(emptyBorder);
		
		
		JButton btnFresh = new JButton("Fresh");
		btnFresh.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getFreshPosts().length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getFreshPosts()[index],user);
						
						listModel.addElement(posts[index]);
					}
					list.setModel(listModel);
					list.setBounds(103, 55, 351, 406);
					scrollPane.setBounds(103, 42, 352, 419);
					scrollPane.setViewportView(list);
					frame.getContentPane().add(scrollPane);
				}
			}
		});
		btnFresh.setBackground(SystemColor.menu);
		btnFresh.setBounds(0, 41, 89, 23);
		panel_1.add(btnFresh);
		btnFresh.setBorder(emptyBorder);
		btnFresh.setBorder(emptyBorder);
		
		
		JButton btnTrending = new JButton("Trending");
		btnTrending.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getTrendingPosts().length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getTrendingPosts()[index],user);					
						listModel.addElement(posts[index]);
					}
					list.setModel(listModel);
					list.setBounds(103, 55, 351, 406);
					scrollPane.setBounds(103, 42, 352, 419);
					scrollPane.setViewportView(list);
					frame.getContentPane().add(scrollPane);
				}
			}
		});
		btnTrending.setBounds(0, 21, 89, 23);
		panel_1.add(btnTrending);
		btnTrending.setBackground(SystemColor.menu);
		btnTrending.setBorder(emptyBorder);
		btnTrending.setBorder(emptyBorder);
		
	}
	
	

}
