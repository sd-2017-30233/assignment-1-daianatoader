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
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import business.AccountService;
import business.ClientService;
import business.UserService;
import data.Account;
import data.Account.AccountType;
import data.Client;
import data.Client.ClientType;
import javax.swing.JList;

public class ViewAccount {

	private JFrame frame;
	private JMenu mnUsers;
	private JMenu mnReports;
	private JTable table;
	private JScrollPane scrollPane;
	public DefaultTableModel model;
	public DefaultListModel listModel;
	public JList list;
	
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
	public ViewAccount() {
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
		frame.setBounds(100, 100, 632, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 205, 50));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setPreferredSize(new Dimension(200, 25));
		panel.add(splitPane);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 400));
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setAlignmentX(Component.LEFT_ALIGNMENT);
		table.setBackground(new Color(154, 205, 50));
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Type", "Amount", "ClientCNP", "CreationDate"
				}
			));
		
		scrollPane.setViewportView(table);
		

		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setPreferredSize(new Dimension(160, 25));
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_2.add(splitPane_1);
		
		JPanel panel_3 = new JPanel();
		splitPane_1.setLeftComponent(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1);
		
		listModel = new DefaultListModel();
		
		 list = new JList(listModel);
		list.setBackground(new Color(32, 178, 170));
		scrollPane_1.setViewportView(list);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(154, 205, 50));
		splitPane_1.setRightComponent(panel_4);
		panel_4.setLayout(null);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(45, 11, 112, 23);
		panel_4.add(btnDelete);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(49, 113, 108, 23);
		panel_4.add(btnLogOut);
		
		JButton btnUpdate = new JButton("Update Amount");
		btnUpdate.setBounds(45, 79, 112, 23);
		panel_4.add(btnUpdate);
		
		JButton btnGetAccounts = new JButton("Get accounts ");
		btnGetAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GetAccounts(arg0);
			}
		});
		btnGetAccounts.setBounds(45, 45, 115, 23);
		panel_4.add(btnGetAccounts);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Update(arg0);
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogOutActionPerformed(e);
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete(e);
			}
		});
	
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
	
	 model=(DefaultTableModel)table.getModel();
	
	AccountService cl = new AccountService();

	ArrayList<Account> c = (ArrayList<Account>) cl.listAll();
	for(int i = 0; i<c.size();i++){
		model.addRow(new Object[]{c.get(i).getId(),c.get(i).getAccountType().name(),c.get(i).getAmount(),c.get(i).getClient().getCnp(),c.get(i).getReport().getDate()});
	}
	
	ClientService cs =new ClientService();
	ArrayList<Client> client = cs.listAll();
	for(int i = 0; i<client.size();i++){
			listModel.addElement(client.get(i).getName());
	}
}	
	private void GetAccounts(ActionEvent evt){
		String name = list.getSelectedValue().toString();
		if(name.equals(null)){
			JOptionPane.showMessageDialog(null, "Select client!");
			return;
		}
		ClientService cs = new ClientService();
		AccountService as = new AccountService();
		ArrayList<Client> c = cs.listAll();
		Client cl = null;
		for(int i = 0; i<c.size();i++){
			if(c.get(i).getName().equals(name))
				cl = c.get(i);
		}

		model.setRowCount(0);
		
		
		ArrayList<Account> ac = (ArrayList<Account>) as.listAccountsByClient(cl.getName());
		System.out.println(ac);
		for(int i = 0; i<ac.size();i++){
			model.addRow(new Object[]{ac.get(i).getId(),ac.get(i).getAccountType().name(),ac.get(i).getAmount(),ac.get(i).getClient().getCnp(),ac.get(i).getReport().getDate()});
		}
		model.fireTableDataChanged();
			
		
	}
	private void Delete(ActionEvent evt){
		int row = table.getSelectedRow();
		if(row==-1){
			JOptionPane.showMessageDialog(null, "Select account!");
			return;
		}
		AccountService cs = new AccountService();
		ClientService cl = new ClientService();
		int id = Integer.parseInt(table.getValueAt(row, 0).toString());
		Account  c = cs.getAccountById(id);	
			
		model.removeRow(row);
		model.fireTableRowsDeleted(row, row);
		
		cs.delete(id);
	
	}
	private void Update(ActionEvent evt){
		int row = table.getSelectedRow();
		int column = table.getColumnCount();
		if(row==-1){
			JOptionPane.showMessageDialog(null, "Select account!");
			return;
		}
		AccountService cs = new AccountService();
		ClientService cl = new ClientService();
		int id = Integer.parseInt(table.getValueAt(row, 0).toString());
		Account  c = cs.getAccountById(id);
		for(int i = 0; i < column; i++) {
			
			table.setValueAt(table.getValueAt(row, i), row, i);
			model.fireTableDataChanged();
			
		}
		
		c.setId(Integer.parseInt(table.getValueAt(row, 0).toString()));
		c.setAccountType(AccountType.valueOf((table.getValueAt(row, 1).toString())));;
		c.setAmount(Double.parseDouble(table.getValueAt(row, 2).toString()));
		c.setClient(cl.getClientById(Long.parseLong(table.getValueAt(row, 3).toString())));
		
		cs.update(c);
		table.clearSelection();
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


