package NineGagProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import NineGagProject.Settings.Genders;
import javax.swing.JScrollBar;
import NineGagProject.Settings.Statuses;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class SettingsWindow {

	private JFrame frame;
	private JTextField emailField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField fullName;
	private JTextField year;
	private JTextField month;
	private JTextField day;
	private JTextField txtSomeFunnyThings;
	private User us;
	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsWindow window = new SettingsWindow(us);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public SettingsWindow() {
		initialize();
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	
	
	

	public SettingsWindow(User us){
		this();
		this.us = us;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
			JLabel lblNewLabel = new JLabel("Account");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblNewLabel.setBounds(53, -3, 287, 50);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblUsername.setBounds(63, 58, 89, 19);
			frame.getContentPane().add(lblUsername);
			
			emailField = new JTextField();
			emailField.setBounds(66, 153, 274, 26);
			frame.getContentPane().add(emailField);
			emailField.setColumns(10);
			
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblEmail.setBounds(63, 123, 89, 19);
			frame.getContentPane().add(lblEmail);
			
			usernameField = new JTextField();
			usernameField.setColumns(10);
			usernameField.setBounds(66, 86, 274, 26);
			frame.getContentPane().add(usernameField);
			
			JButton deleteAccountBtn = new JButton("Delete account");
			deleteAccountBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						UserStorage.giveUserStorage().deleteUser(us);
					} catch (InvalidDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Sorry you felt that," + us.getFullName() + " !",
							"Your account was deactivated!", JOptionPane.INFORMATION_MESSAGE);
					try {
						UserStorage.giveUserStorage().toJson();
					} catch (InvalidDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.setVisible(false);
					MenuFor9gag m;
					try {
						m = new MenuFor9gag();
						m.main();
					} catch (InvalidDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			deleteAccountBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
			deleteAccountBtn.setBounds(63, 190, 122, 19);
			frame.getContentPane().add(deleteAccountBtn);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(10, 220, 397, 10);
			frame.getContentPane().add(separator_1);
			
			JLabel lblNewLabel_1 = new JLabel("Password");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblNewLabel_1.setBounds(63, 228, 270, 50);
			frame.getContentPane().add(lblNewLabel_1);
			
			JLabel lblNewPassword = new JLabel("New Password");
			lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewPassword.setBounds(63, 279, 89, 19);
			frame.getContentPane().add(lblNewPassword);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(73, 304, 267, 26);
			frame.getContentPane().add(passwordField);
			
			JLabel lblRetypePassword = new JLabel("Re-type New Password");
			lblRetypePassword.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblRetypePassword.setBounds(63, 334, 156, 19);
			frame.getContentPane().add(lblRetypePassword);
			
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(73, 360, 267, 26);
			frame.getContentPane().add(passwordField_1);
			
			JButton saveChangesAcc = new JButton("Save changes");
			saveChangesAcc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					us.getSettings().setUserName(usernameField.getText());
					us.setEmail(emailField.getText());
					JOptionPane.showMessageDialog(null, "Your username and email were changed!",
							"Settings message", JOptionPane.INFORMATION_MESSAGE);	
					try {
						UserStorage.giveUserStorage().toJson();
					} catch (InvalidDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			saveChangesAcc.setForeground(SystemColor.text);
			saveChangesAcc.setFont(new Font("Tahoma", Font.BOLD, 12));
			saveChangesAcc.setBackground(SystemColor.textHighlight);
			saveChangesAcc.setBounds(224, 190, 129, 26);
			frame.getContentPane().add(saveChangesAcc);
			
			JButton saveChangesPass = new JButton("Save changes");
			saveChangesPass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if(us.changePassword(passwordField.getText(), passwordField_1.getText())){
						JOptionPane.showMessageDialog(null, "Password changed successfully!",
								"Settings message!", JOptionPane.INFORMATION_MESSAGE);
						try {
							UserStorage.giveUserStorage().toJson();
						} catch (InvalidDataException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid password!",
								"Settings message!", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
			});
			saveChangesPass.setForeground(Color.WHITE);
			saveChangesPass.setFont(new Font("Tahoma", Font.BOLD, 12));
			saveChangesPass.setBackground(SystemColor.textHighlight);
			saveChangesPass.setBounds(56, 402, 129, 26);
			frame.getContentPane().add(saveChangesPass);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 439, 864, 2);
			frame.getContentPane().add(separator);
			
			JLabel lblYourName = new JLabel("Your Name");
			lblYourName.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblYourName.setBounds(471, 89, 89, 19);
			frame.getContentPane().add(lblYourName);
			
			fullName = new JTextField();
			fullName.setColumns(10);
			fullName.setBounds(467, 120, 274, 26);
			frame.getContentPane().add(fullName);
			
			JLabel lblProfile = new JLabel("Profile");
			lblProfile.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblProfile.setBounds(471, 11, 270, 50);
			frame.getContentPane().add(lblProfile);
			
			JList list = new JList();
			list.setBounds(106, 602, 1, 1);
			frame.getContentPane().add(list);
			
			JComboBox genders = new JComboBox();
			genders.setMaximumRowCount(3);
			genders.setBackground(SystemColor.text);
			genders.setFont(new Font("Tahoma", Font.BOLD, 12));
			genders.setModel(new DefaultComboBoxModel(Genders.values()));
			genders.setBounds(471, 188, 75, 20);
			frame.getContentPane().add(genders);
			
			JLabel lblStatus = new JLabel("Gender");
			lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblStatus.setBounds(471, 156, 89, 19);
			frame.getContentPane().add(lblStatus);
			
			JLabel lblStatus_1 = new JLabel("Status");
			lblStatus_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblStatus_1.setBounds(471, 220, 89, 19);
			frame.getContentPane().add(lblStatus_1);
			
			JComboBox statuses = new JComboBox();
			statuses.setModel(new DefaultComboBoxModel(Statuses.values()));
			statuses.setMaximumRowCount(25);
			statuses.setFont(new Font("Tahoma", Font.PLAIN, 12));
			statuses.setBackground(SystemColor.text);
			statuses.setBounds(471, 250, 105, 20);
			frame.getContentPane().add(statuses);
			
			JLabel lblBirthday = new JLabel("Birthday");
			lblBirthday.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblBirthday.setBounds(471, 297, 89, 19);
			frame.getContentPane().add(lblBirthday);
			
			year = new JTextField();
			year.setText("YYYY");
			year.setBounds(471, 327, 105, 26);
			frame.getContentPane().add(year);
			year.setColumns(10);
			
			month = new JTextField();
			month.setText("MM");
			month.setColumns(10);
			month.setBounds(586, 327, 75, 26);
			frame.getContentPane().add(month);
			
			day = new JTextField();
			day.setText("DD");
			day.setColumns(10);
			day.setBounds(671, 327, 75, 26);
			frame.getContentPane().add(day);
			
			JLabel lblCountry = new JLabel("Country");
			lblCountry.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblCountry.setBounds(471, 360, 89, 19);
			frame.getContentPane().add(lblCountry);
			
			JComboBox countries = new JComboBox();
			countries.setModel(new DefaultComboBoxModel(new String[] {"    Afghanistan", "    Albania", "    Algeria", "    Andorra", "    Angola", "    Antigua and Barbuda", "    Argentina", "    Armenia", "    Aruba", "    Australia", "    Austria", "    Azerbaijan", "    Bahamas, The", "    Bahrain", "    Bangladesh", "    Barbados", "    Belarus", "    Belgium", "    Belize", "    Benin", "    Bhutan", "    Bolivia", "    Bosnia and Herzegovina", "    Botswana", "    Brazil", "    Brunei", "    Bulgaria", "    Burkina Faso", "    Burma", "    Burundi", "    Cambodia", "    Cameroon", "    Canada", "    Cabo Verde", "    Central African Republic", "    Chad", "    Chile", "    China", "    Colombia", "    Comoros", "    Congo, Democratic Republic of the", "    Congo, Republic of the", "    Costa Rica", "    Cote d'Ivoire", "    Croatia", "    Cuba", "    Curacao", "    Cyprus", "    Czechia", "    Denmark", "    Djibouti", "    Dominica", "    Dominican Republic", "    Ecuador", "    Egypt", "    El Salvador", "    Equatorial Guinea", "    Eritrea", "    Estonia", "    Eswatini", "    Ethiopia\t", "    Fiji", "    Finland", "    France", "    Gabon", "    Gambia, The", "    Georgia", "    Germany", "    Ghana", "    Greece", "    Grenada", "    Guatemala", "    Guinea", "    Guinea-Bissau", "    Guyana", "    Haiti", "    Holy See", "    Honduras", "    Hong Kong", "    Hungary", "    Iceland", "    India", "    Indonesia", "    Iran", "    Iraq", "    Ireland", "    Israel", "    Italy", "    Jamaica", "    Japan", "    Jordan", "    Kazakhstan", "    Kenya", "    Kiribati", "    Korea, North", "    Korea, South", "    Kosovo", "    Kuwait", "    Kyrgyzstan", "    Laos", "    Latvia", "    Lebanon", "    Lesotho", "    Liberia", "    Libya", "    Liechtenstein", "    Lithuania", "    Luxembourg", "    Macau", "    Macedonia", "    Madagascar", "    Malawi", "    Malaysia", "    Maldives", "    Mali", "    Malta", "    Marshall Islands", "    Mauritania", "    Mauritius", "    Mexico", "    Micronesia", "    Moldova", "    Namibia", "    Nauru", "    Nepal", "    Netherlands", "    New Zealand", "    Nicaragua", "    Niger", "    Nigeria", "    North Korea", "    Norway", "    Pakistan", "    Palau", "    Palestinian Territories", "    Panama", "    Papua New Guinea", "    Paraguay", "    Peru", "    Philippines", "    Poland", "    Portugal", "    Romania", "    Russia", "    Rwanda", "    Saint Kitts and Nevis", "    Saint Lucia", "    Saint Vincent and the Grenadines", "    Samoa", "    San Marino", "    Sao Tome and Principe", "    Saudi Arabia", "    Senegal", "    Serbia", "    Seychelles", "    Sierra Leone", "    Singapore", "    Sint Maarten", "    Slovakia", "    Slovenia", "    Solomon Islands", "    Somalia", "    South Africa", "    South Korea", "    South Sudan", "    Spain", "    Sri Lanka", "    Sudan", "    Suriname", "    Swaziland (See Eswatini)", "    Sweden", "    Switzerland", "    Syria", "    Taiwan", "    Tajikistan", "    Tanzania", "    Thailand", "    Timor-Leste", "    Togo", "    Tonga", "    Trinidad and Tobago", "    Tunisia", "    Turkey", "    Turkmenistan", "    Tuvalu", "    Uganda", "    Ukraine", "    United Arab Emirates", "    United Kingdom", "    Uruguay", "    Uzbekistan", "    Vanuatu", "    Venezuela", "    Vietnam", "    Yemen", "    Zambia", "    Zimbabwe", "", "\t"}));
			countries.setMaximumRowCount(25);
			countries.setFont(new Font("Tahoma", Font.PLAIN, 12));
			countries.setBackground(Color.WHITE);
			countries.setBounds(471, 390, 105, 20);
			frame.getContentPane().add(countries);
			
			JLabel lblTellPeopleWho = new JLabel("Tell people who you are");
			lblTellPeopleWho.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblTellPeopleWho.setBounds(302, 452, 191, 19);
			frame.getContentPane().add(lblTellPeopleWho);
			
			txtSomeFunnyThings = new JTextField();
			txtSomeFunnyThings.setText("Some funny things about you");
			txtSomeFunnyThings.setBounds(302, 482, 274, 82);
			frame.getContentPane().add(txtSomeFunnyThings);
			txtSomeFunnyThings.setColumns(10);
			
			JSeparator separator_2 = new JSeparator();
			separator_2.setBounds(10, 602, 864, 2);
			frame.getContentPane().add(separator_2);
			
			JButton button = new JButton("Save changes");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					us.getSettings().description(txtSomeFunnyThings.getText());
					JOptionPane.showMessageDialog(null, "Description changed successfully!",
							"Settings message!", JOptionPane.INFORMATION_MESSAGE);
					try {
						UserStorage.giveUserStorage().toJson();
					} catch (InvalidDataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			button.setForeground(Color.WHITE);
			button.setFont(new Font("Tahoma", Font.BOLD, 12));
			button.setBackground(SystemColor.textHighlight);
			button.setBounds(108, 565, 127, 26);
			frame.getContentPane().add(button);
			
			JSeparator separator_3 = new JSeparator();
			separator_3.setOrientation(SwingConstants.VERTICAL);
			separator_3.setBackground(SystemColor.desktop);
			separator_3.setBounds(421, 11, 1, 417);
			frame.getContentPane().add(separator_3);
			
			JButton button_1 = new JButton("Save changes");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					us.getSettings().country(countries.getSelectedItem().toString().trim());
					us.getSettings().gender((Genders) genders.getSelectedItem());
					us.setFullName(fullName.getText());
					
					try {
						us.getSettings().birthDate(year.getText()+"-"+month.getText()+"-"+day.getText());
					} catch (InvalidDataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						UserStorage.giveUserStorage().toJson();
					} catch (InvalidDataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			button_1.setForeground(Color.WHITE);
			button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			button_1.setBackground(SystemColor.textHighlight);
			button_1.setBounds(626, 402, 127, 26);
			frame.getContentPane().add(button_1);
			
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
				}
			});
			btnBack.setForeground(Color.WHITE);
			btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnBack.setBackground(SystemColor.textHighlight);
			btnBack.setBounds(614, 568, 127, 26);
			frame.getContentPane().add(btnBack);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
