package presentation;

import java.awt.EventQueue;
import static presentation.GUI.currentUserName;
import static presentation.GUI.currentPassword;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import business.AccountService;
import business.ClientService;
import business.ReportService;
import business.UserService;
import data.Client;
import data.Report;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import static presentation.GUI.currentUserName;
import static presentation.GUI.currentPassword;

public class AddAccount {

	private JFrame frame;
	private JMenu mnUsers;
	private JMenu mnReports;
	private JTextField sum;
	private JCheckBox chckbxSpending;
	private JCheckBox chckbxSaving;
	private JList list;
	private DefaultListModel model;
	private Report r;
	
	public void setR(String date,int user){
		UserService u = new UserService();
		this.r.setUser(u.getUserById(user));
		this.r.setDate(date);
	}
	
	public Report getReport(){
		return this.r;
	}

	
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
	
	public double getSumt(){
		return Double.parseDouble(this.sum.getText());
	}
	
	public void setEmpty(){
		this.sum.setText("");
		this.chckbxSaving.setSelected(false);
		this.chckbxSpending.setSelected(false);
		this.list.clearSelection();
	}
	
	public JCheckBox getSaving(){
		return this.chckbxSaving;
	}
	
	public JCheckBox getSpending(){
		return this.chckbxSpending;
	}
	
	/*public AddAccount(){
		initialize();
	}*/
	
	public AddAccount() {
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
		frame.setBounds(100, 100, 544, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		menuBar.setToolTipText("");
		menuBar.setFont(new Font("Californian FB", Font.BOLD, 14));
		menuBar.setBackground(Color.WHITE);
		
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

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 250));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
	JSplitPane splitPane = new JSplitPane();
	panel.add(splitPane);
	
	JPanel panel_1 = new JPanel();
	splitPane.setLeftComponent(panel_1);
	panel_1.setLayout(new GridLayout(0, 1, 0, 0));
	
	JScrollPane scrollPane = new JScrollPane();
	panel_1.add(scrollPane);
	
	model = new DefaultListModel();
	
	list = new JList(model);
	list.setBackground(new Color(154, 205, 50));
	scrollPane.setViewportView(list);
	
	JPanel panel_2 = new JPanel();
	splitPane.setRightComponent(panel_2);
	panel_2.setLayout(null);
	
	JButton btnLogOut = new JButton("Log out");
	btnLogOut.setBounds(126, 191, 97, 23);
	panel_2.add(btnLogOut);
	
	JLabel type = new JLabel("Type");
	type.setBounds(20, 11, 46, 14);
	panel_2.add(type);
	
	chckbxSpending = new JCheckBox("Spending");
	chckbxSpending.setBounds(54, 7, 97, 23);
	panel_2.add(chckbxSpending);
	
	chckbxSaving = new JCheckBox("Saving");
	chckbxSaving.setBounds(153, 7, 97, 23);
	panel_2.add(chckbxSaving);
	
	JLabel lblSum = new JLabel("Sum");
	lblSum.setBounds(20, 67, 46, 14);
	panel_2.add(lblSum);
	
	sum = new JTextField();
	sum.setBounds(65, 64, 86, 20);
	panel_2.add(sum);
	sum.setColumns(10);
	
	JButton btnAdd = new JButton("Add");
	btnAdd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Add(arg0);
		}
	});
	btnAdd.setBounds(10, 191, 106, 23);
	panel_2.add(btnAdd);
	btnLogOut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LogOutActionPerformed(e);
		}
	});
	ClientService cs =new ClientService();
	ArrayList<Client> c = cs.listAll();
	for(int i = 0; i<c.size();i++){
			model.addElement(c.get(i).getName());
	}
	
}
	private void Add(ActionEvent e){
		String name = list.getSelectedValue().toString();
		ClientService cs = new ClientService();
		ArrayList<Client> c = cs.listAll();
		Client cl = null;
		for(int i = 0; i<c.size();i++){
			if(c.get(i).getName().equals(name))
				cl = c.get(i);
		}
		
		if(this.sum.getText().equals(null) || name.equals(null) || (this.getSaving().isSelected() == true && this.getSpending().isSelected() == true) || (this.getSaving().isSelected() == false && this.getSpending().isSelected() == false)){
			JOptionPane.showMessageDialog(null, "Date invalide!");
			this.setEmpty();
			return;
		}
		
		try
		{
			Double.parseDouble(this.sum.getText());
		}
		catch(NumberFormatException ex)
		{
		  JOptionPane.showMessageDialog(null, "Suma invalida!");
		  this.setEmpty();
		  return;
		}
		
		AccountService as =new AccountService();

		
		
		if(this.getSaving().isSelected()){
		as.create("SAVING", this.getSumt(), cl.getCnp(), currentUserName);
		}
		if(this.getSpending().isSelected()){
			as.create("SPENDING", this.getSumt(), cl.getCnp(), currentUserName);
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
		this.getFrame().setVisible(true);
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

