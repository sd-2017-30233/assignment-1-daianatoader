package presentation;

import static presentation.GUI.currentPassword;
import static presentation.GUI.currentUserName;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import business.ClientService;
import business.UserService;
import data.Client.ClientType;

import javax.swing.JCheckBox;

public class AddClient {

	private JFrame frame;
	private JTextField nameField;
	private JTextField cnpField;
	private JTextField cardField;
	private JTextField addressField;
	private JCheckBox indCheck;
	private JCheckBox legalCheck;
	private JMenu mnUsers;
	private JMenu mnReports;
	
	public void setAdmin(){
		this.getMnReports().setVisible(true);
		this.getMnUsers().setVisible(true);
	}
	public void setRegularUser(){
		this.getMnReports().setVisible(false);
		this.getMnUsers().setVisible(false);
	}
	
	public JCheckBox getInd(){
		return this.indCheck;
	}
	
	public JCheckBox getLegal(){
		return this.legalCheck;
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
	
	public String name(){
		return nameField.getText();
	}
	public String address(){
		return addressField.getText();
	}
	public String card(){
		return cardField.getText();
	}
	public long cnp(){
		return Long.parseLong(cnpField.getText());
	}
	
	public void setEmpty(){
		nameField.setText("");
		addressField.setText("");
		cardField.setText("");
		cnpField.setText("");
		indCheck.setSelected(false);
		legalCheck.setSelected(false);
	}
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AddClient() {
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
		menuBar.setBackground(Color.WHITE);
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
	btnLogOut.setBounds(309, 216, 104, 23);
	panel.add(btnLogOut);
	
	JLabel lblName = new JLabel("Name");
	lblName.setBounds(10, 43, 66, 14);
	panel.add(lblName);
	
	nameField = new JTextField();
	nameField.setBounds(114, 40, 121, 20);
	panel.add(nameField);
	nameField.setColumns(10);
	
	JLabel lblCnp = new JLabel("CNP");
	lblCnp.setBounds(10, 77, 66, 14);
	panel.add(lblCnp);
	
	cnpField = new JTextField();
	cnpField.setBounds(114, 74, 121, 20);
	panel.add(cnpField);
	cnpField.setColumns(10);
	
	JLabel lblCardNumber = new JLabel("Card Number");
	lblCardNumber.setBounds(10, 112, 83, 14);
	panel.add(lblCardNumber);
	
	cardField = new JTextField();
	cardField.setBounds(114, 109, 121, 20);
	panel.add(cardField);
	cardField.setColumns(10);
	
	JLabel lblAddress = new JLabel("Address");
	lblAddress.setBounds(10, 147, 66, 14);
	panel.add(lblAddress);
	
	addressField = new JTextField();
	addressField.setBounds(114, 144, 121, 20);
	panel.add(addressField);
	addressField.setColumns(10);
	
	JLabel lblPersonType = new JLabel("Person Type");
	lblPersonType.setBounds(10, 180, 83, 14);
	panel.add(lblPersonType);
	
 indCheck = new JCheckBox("Individual");
	indCheck.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	indCheck.setBackground(new Color(154, 205, 50));
	indCheck.setBounds(114, 176, 121, 23);
	panel.add(indCheck);
	
    legalCheck = new JCheckBox("Legal entity");
	legalCheck.setBackground(new Color(154, 205, 50));
	legalCheck.setBounds(259, 176, 121, 23);
	panel.add(legalCheck);
	
	JButton btnAdd = new JButton("ADD");
	btnAdd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AddActionPerformed(e);
		}
	});
	btnAdd.setBounds(154, 216, 104, 23);
	panel.add(btnAdd);
}
	
	private void AddActionPerformed(ActionEvent e){
		
		if(this.name().equals(null) || this.address().equals(null) || this.card().equals(null) || (this.getInd().isSelected() == true && this.getLegal().isSelected() == true) || (this.getInd().isSelected() == false && this.getLegal().isSelected() == false)){
			JOptionPane.showMessageDialog(null, "Date invalide!");
			this.setEmpty();
			return;
		}
		
		ClientService c =new ClientService();
		if(this.getInd().isSelected()){
			c.add(this.cnp(), this.name(), this.address(), this.card(), ClientType.INDIVIDUAL.name());
		}
		if(this.getLegal().isSelected()){
			c.add(this.cnp(), this.name(), this.address(), this.card(), ClientType.LEGAL_ENTITY.name());
		}
		this.setEmpty();
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

