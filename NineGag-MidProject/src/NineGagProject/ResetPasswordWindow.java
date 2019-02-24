package NineGagProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResetPasswordWindow {

	private JFrame frame;
	private JTextField txtEnterYourEmail;
	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPasswordWindow window = new ResetPasswordWindow();
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
	public ResetPasswordWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtEnterYourEmail = new JTextField();
		txtEnterYourEmail.setText("Enter your email here");
		txtEnterYourEmail.setBounds(84, 35, 195, 28);
		frame.getContentPane().add(txtEnterYourEmail);
		txtEnterYourEmail.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(232, 171, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel EmailField = new JLabel("Email");
		EmailField.setFont(new Font("Tahoma", Font.BOLD, 20));
		EmailField.setForeground(SystemColor.desktop);
		EmailField.setBounds(20, 36, 64, 23);
		frame.getContentPane().add(EmailField);
		
		JButton btnNewButton_1 = new JButton("Send password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email = txtEnterYourEmail.getText();
				
				try {
					if(UserStorage.giveUserStorage().checkIfUserExists(email)) {
							
							NineGag.giveNineGag().forgotPassword(email);
							JOptionPane.showMessageDialog(null, "Email was sent with your new password!", "Email sent!", JOptionPane.INFORMATION_MESSAGE);
							frame.setVisible(false);
							MenuFor9gag m = new MenuFor9gag();
							m.main();
						} else {
							JOptionPane.showMessageDialog(null, "Invalid email!", "Couldn't sent an email!", JOptionPane.ERROR_MESSAGE);
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
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBounds(20, 88, 145, 28);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		button.setForeground(SystemColor.text);
		button.setFont(new Font("Tahoma", Font.BOLD, 15));
		button.setBackground(SystemColor.textHighlight);
		button.setBounds(209, 88, 79, 28);
		frame.getContentPane().add(button);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 11, 304, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 148, 304, 2);
		frame.getContentPane().add(separator_1);
		
		
		
	}
}
