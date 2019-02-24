package NineGagProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PostWindow {

	private JFrame frame;
	private Post p;
	private User us;
	private JTextField commentField;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PostWindow window = new PostWindow(p, us);
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

	/**
	 * @wbp.parser.constructor
	 */

	public PostWindow(Post p, User user) {
		this.us = user;
		this.p = p;
		initialize();
	}

	public PostWindow(Post p) {
		this.p = p;
		initialize();
	}

	public PostWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 8));
		frame.getContentPane().setBackground(SystemColor.controlLtHighlight);
		frame.setBounds(100, 100, 350, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel(p.getSection());
		lblNewLabel.setBounds(10, 11, 101, 18);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(p.getDescription());
		lblNewLabel_1.setBounds(10, 32, 101, 18);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(p.getPhoto());
		lblNewLabel_2.setBounds(20, 57, 112, 23);
		frame.getContentPane().add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				p.increasePoints();
				try {
					UserStorage.giveUserStorage().toJson();
				} catch (InvalidDataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(10, 143, 99, 31);
		frame.getContentPane().add(btnNewButton);

		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.decreasePoints();
				try {
					UserStorage.giveUserStorage().toJson();
				} catch (InvalidDataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDown.setBackground(SystemColor.textHighlight);
		btnDown.setForeground(SystemColor.text);
		btnDown.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDown.setBounds(128, 143, 87, 31);
		frame.getContentPane().add(btnDown);

		JLabel lblNewLabel_3 = new JLabel("Points " + p.getPoints());
		lblNewLabel_3.setBounds(30, 82, 81, 23);
		frame.getContentPane().add(lblNewLabel_3);

		commentField = new JTextField();
		commentField.setBounds(27, 185, 176, 18);
		frame.getContentPane().add(commentField);
		commentField.setColumns(10);

		JButton btnComment = new JButton("Comment");
		btnComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					us.writeAComment(p, commentField.getText());
					JOptionPane.showMessageDialog(null, "You commented successully " + us.getFullName() + " !",
							"Comment posted!", JOptionPane.INFORMATION_MESSAGE);
					UserStorage.giveUserStorage().toJson();
				} catch (InvalidDataException e1) {
					JOptionPane.showMessageDialog(null, "Invalid entry!", "Comment not posted!",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});
		btnComment.setForeground(Color.WHITE);
		btnComment.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnComment.setBackground(SystemColor.textHighlight);
		btnComment.setBounds(20, 219, 134, 31);
		frame.getContentPane().add(btnComment);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBackground(SystemColor.textHighlight);
		btnBack.setBounds(237, 223, 87, 23);
		frame.getContentPane().add(btnBack);
	}

	@Override
	public String toString() {
		return "Description : " + p.getDescription();
	}

	void setUser(User us) {
		this.us = us;
	}

	User getUser() {
		return this.us;
	}
}
