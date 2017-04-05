package presentation;

import static presentation.GUI.currentPassword;
import static presentation.GUI.currentUserName;

import java.awt.BorderLayout;
import java.awt.Color;
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
import business.BillService;
import business.ClientService;
import business.UserService;
import data.Account;
import data.Client;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

public class AddPayment {

	private JFrame frame;
	private JMenu mnUsers;
	private JMenu mnReports;
	private JTable accounts;
	private JList clients;
	private JTextField sum;
	public DefaultListModel modelClients;
	public DefaultTableModel modelBillAccounts;
	public DefaultTableModel tableModel;
	private JTable utilities;
	
	public Double getSum(){
		return Double.parseDouble(sum.getText());
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


	/**
	 * Create the application.
	 */
	public AddPayment() {
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
		frame.setBounds(100, 100, 642, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		menuBar.setToolTipText("");
		menuBar.setFont(new Font("Californian FB", Font.BOLD, 14));
		menuBar.setBackground(new Color(255, 255, 255));
		
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
		panel.setBackground(new Color(154, 205, 50));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(new Color(154, 205, 50));
		splitPane.setResizeWeight(0.7);
		panel.add(splitPane);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setPreferredSize(new Dimension(184, 27));
		splitPane_1.setResizeWeight(0.6);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_1);
		
		JPanel panel_3 = new JPanel();
		splitPane_1.setLeftComponent(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setPreferredSize(new Dimension(179, 24));
		splitPane_2.setResizeWeight(0.4);
		panel_3.add(splitPane_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(154, 205, 50));
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Select a client", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		splitPane_2.setLeftComponent(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane);
		
		modelClients = new DefaultListModel();
		
		clients = new JList(modelClients);
		scrollPane.setViewportView(clients);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(154, 205, 50));
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Select client account (If it's possible)", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		splitPane_2.setRightComponent(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPaneClAcc = new JScrollPane();
		scrollPaneClAcc.setForeground(new Color(255, 255, 255));
		panel_5.add(scrollPaneClAcc);
		
		accounts = new JTable();
		accounts.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Amount"
				}
			));
		scrollPaneClAcc.setViewportView(accounts);
		
		JLabel lblNewLabel = new JLabel("The client has no accounts!\r\n");
		scrollPaneClAcc.setColumnHeaderView(lblNewLabel);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(154, 205, 50));
		splitPane_1.setRightComponent(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblSum = new JLabel("Sum:");
		lblSum.setForeground(new Color(255, 255, 255));
		lblSum.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblSum.setBounds(41, 33, 55, 21);
		panel_6.add(lblSum);
		
		sum = new JTextField();
		sum.setBounds(92, 34, 86, 20);
		panel_6.add(sum);
		sum.setColumns(10);
		
		JButton btnPayment = new JButton("Pay\r\n");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PayPerformed(arg0);
			}
		});
		btnPayment.setBounds(204, 33, 89, 23);
		panel_6.add(btnPayment);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(134, 93, 89, 23);
		panel_6.add(btnLogOut);
		
		JButton btnGetAcc = new JButton("View client accounts\r\n\r\n");
		btnGetAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GetAccountsPerformed(arg0);
			}
		});
		btnGetAcc.setBounds(314, 33, 156, 23);
		panel_6.add(btnGetAcc);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogOutActionPerformed(e);
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(9, 10));
		panel_2.setBackground(new Color(154, 205, 50));
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Select utility", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);
		
		utilities = new JTable();
		utilities.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Utility"
				}
			));
		scrollPane_1.setViewportView(utilities);
		
		modelBillAccounts = (DefaultTableModel)utilities.getModel();
		
	
		
		tableModel=(DefaultTableModel)accounts.getModel();
		
		ClientService cs =new ClientService();
		ArrayList<Client> client = cs.listIndividual();
		for(int i = 0; i<client.size();i++){
				modelClients.addElement(client.get(i).getName());
		}
		AccountService as = new AccountService();
		ArrayList<Account> utility = (ArrayList<Account>) as.listLegal();
		for(int i = 0; i<utility.size();i++){
			modelBillAccounts.addRow(new Object[]{utility.get(i).getId(),utility.get(i).getClient().getName()});
		}
		
				
}
	private void PayPerformed(ActionEvent evt){
		ClientService cs =new ClientService();
		AccountService as = new AccountService();
		ArrayList<Client> client = cs.listAll();
		Client c=null;
		String name = clients.getSelectedValue().toString();
		for(int i=0; i<client.size();i++){
			if(client.get(i).getName().equals(name)){
				c=client.get(i);
			}
		}
			
		try
		{
			this.getSum();
		}
		catch(NumberFormatException e)
		{
		  JOptionPane.showMessageDialog(null, "Suma invalida!");
		  return;
		}
		
		BillService bs = new BillService();
		if(tableModel.getRowCount()==0){

			int rowTo = utilities.getSelectedRow();
			int dest = Integer.parseInt(utilities.getValueAt(rowTo, 0).toString());
			if(rowTo == -1 || clients.getSelectedValue()==null || this.getSum()==null){
				 JOptionPane.showMessageDialog(null, "Selectati toate campurile necesare!");
				  return;
			}
			bs.addWithoutAccount(c.getCnp(), this.getSum(), dest, currentUserName);
		}
		else{

			int rowTo = utilities.getSelectedRow();
			int dest = Integer.parseInt(utilities.getValueAt(rowTo, 0).toString());
			int row = accounts.getSelectedRow();
			int source = Integer.parseInt(accounts.getValueAt(row, 0).toString());
			if(rowTo == -1 || row == -1 || clients.getSelectedValue()==null || this.getSum()==null){
				 JOptionPane.showMessageDialog(null, "Selectati toate campurile necesare!");
				  return;
			}
			bs.addWithAccount(source, dest, this.getSum(), currentUserName);
			
		}
		this.sum.setText("");
		accounts.clearSelection();
		utilities.clearSelection();
		clients.clearSelection();
	}
	
	private void GetAccountsPerformed(ActionEvent evt){
		ClientService cs =new ClientService();
		AccountService as = new AccountService();
		if(clients.getSelectedValue()!=null){
			String name = clients.getSelectedValue().toString();
			ArrayList<Client> c = cs.listAll();
			Client cl = null;
			for(int i = 0; i<c.size();i++){
				if(c.get(i).getName().equals(name))
					cl = c.get(i);
			}

			tableModel.setRowCount(0);
			
			ArrayList<Account> ac = (ArrayList<Account>) as.listSpendingAccountsByClient(cl.getName());
			for(int i = 0; i<ac.size();i++){
				tableModel.addRow(new Object[]{ac.get(i).getId(),ac.get(i).getAmount()});
			}
			tableModel.fireTableDataChanged();
		}
		else{
			JOptionPane.showMessageDialog(null, "Please select a client!");
			return;
		}
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

