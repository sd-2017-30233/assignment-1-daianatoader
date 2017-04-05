package presentation;

import static presentation.GUI.currentPassword;
import static presentation.GUI.currentUserName;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import business.AccountService;
import business.ClientService;
import business.TransferService;
import business.UserService;
import data.Account;

public class AddTransfer {

	private JFrame frame;
	private JMenu mnUsers;
	private JMenu mnReports;
	private JTable tableFrom;
	private JTable tableTo;
	public DefaultTableModel modelFrom;
	public DefaultTableModel modelTo;
	private JTextField textField;
	private Double getAmount(){
		return Double.parseDouble(textField.getText());
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
	public AddTransfer() {
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

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 765, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 205, 50));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.6);
		panel.add(splitPane);
		
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setPreferredSize(new Dimension(260, 32));
		splitPane_1.setResizeWeight(0.5);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(154, 205, 50));
		panel_3.setBorder(new TitledBorder(null, "Source Account", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_3.setPreferredSize(new Dimension(20, 44));
		splitPane_1.setLeftComponent(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPaneFrom = new JScrollPane();
		scrollPaneFrom.setBackground(new Color(154, 205, 50));
		scrollPaneFrom.setName("ea");
		scrollPaneFrom.setPreferredSize(new Dimension(6, 41));
		
		panel_3.add(scrollPaneFrom);
		
		tableFrom = new JTable();
		tableFrom.setBackground(new Color(154, 205, 50));
		
		tableFrom.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Client", "AccountNr", "Type", "Amount"
				}
			));
		
		scrollPaneFrom.setViewportView(tableFrom);
		
		JPanel panel_4 = new JPanel();
		panel_4.setFont(new Font("Californian FB", Font.BOLD, 13));
		panel_4.setBackground(new Color(154, 205, 50));
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Destination Account", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		splitPane_1.setRightComponent(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPaneTo = new JScrollPane();
		scrollPaneTo.setBackground(new Color(154, 205, 50));
		panel_4.add(scrollPaneTo);
		
		
		tableTo = new JTable();
		tableTo.setBackground(new Color(154, 205, 50));
		
		tableTo.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Client", "AccountNr", "Type", "Amount"
				}
			));
		
		scrollPaneTo.setViewportView(tableTo);
		
		modelFrom=(DefaultTableModel)tableFrom.getModel();
		modelTo=(DefaultTableModel)tableTo.getModel();
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(6, 10));
		panel_2.setBackground(new Color(255, 250, 250));
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblSelectASource = new JLabel("  Select a source and \r\n");
		lblSelectASource.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblSelectASource.setBounds(24, 29, 196, 39);
		panel_2.add(lblSelectASource);
		
		JLabel lblNewLabel = new JLabel("a destination account!");
		lblNewLabel.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblNewLabel.setBounds(24, 59, 177, 29);
		panel_2.add(lblNewLabel);
		
		JLabel lblAmountOfMoney = new JLabel("Amount of money");
		lblAmountOfMoney.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblAmountOfMoney.setBounds(34, 216, 142, 29);
		panel_2.add(lblAmountOfMoney);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(63, 351, 92, 29);
		panel_2.add(btnLogOut);
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogOutActionPerformed(e);
			}
		});
		
		JButton btnTransfer = new JButton("Transfer");
		
		btnTransfer.setBounds(63, 311, 92, 29);
		panel_2.add(btnTransfer);
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferPerformed(e);
			}
		});
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		textField.setBounds(47, 280, 115, 20);
		panel_2.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("you want to transfer:");
		lblNewLabel_1.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblNewLabel_1.setBounds(24, 241, 163, 28);
		panel_2.add(lblNewLabel_1);
	
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
	
	AccountService cl = new AccountService();

	ArrayList<Account> c = (ArrayList<Account>) cl.listSpendingAccounts();
	for(int i = 0; i<c.size();i++){
		modelFrom.addRow(new Object[]{c.get(i).getClient().getName(),c.get(i).getId(),c.get(i).getAccountType().name(),c.get(i).getAmount()});
	}
	ArrayList<Account> c2 = (ArrayList<Account>) cl.listAll();
	for(int i = 0; i<c2.size();i++){
		modelTo.addRow(new Object[]{c2.get(i).getClient().getName(),c2.get(i).getId(),c2.get(i).getAccountType().name(),c2.get(i).getAmount()});
	}
	

}
	private void TransferPerformed(ActionEvent evt){

		int row = tableFrom.getSelectedRow();
		int rowTo = tableTo.getSelectedRow();
		
		if(row == -1 || rowTo == -1){
			 JOptionPane.showMessageDialog(null, "Selectati cont sursa/destinatie!");
			  return;
		}
		
		int idFrom = Integer.parseInt(tableFrom.getValueAt(row, 1).toString());
		int idTo = Integer.parseInt(tableTo.getValueAt(rowTo, 1).toString());
		
		if(this.getAmount()==null){
			JOptionPane.showMessageDialog(null, "Adaugati suma dorita!");
			
			  return;
		}
		
		try
		{
			this.getAmount();
		}
		catch(NumberFormatException e)
		{
		  JOptionPane.showMessageDialog(null, "Suma invalida!");
		  this.textField.setText("");
		  tableFrom.clearSelection();
		  tableTo.clearSelection();
		  return;
		}
		double amount = this.getAmount();
		
		TransferService t = new TransferService();
		
		t.addTransfer(idFrom, idTo, amount, currentUserName);
		this.textField.setText("");
		tableFrom.clearSelection();
		  tableTo.clearSelection();
	}
	
	private void LogOutActionPerformed(ActionEvent e){
		this.getFrame().setVisible(false);
		GUI gui = new GUI();
		gui.getFrmDBank().setVisible(true);
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
}


