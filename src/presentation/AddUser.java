package presentation;

import static presentation.GUI.currentPassword;
import static presentation.GUI.currentUserName;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import business.UserService;
import data.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class AddUser {

	private JFrame frame;
	private JMenu mnUsers;
	private JMenu mnReports;
	private JTextField name;
	private JTextField username;
	JCheckBox chckbxAdmin;
	JCheckBox chckbxRegularuser;
	private JPasswordField password;
	
	public void setAdmin(){
		this.getMnReports().setVisible(true);
		this.getMnUsers().setVisible(true);
	}
	public void setRegularUser(){
		this.getMnReports().setVisible(false);
		this.getMnUsers().setVisible(false);
	}
	public JMenu getMnUsers(){
		return mnUsers;
	}
	
	public JMenu getMnReports(){
		return mnReports;
	}
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public AddUser() {
		initialize();
		UserService us = new UserService();
		if(us.checkAdmin(currentUserName, currentPassword)==true){
			this.getMnReports().setVisible(true);
			this.getMnUsers().setVisible(true);
		}
		else{
			this.getMnReports().setVisible(false);
			this.getMnUsers().setVisible(false);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 205, 50));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		menuBar.setFont(new Font("Californian FB", Font.BOLD, 14));
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 0, 434, 21);
		panel.add(menuBar);
		
		JMenu mnClients = new JMenu("Clients");
		mnClients.setBackground(new Color(72, 61, 139));
		menuBar.add(mnClients);
		
		JMenuItem addNewClients = new JMenuItem("Add a new Client");
		addNewClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddNewClientsActionPerformed(arg0);
			}
		});
		mnClients.add(addNewClients);
		
		JMenuItem viewClients = new JMenuItem("View Clients");
		viewClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewClientsActionPerformed(e);
			}
		});
		mnClients.add(viewClients);
		
		JMenu mnAccounts = new JMenu("Accounts");
		menuBar.add(mnAccounts);
		
		JMenuItem addNewAccount = new JMenuItem("Add new account");
		addNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewAccountActionPerformed(e);
			}
		});
		mnAccounts.add(addNewAccount);
		
		JMenuItem viewAccounts = new JMenuItem("View accounts");
		viewAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAccountsActionPerformed(e);
			}
		});
		mnAccounts.add(viewAccounts);
		
		JMenu mnTransfer = new JMenu("Transfer");
		menuBar.add(mnTransfer);
		
		JMenuItem addTransfer = new JMenuItem("Add a new transfer");
		addTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTransferActionPerformed(e);
			}
		});
		mnTransfer.add(addTransfer);
		
		JMenu mnUtilitiesBills = new JMenu("Utilities bills");
		menuBar.add(mnUtilitiesBills);
		
		JMenuItem addPayment = new JMenuItem("Add new payment");
		addPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPaymentActionPerformed(e);
			}
		});
		mnUtilitiesBills.add(addPayment);
		
		mnUsers = new JMenu("Users");
		menuBar.add(mnUsers);
		
		JMenuItem addUser = new JMenuItem("Add a new user");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUserActionPerformed(e);
			}
		});
		mnUsers.add(addUser);
		
		JMenuItem viewUsers = new JMenuItem("View users");
		viewUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUserActionPerformed(e);
			}
		});
		mnUsers.add(viewUsers);
		
		mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem transferReports = new JMenuItem("Transfer reports");
		transferReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferReportsActionPerformed(e);
			}
		});
		mnReports.add(transferReports);
		
		JMenuItem utilitiesBillsReports = new JMenuItem("Utilities bills reports");
		utilitiesBillsReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilitiesBillsActionPerformed(e);
			}
		});
		mnReports.add(utilitiesBillsReports);
	
	JButton btnLogOut = new JButton("Log out");
	btnLogOut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LogOutActionPerformed(e);
		}
	});
	btnLogOut.setBounds(262, 207, 101, 23);
	panel.add(btnLogOut);
	
	JLabel lblName = new JLabel("Name");
	lblName.setBounds(42, 46, 70, 14);
	panel.add(lblName);
	
	name = new JTextField();
	name.setBounds(146, 43, 86, 20);
	panel.add(name);
	name.setColumns(10);
	
	JLabel lblUsername = new JLabel("Username");
	lblUsername.setBounds(42, 77, 82, 14);
	panel.add(lblUsername);
	
	username = new JTextField();
	username.setBounds(146, 74, 86, 20);
	panel.add(username);
	username.setColumns(10);
	
	JLabel lblPassword = new JLabel("Password");
	lblPassword.setBounds(42, 113, 70, 14);
	panel.add(lblPassword);
	
	JButton btnAddUser = new JButton("Add user");
	btnAddUser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			AddUserPerformed(arg0);
		}
	});
	btnAddUser.setBounds(131, 207, 101, 23);
	panel.add(btnAddUser);
	
	JLabel lblType = new JLabel("Type");
	lblType.setBounds(42, 152, 70, 14);
	panel.add(lblType);
	
	chckbxAdmin = new JCheckBox("Admin\r\n");
	chckbxAdmin.setBackground(new Color(154, 205, 50));
	chckbxAdmin.setBounds(146, 137, 124, 23);
	panel.add(chckbxAdmin);
	
	chckbxRegularuser = new JCheckBox("Regular user\r\n");
	chckbxRegularuser.setBackground(new Color(154, 205, 50));
	chckbxRegularuser.setBounds(146, 163, 124, 23);
	panel.add(chckbxRegularuser);
	
	password = new JPasswordField();
	password.setBounds(146, 110, 86, 20);
	panel.add(password);
}
	private void AddUserPerformed(ActionEvent evt){
		UserService us = new UserService();
		int type=-1;
		if(chckbxAdmin.isSelected())
			type=0;
		if(chckbxRegularuser.isSelected())
			type=1;
		if(name.getText().equals(null) || username.getText().equals(null) || password.getText().equals(null) || (chckbxAdmin.isSelected() && chckbxRegularuser.isSelected()) || type==-1){
			JOptionPane.showMessageDialog(null, "Cont invalid!");
			name.setText("");
			username.setText("");
			password.setText("");
			chckbxAdmin.setSelected(false);
			chckbxRegularuser.setSelected(false);
			return;
		}
		User u = new User(name.getText(), username.getText(), password.getText(), type);
		ArrayList<User> users = (ArrayList<User>) us.listAll();
		for(int i=0; i<users.size();i++){
			if(users.get(i).equals(u)){
				JOptionPane.showMessageDialog(null, "Cont existent!");
				return;
			}
		}
		us.create(name.getText(), username.getText(), password.getText(), type);
		name.setText("");
		username.setText("");
		password.setText("");
		chckbxAdmin.setSelected(false);
		chckbxRegularuser.setSelected(false);
	}

	private void AddNewClientsActionPerformed(ActionEvent evt){
		this.getFrame().setVisible(false);
		AddClient ac = new AddClient();
		ac.getFrame().setVisible(true);
	}
	
	private void ViewClientsActionPerformed(ActionEvent evt){
		this.getFrame().setVisible(false);
		ViewClient vc = new ViewClient();
		vc.getFrame().setVisible(true);
	}
	
	private void AddNewAccountActionPerformed(ActionEvent evt){
		this.getFrame().setVisible(false);
		AddAccount vc = new AddAccount();
		vc.getFrame().setVisible(true);
	}
	private void ViewAccountsActionPerformed(ActionEvent evt){
		this.getFrame().setVisible(false);
		ViewAccount vc = new ViewAccount();
		vc.getFrame().setVisible(true);
	}
	private void AddTransferActionPerformed(ActionEvent evt){
		this.getFrame().setVisible(false);
		AddTransfer vc = new AddTransfer();
		vc.getFrame().setVisible(true);
	}
	private void AddPaymentActionPerformed(ActionEvent evt){
		this.getFrame().setVisible(false);
		AddPayment vc = new AddPayment();
		vc.getFrame().setVisible(true);
	}
	private void AddUserActionPerformed(ActionEvent evt){
		this.getFrame().setVisible(false);
		AddUser vc = new AddUser();
		vc.getFrame().setVisible(true);
	}
	
	private void ViewUserActionPerformed(ActionEvent evt){
		this.getFrame().setVisible(false);
		ViewUsers vc = new ViewUsers();
		vc.getFrame().setVisible(true);
	}
	private void TransferReportsActionPerformed(ActionEvent e){
		this.getFrame().setVisible(false);
		TransferReport vc = new TransferReport();
		vc.getFrame().setVisible(true);
	}
	private void UtilitiesBillsActionPerformed(ActionEvent e){
		this.getFrame().setVisible(false);
		PaymentReport vc = new PaymentReport();
		vc.getFrame().setVisible(true);
	}
	private void LogOutActionPerformed(ActionEvent e){
		this.getFrame().setVisible(false);
		GUI gui = new GUI();
		gui.getFrmDBank().setVisible(true);
	}
}


