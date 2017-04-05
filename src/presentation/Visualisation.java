package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import business.UserService;

import static presentation.GUI.currentPassword;
import static presentation.GUI.currentUserName;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JButton;

public class Visualisation {

	private JFrame frmDBank;
	private JMenu mnUsers;
	private JMenu mnReports;
	private boolean admininistrator;
	private String username;
	
	public void setAdmin(){
		this.getMnReports().setVisible(true);
		this.getMnUsers().setVisible(true);
	}
	public void setRegularUser(){
		this.getMnReports().setVisible(false);
		this.getMnUsers().setVisible(false);
	}
	
	
	
	public boolean getAdmin(){
		return admininistrator;
	}
	
	public JMenu getMnUsers(){
		return mnUsers;
	}
	
	public JMenu getMnReports(){
		return mnReports;
	}
	
	public JFrame getFrame() {
		return frmDBank;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Create the application.
	 */
	public Visualisation() {

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
		frmDBank = new JFrame();
		frmDBank.setTitle("D Bank");
		frmDBank.setBounds(100, 100, 566, 394);
		frmDBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 205, 50));
		frmDBank.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		menuBar.setFont(new Font("Californian FB", Font.BOLD, 14));
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 550, 21);
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
		
		JLabel lblSelectWhichAction = new JLabel("Welcome to D Bank!");
		lblSelectWhichAction.setForeground(new Color(255, 250, 250));
		lblSelectWhichAction.setFont(new Font("Lucida Handwriting", Font.BOLD, 18));
		lblSelectWhichAction.setBounds(151, 62, 256, 63);
		panel.add(lblSelectWhichAction);
		
		JLabel lblNewLabel = new JLabel("Please select a service from \r\n\tthe menu above!");
		lblNewLabel.setFont(new Font("Californian FB", Font.BOLD, 19));
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setBounds(89, 111, 346, 102);
		panel.add(lblNewLabel);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogOutActionPerformed(e);
			}
		});
		btnLogOut.setBounds(230, 250, 89, 23);
		panel.add(btnLogOut);
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
