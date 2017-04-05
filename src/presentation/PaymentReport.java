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

import business.BillService;
import business.TransferService;
import business.UserService;
import data.Bill;
import data.Transfer;
import data.User;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Dimension;

public class PaymentReport {

	private JFrame frame;
	private JMenu mnUsers;
	private JMenu mnReports;
	private JTable table;
	private JList list;
	private JTextField day;
	private JTextField month;
	private JTextField year;
	private DefaultTableModel modelTable;
	private DefaultListModel modelList;
	private JScrollPane scrollPane_1;
	
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
	public PaymentReport() {
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
		frame.setBounds(100, 100, 704, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 205, 50));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.4);
		panel.add(splitPane);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.5);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_1);
		
		JPanel panel_3 = new JPanel();
		splitPane_1.setLeftComponent(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane);
		
		modelList = new DefaultListModel();
		list = new JList(modelList);
		list.setBackground(new Color(154, 205, 50));
		scrollPane.setViewportView(list);
		
		JPanel panel_4 = new JPanel();
		splitPane_1.setRightComponent(panel_4);
		panel_4.setLayout(null);
		
		day = new JTextField();
		day.setBounds(10, 46, 54, 20);
		panel_4.add(day);
		day.setColumns(10);
		
		month = new JTextField();
		month.setBounds(89, 46, 54, 20);
		panel_4.add(month);
		month.setColumns(10);
		
		year = new JTextField();
		year.setBounds(166, 46, 54, 20);
		panel_4.add(year);
		year.setColumns(10);
		
		JLabel lblDd = new JLabel("dd");
		lblDd.setBounds(28, 21, 46, 14);
		panel_4.add(lblDd);
		
		JLabel lblMm = new JLabel("mm");
		lblMm.setBounds(107, 21, 46, 14);
		panel_4.add(lblMm);
		
		JLabel lblYyyy = new JLabel("yyyy");
		lblYyyy.setBounds(174, 21, 46, 14);
		panel_4.add(lblYyyy);
		
		JButton btnGetReports = new JButton("Get reports");
		btnGetReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetReports(e);
			}
		});
		btnGetReports.setBounds(10, 77, 107, 23);
		panel_4.add(btnGetReports);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(141, 77, 79, 23);
		panel_4.add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogOutActionPerformed(e);
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(9, 10));
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);
		
		table = new JTable();
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(154, 205, 50));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Client",  "Utility", "Sum"
				}
			));
		scrollPane_1.setViewportView(table);
	
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



	modelTable=(DefaultTableModel)table.getModel();
	UserService cs =new UserService();
	ArrayList<User> user = (ArrayList<User>) cs.listAll();
	for(int i = 0; i<user.size();i++){
			modelList.addElement(user.get(i).getUsername());
	}
}
	private void GetReports(ActionEvent evt){
		modelTable.setRowCount(0);
		int d,m,y;
		try{
			Integer.parseInt(this.day.getText());
			Integer.parseInt(this.month.getText());
			Integer.parseInt(this.year.getText());
		}
		catch(NumberFormatException e)
		{
		  JOptionPane.showMessageDialog(null, "Data invalida!");
		  return;
		}
		d = Integer.parseInt(this.day.getText());
		m = Integer.parseInt(this.month.getText());
		y = Integer.parseInt(this.year.getText());
		String ds=null, ms=null, ys=null;
		if(d<1 || d>31 || m<1 || m>12 || y<2000 || y>2999){
			JOptionPane.showMessageDialog(null, "Data invalida!");
			this.day.setText("");
			this.month.setText("");
			this.year.setText("");
			  return;
		}
		if(d<10){
			ds="0"+d;
		}
		else{
			ds=Integer.toString(d);
		}
		if(m<10){
			ms="0"+m;
		}
		else{
			ms=Integer.toString(m);
		}
		
			ys = Integer.toString(y);
			ys = ys.substring(2, 4);
			String date = ds+"/"+ms+"/"+ys;
		String user = list.getSelectedValue().toString();
		if(user.equals(null)){
			JOptionPane.showMessageDialog(null, "Selectati user!");
			this.day.setText("");
			this.month.setText("");
			this.year.setText("");
			list.clearSelection();
			return;
		}
		
		BillService ts = new BillService();

		ArrayList<Bill> t = (ArrayList<Bill>) ts.listByUserDate(user, date);
		if(t.isEmpty()){
			JOptionPane.showMessageDialog(null, "Nu s-au efectuat plati in aceasta zi!");
			this.day.setText("");
			this.month.setText("");
			this.year.setText("");
			list.clearSelection();
			return;
		}
		for(int i = 0; i<t.size();i++){
			modelTable.addRow(new Object[]{t.get(i).getClient().getName(),t.get(i).getAccount().getClient().getName(),t.get(i).getAmount()});
		}
		this.day.setText("");
		this.month.setText("");
		this.year.setText("");
		list.clearSelection();
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


