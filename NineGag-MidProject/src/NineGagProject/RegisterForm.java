package NineGagProject;

import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.UIManager;

import com.google.gson.Gson;
import com.sun.glass.events.WindowEvent;

public class RegisterForm { // forma za registraciq vzima konstructora ot User i
							// suzdava obekt na bazata na
							// vuvedenite v poletata textove
							// ako ne vadi message board.

	private JFrame frame;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JTextField fullNameField;

	protected String getFullNameField() {
		return this.emailField.getText();
	}

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm window = new RegisterForm();
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
	public RegisterForm() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(300, 300, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.textHighlight);
		separator.setBounds(10, 334, 464, 2);
		frame.getContentPane().add(separator);

		JButton btnRegister = new JButton("Sign up");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (!UserStorage.giveUserStorage().checkIfUserExists(emailField.getText())) {
						User us = new User(fullNameField.getText(), passwordField.getText(), emailField.getText());
						UserStorage.giveUserStorage().addUserToSite(us);
						UserStorage.giveUserStorage().toJson();
						JOptionPane.showMessageDialog(null, "Welcome to 9gag," + us.getFullName() + " !",
								"Your account is ready to use!", JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);
						MenuForLoggedUsers9gag m = new MenuForLoggedUsers9gag(us);
						m.main();

					} else {
						JOptionPane.showMessageDialog(null, "User already exists!", "Try another email!",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (InvalidDataException e1) {
					JOptionPane.showMessageDialog(null, "Invalid entry!", "Signup Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnRegister.setForeground(SystemColor.text);
		btnRegister.setBackground(SystemColor.textHighlight);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRegister.setBounds(43, 347, 136, 39);
		frame.getContentPane().add(btnRegister);

		JLabel lblNewLabel = new JLabel("Become a member");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(126, 11, 267, 32);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblEmail = new JLabel(" Email Address");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(126, 142, 206, 32);
		frame.getContentPane().add(lblEmail);

		emailField = new JTextField();
		emailField.setBounds(126, 186, 219, 32);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 51, 484, -8);
		frame.getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 54, 464, 2);
		frame.getContentPane().add(separator_2);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(126, 240, 170, 32);
		frame.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(126, 271, 219, 32);
		frame.getContentPane().add(passwordField);

		fullNameField = new JTextField();
		fullNameField.setBounds(126, 85, 219, 32);
		frame.getContentPane().add(fullNameField);
		fullNameField.setColumns(10);

		JLabel lblFu = new JLabel("Full Name");
		lblFu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFu.setBounds(126, 54, 119, 32);
		frame.getContentPane().add(lblFu);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuFor9gag.main();
				frame.setVisible(false);
			}
		});
		btnBack.setForeground(SystemColor.text);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setBackground(SystemColor.textHighlight);
		btnBack.setBounds(290, 347, 136, 39);
		frame.getContentPane().add(btnBack);
	}
}
