package NineGagProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JFormattedTextField;
import javax.swing.JList;

public class MenuFor9gag {

	private JFrame frame;
	private JTextField searchField;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFor9gag window = new MenuFor9gag();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws InvalidDataException 
	 */
	public MenuFor9gag() throws InvalidDataException {
		try {
			UserStorage.giveUserStorage().loadJson("src\\NineGagProject\\jsonStorage.json");
		} catch (IOException e) {
			return;

		}
		new SpecialSectionWorker().start();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	Border emptyBorder = BorderFactory.createEmptyBorder();

	private void initialize() { // startova stranica na 9gag
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.GRAY);
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 10));
		frame.setBounds(200, 200, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("9gag");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 93, 31);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlText);
		panel.setBounds(0, 0, 484, 44);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterForm.main();
				frame.setVisible(false);
			}
		});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(385, 11, 89, 23);
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnNewButton);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login_System.main();
				frame.setVisible(false);
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setForeground(SystemColor.activeCaptionBorder);
		btnLogin.setBackground(SystemColor.desktop);
		btnLogin.setBounds(296, 11, 89, 23);
		panel.add(btnLogin);

		searchField = new JTextField();
		searchField.setBounds(162, 14, 124, 20);
		panel.add(searchField);
		searchField.setColumns(10);

		

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 42, 93, 419);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblSections = new JLabel("Sections");
		lblSections.setForeground(Color.LIGHT_GRAY);
		lblSections.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSections.setBounds(10, 75, 79, 27);
		panel_1.add(lblSections);

		

		

		

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setForeground(SystemColor.textHighlight);
		scrollBar.setBounds(457, 42, 17, 408);
		frame.getContentPane().add(scrollBar);

		PostWindow[] posts = new PostWindow[PostStorage.givePostStorage().getPosts().length];
		DefaultListModel listModel = new DefaultListModel();

		for (int index = 0; index < posts.length; index++) {
			posts[index] = new PostWindow(PostStorage.givePostStorage().getPosts()[index]);
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
			public void actionPerformed(ActionEvent arg0) {
							Login_System ls = new Login_System();
							ls.main();
							frame.setVisible(false);
					}
			}
		);
		btnSelect.setForeground(SystemColor.text);
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSelect.setBorder(emptyBorder);
		btnSelect.setBackground(SystemColor.textHighlight);
		btnSelect.setBounds(0, 274, 89, 23);
		panel_1.add(btnSelect);

		JButton btnAnimals = new JButton("Animals");
		btnAnimals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getSection("Animals").length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getSection("Animals")[index]);
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
		
		JButton btnFunny = new JButton("Funny");
		btnFunny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getSection("Funny").length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getSection("Funny")[index]);
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
		
		
		JButton btnSport = new JButton("Sport");
		btnSport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getSection("Sport").length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getSection("Sport")[index]);
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
		
		
		

		JButton btnFresh = new JButton("Fresh");
		btnFresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getFreshPosts().length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getFreshPosts()[index]);
						
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
		
		
		JButton btnHot = new JButton("Hot");
		btnHot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getHotPosts().length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getHotPosts()[index]);
						
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

		JButton btnTrending = new JButton("Trending");
		btnTrending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().getTrendingPosts().length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().getTrendingPosts()[index]);					
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
		
		btnTrending.setBackground(SystemColor.menu);
		btnTrending.setBounds(0, 21, 89, 23);
		panel_1.add(btnTrending);
		btnTrending.setBorder(emptyBorder);
		
		
		JButton button_3 = new JButton("Search");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PostStorage.givePostStorage().giveSearchedPosts(searchField.getText());
				listModel.removeAllElements();
				PostWindow[] section = new PostWindow[PostStorage.givePostStorage().giveSearchedPosts(searchField.getText()).length];
				if (section.length > 0) {
					for (int index = 0; index < section.length; index++) {
						posts[index] = new PostWindow(PostStorage.givePostStorage().giveSearchedPosts(searchField.getText())[index]);					
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
		button_3.setForeground(SystemColor.textInactiveText);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_3.setBackground(Color.BLACK);
		button_3.setBounds(91, 13, 69, 20);
		panel.add(button_3);
	}
}
