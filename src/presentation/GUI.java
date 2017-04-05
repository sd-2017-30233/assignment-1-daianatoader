package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;

import business.ReportService;
import business.UserService;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frmDBank;
	public JFrame getFrmDBank() {
		return frmDBank;
	}
	private JButton btnLogIn;
	private JTextField userField;
	public String getUserField() {
		return userField.getText();
	}
	
	
	
	public static String currentUserName = "unknown";
	public static String currentPassword = "unknown";
	public ArrayList<String> up;
	

	private JPasswordField passwordField;
	
	public void setEmpty(){
		userField.setText("");
		passwordField.setText("");
	}


	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDBank = new JFrame();
		frmDBank.setForeground(new Color(0, 128, 0));
		frmDBank.setBackground(new Color(147, 112, 219));
		frmDBank.setFont(new Font("Californian FB", Font.BOLD, 14));
		frmDBank.setTitle("D Bank");
		frmDBank.setBounds(100, 100, 682, 395);
		frmDBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 205, 50));
		frmDBank.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to D Bank!");
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setBounds(168, 11, 283, 40);
		lblNewLabel.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 20));
		panel.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Californian FB", Font.BOLD, 14));
		lblUsername.setBounds(89, 92, 75, 14);
		panel.add(lblUsername);
		
		userField = new JTextField();
		userField.setBounds(242, 89, 86, 20);
		panel.add(userField);
		userField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Californian FB", Font.BOLD, 14));
		lblNewLabel_1.setBounds(89, 133, 69, 14);
		panel.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(242, 130, 86, 20);
		panel.add(passwordField);
		
		btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogInActionPerformed(arg0);
			}
		});
		btnLogIn.setBackground(new Color(154, 205, 50));
		btnLogIn.setFont(new Font("Californian FB", Font.BOLD, 14));
		btnLogIn.setBounds(258, 243, 89, 23);
		panel.add(btnLogIn);
	}
	
	private void LogInActionPerformed(ActionEvent e){
		UserService us = new UserService();
		
		if(us.validateUsernamePassword(userField.getText(), passwordField.getText())==true){
			currentUserName = userField.getText();
			currentPassword = passwordField.getText();
			Visualisation v = new Visualisation();

			v.getFrame().setVisible(true);
			this.getFrmDBank().setVisible(false);

		}
		else JOptionPane.showMessageDialog(null, "Username/Parola incorecte");
	this.setEmpty();
	}
	
}
