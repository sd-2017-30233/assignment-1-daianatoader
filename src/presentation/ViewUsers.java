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

import business.AccountService;
import business.ClientService;
import business.UserService;
import data.Account;
import data.User;
import data.Account.AccountType;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewUsers {

	private JFrame frame;
	private JMenu mnUsers;
	private JMenu mnReports;
	private JTable table;
	private DefaultTableModel model;
	public static int id=0;
	
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
	public ViewUsers() {
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
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.25);
		panel.add(splitPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(154, 205, 50));
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(null);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(10, 139, 79, 23);
		panel_1.add(btnLogOut);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletePerformed(e);
			}
		});
		btnDelete.setBounds(10, 105, 79, 23);
		panel_1.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePerformed(e);
			}
		});
		btnUpdate.setBounds(10, 71, 81, 23);
		panel_1.add(btnUpdate);
		
		JButton btnSelectUser = new JButton("Select");
		btnSelectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectPerformed(arg0);
			}
		});
		btnSelectUser.setBounds(10, 37, 81, 23);
		panel_1.add(btnSelectUser);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogOutActionPerformed(e);
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(154, 205, 50));
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(154, 205, 50));
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(154, 205, 50));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Name", "Username", "Password", "Type"
				}
			));
		scrollPane.setViewportView(table);
	
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
	model = (DefaultTableModel)table.getModel();
	
	UserService us = new UserService();
	ArrayList<User> u = (ArrayList<User>) us.listAll();
	for(int i = 0; i<u.size();i++){
		model.addRow(new Object[]{u.get(i).getName(),u.get(i).getUsername(), u.get(i).getPassword(),u.get(i).getType()});
	}
	
}
	private void DeletePerformed(ActionEvent evt){
		int row = table.getSelectedRow();
		if(row==-1){
			JOptionPane.showMessageDialog(null, "Select user!");
			return;
		}
		UserService us = new UserService();
		String user = table.getValueAt(row, 1).toString();
		int id = us.getIdByUsername(user);
			
		model.removeRow(row);
		model.fireTableRowsDeleted(row, row);
		
		us.delete(id);
	}
	private void SelectPerformed(ActionEvent evt){
		int row = table.getSelectedRow();
		int column = table.getColumnCount();
		UserService us = new UserService();
		String user = table.getValueAt(row, 1).toString();
		id = us.getIdByUsername(user);
		if(row==-1){
			JOptionPane.showMessageDialog(null, "Select user!");
			return;
		}
	}
	private void UpdatePerformed(ActionEvent evt){
		int row = table.getSelectedRow();
		int column = table.getColumnCount();
		UserService us = new UserService();
		String user = table.getValueAt(row, 1).toString();
		User u = us.getUserById(id);
		if(row==-1){
			JOptionPane.showMessageDialog(null, "Select user!");
			return;
		}
		for(int i = 0; i < column; i++) {
			
			table.setValueAt(table.getValueAt(row, i), row, i);
			model.fireTableDataChanged();
			
		}
		System.out.println(id);
		u.setName(table.getValueAt(row, 0).toString());
		u.setUsername(table.getValueAt(row, 1).toString());
		u.setPassword(table.getValueAt(row, 2).toString());
		u.setType(Integer.parseInt(table.getValueAt(row, 3).toString()));
		us.update(id, u);
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


