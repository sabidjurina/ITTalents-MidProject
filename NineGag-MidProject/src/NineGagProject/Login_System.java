package NineGagProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;

import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import java.awt.SystemColor;

public class Login_System { //napravih sistema za logvane na useri

	private JFrame frame;
	private JTextField EmailField;
	private JPasswordField passwordField;
	private JFrame frmLoginSystem;
	

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_System window = new Login_System();
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
	public Login_System() {
		initialize();
		
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblLogin.setBounds(148, 6, 160, 39);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail.setBounds(29, 86, 70, 20);
		frame.getContentPane().add(lblEmail);
		
		EmailField = new JTextField();
		EmailField.setBounds(148, 86, 213, 24);
		frame.getContentPane().add(EmailField);
		EmailField.setColumns(10);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(29, 119, 104, 21);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(148, 121, 213, 24);
		frame.getContentPane().add(passwordField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 43, 464, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 185, 464, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String password = passwordField.getText();
				String email = EmailField.getText();
				try {
					if( UserStorage.giveUserStorage().checkIfUserExists(email)) {
						if( NineGag.giveNineGag().getUserStorage().checkIfPasswordIsCorrect(email, password)) {
							User us = UserStorage.giveUserStorage().getUserFromStorage(email);
							JOptionPane.showMessageDialog(null, "Welcome back " + us.getFullName(), "Logged in!", JOptionPane.INFORMATION_MESSAGE);
							frame.setVisible(false);
							MenuForLoggedUsers9gag m = new MenuForLoggedUsers9gag(us);
							m.main();
						}
					} else {
					JOptionPane.showMessageDialog(null, "Invalid loggin details!", "Login Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidDataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(29, 198, 118, 45);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailField.setText(null);
				passwordField.setText(null);
				
			}
		});
		btnNewButton_1.setBounds(173, 198, 118, 45);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setBackground(SystemColor.textHighlight);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit","Login Systems",
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					frame.setVisible(false);
					System.exit(0);
				}
			}
		});
		btnNewButton_2.setBounds(317, 198, 104, 45);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuFor9gag.main();
				frame.setVisible(false);
			}
		});
		btnNewButton_3.setForeground(SystemColor.text);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setBackground(SystemColor.textHighlight);
		btnNewButton_3.setBounds(404, 50, 70, 39);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnForgottenPassword = new JButton("Forgotten password");
		btnForgottenPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResetPasswordWindow rsw = new ResetPasswordWindow();
				rsw.main();
			}
		});
		btnForgottenPassword.setForeground(Color.WHITE);
		btnForgottenPassword.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnForgottenPassword.setBackground(SystemColor.textHighlight);
		btnForgottenPassword.setBounds(305, 156, 157, 20);
		frame.getContentPane().add(btnForgottenPassword);
		
	}
}
