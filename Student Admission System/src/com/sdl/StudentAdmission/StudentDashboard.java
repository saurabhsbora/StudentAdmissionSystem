package com.sdl.StudentAdmission;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseMotionAdapter;

public class StudentDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame StudentDashboardFrame;
	private JTable table;
	private JTextField UFirstNameTextField;
	private JTextField ULastNameTextField;
	private JTextField UUsernameTextField;
	private JPasswordField UPassTextField;
	private JTextField UEmailTextField;
	private JTextField UPhoneTextField;
	private ThreadedClient threadedClient;
	private Vector<Student> vs;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDashboard frame = new StudentDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void bindToServer() throws IOException, InterruptedException
	{
		threadedClient = new ThreadedClient();
		new Thread(threadedClient).start();
	}
	public String getFeePaidStatus() throws IOException, InterruptedException
	{
		Wrapper wsend;
		String ans;
		wsend = new Wrapper(vs.firstElement().getId(), 5);
		bindToServer();
		threadedClient.sendObjectToServer(wsend);
		ans = threadedClient.receiveMsgFromServer();
		threadedClient.close();
		return ans;
	}
	public String updateFeeReport() throws ParseException, IOException, InterruptedException
	{
		Wrapper wsend;
		String ans;
		FeeReport fr = new FeeReport(vs.firstElement().getId(), vs.firstElement().getDept());
		fr.setFee_date(FeeReport.getcurrentSqlDate());
		fr.setStatus("P");
		wsend = new Wrapper(fr);
		bindToServer();
		threadedClient.sendObjectToServer(wsend);
		ans = threadedClient.receiveMsgFromServer();
		threadedClient.close();
		return ans;
	}
	public String UpdateDetails() throws IOException, InterruptedException
	{
		String fname = null,lname = null ,uname = null ,email = null ,pass = null, ans = null;
		char [] p;
		long pno = 0;
		Wrapper wsend;
		p = UPassTextField.getPassword();
		pass = new String(p);
		fname = UFirstNameTextField.getText();
		lname = ULastNameTextField.getText();
		uname = UUsernameTextField.getText();
		email = UEmailTextField.getText();
		if(!UPhoneTextField.getText().equals(""))
			pno = Long.parseLong(UPhoneTextField.getText());		
		Student s = new Student(fname,lname,email,pno);
		wsend = new Wrapper(s,uname,pass,vs.firstElement().getId());
		bindToServer();
		threadedClient.sendObjectToServer(wsend);
		ans = threadedClient.receiveMsgFromServer();
		threadedClient.close();
		return ans;
	}
	public Vector<Student> loadProfile() throws IOException, InterruptedException, ClassNotFoundException
	{
		Wrapper wsend,wrecv;
		Vector<Student> vs = null;
		bindToServer();
		wsend = new Wrapper(vs,StudentLogin.UsernameField.getText());
		threadedClient.sendObjectToServer(wsend);
		wrecv = threadedClient.recieveObjectFromServer();
		threadedClient.close();
		return wrecv.getStudentVector();
	}
	public StudentDashboard() throws ClassNotFoundException, IOException, InterruptedException {
		StudentDashboardFrame = this;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1181, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				WindowDragger.panelMouseDragged(e, StudentDashboardFrame);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				WindowDragger.panelMousePressed(e);
			}
		});
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 255));
		panel.setBounds(0, 0, 1181, 78);
		contentPane.add(panel);
		
		JLabel TitleLabel = new JLabel();
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setForeground(Color.WHITE);
		TitleLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		TitleLabel.setBounds(0, 0, 322, 78);
		panel.add(TitleLabel);
		
		JLabel CloseLabel = new JLabel("X");
		CloseLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a=JOptionPane.showConfirmDialog(null,"Want to Logout?");
				if(a==JOptionPane.YES_OPTION)
				{ 
					dispose();
					StudentLogin.firstchild.setVisible(true);
				}
			}
		});
		CloseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CloseLabel.setForeground(Color.WHITE);
		CloseLabel.setFont(new Font("Calibri", Font.PLAIN, 45));
		CloseLabel.setBounds(1104, 0, 77, 78);
		panel.add(CloseLabel);
		
		JLabel MinimizeLabel = new JLabel("-");
		MinimizeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		MinimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MinimizeLabel.setForeground(Color.WHITE);
		MinimizeLabel.setFont(new Font("Calibri", Font.PLAIN, 45));
		MinimizeLabel.setBounds(1051, 0, 77, 78);
		panel.add(MinimizeLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 78, 1181, 554);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Calibri", Font.PLAIN, 25));
		tabbedPane.setBounds(0, 0, 1181, 554);
		panel_1.add(tabbedPane);
		
		JPanel ProfilePanel = new JPanel();
		ProfilePanel.setFont(new Font("Calibri", Font.PLAIN, 25));
		ProfilePanel.setBackground(Color.WHITE);
		ProfilePanel.setLayout(null);
		
		JLabel ProfileImgLabel = new JLabel("");
		ProfileImgLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		ProfileImgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ProfileImgLabel.setBackground(Color.WHITE);
		ProfileImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/Profile.png")).getImage();
		ProfileImgLabel.setIcon(new ImageIcon(img));
		ProfileImgLabel.setBounds(12, 13, 200, 193);
		ProfilePanel.add(ProfileImgLabel);
		
		
		
		JPanel FeePanel = new JPanel();
		FeePanel.setFont(new Font("Calibri", Font.PLAIN, 25));
		FeePanel.setBackground(Color.WHITE);
		
		JPanel UpdatePanel = new JPanel();
		UpdatePanel.setBackground(Color.WHITE);
		UpdatePanel.setLayout(null);
		
		tabbedPane.addTab("<html><body><table width='370'>Profile</table></body></html>",ProfilePanel);
		
		JLabel FnameLabel = new JLabel("First Name :");
		FnameLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		FnameLabel.setBounds(303, 13, 184, 31);
		ProfilePanel.add(FnameLabel);
		
		JLabel LnameLabel = new JLabel("Last Name :");
		LnameLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		LnameLabel.setBounds(303, 76, 184, 31);
		ProfilePanel.add(LnameLabel);
		
		JLabel EmailLabel = new JLabel("Email-ID :");
		EmailLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		EmailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		EmailLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		EmailLabel.setBounds(303, 139, 184, 31);
		ProfilePanel.add(EmailLabel);
		
		JLabel DepartmentLabel = new JLabel("Department :");
		DepartmentLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		DepartmentLabel.setHorizontalAlignment(SwingConstants.LEFT);
		DepartmentLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		DepartmentLabel.setBounds(303, 202, 184, 31);
		ProfilePanel.add(DepartmentLabel);
		
		JLabel AdmissionYrLabel = new JLabel("Admission Year :");
		AdmissionYrLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		AdmissionYrLabel.setBounds(303, 265, 184, 31);
		ProfilePanel.add(AdmissionYrLabel);
		
		JLabel EngineeringYrLabel = new JLabel("Engineering Year :");
		EngineeringYrLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		EngineeringYrLabel.setBounds(303, 328, 184, 31);
		ProfilePanel.add(EngineeringYrLabel);
		
		JLabel UIDLabel = new JLabel("Unique ID :");
		UIDLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		UIDLabel.setBounds(303, 391, 184, 31);
		ProfilePanel.add(UIDLabel);
		
		JLabel PhoneNoLabel = new JLabel("Phone Number :");
		PhoneNoLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		PhoneNoLabel.setBounds(303, 454, 184, 31);
		ProfilePanel.add(PhoneNoLabel);
		
		vs =loadProfile();
		
		TitleLabel.setText("Welcome "+vs.firstElement().getFname()+"!");
		
		JLabel labelFN = new JLabel();
		labelFN.setText(vs.firstElement().getFname());
		labelFN.setHorizontalTextPosition(SwingConstants.LEFT);
		labelFN.setHorizontalAlignment(SwingConstants.LEFT);
		labelFN.setFont(new Font("Calibri", Font.PLAIN, 25));
		labelFN.setBounds(499, 13, 308, 31);
		ProfilePanel.add(labelFN);
		
		JLabel labelLN = new JLabel();
		labelLN.setText(vs.firstElement().getLname());
		labelLN.setHorizontalTextPosition(SwingConstants.LEFT);
		labelLN.setHorizontalAlignment(SwingConstants.LEFT);
		labelLN.setFont(new Font("Calibri", Font.PLAIN, 25));
		labelLN.setBounds(499, 76, 308, 31);
		ProfilePanel.add(labelLN);
		
		JLabel labelEmail = new JLabel();
		labelEmail.setText(vs.firstElement().getEmail_id());
		labelEmail.setHorizontalTextPosition(SwingConstants.LEFT);
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		labelEmail.setFont(new Font("Calibri", Font.PLAIN, 25));
		labelEmail.setBounds(499, 139, 308, 31);
		ProfilePanel.add(labelEmail);
		
		JLabel labelDept = new JLabel();
		labelDept.setText(vs.firstElement().getDept());
		labelDept.setHorizontalTextPosition(SwingConstants.LEFT);
		labelDept.setHorizontalAlignment(SwingConstants.LEFT);
		labelDept.setFont(new Font("Calibri", Font.PLAIN, 25));
		labelDept.setBounds(499, 202, 308, 31);
		ProfilePanel.add(labelDept);
		
		JLabel labelAdy = new JLabel();
		labelAdy.setText(Integer.toString(vs.firstElement().getadmYear()));
		labelAdy.setHorizontalTextPosition(SwingConstants.LEFT);
		labelAdy.setHorizontalAlignment(SwingConstants.LEFT);
		labelAdy.setFont(new Font("Calibri", Font.PLAIN, 25));
		labelAdy.setBounds(499, 265, 308, 31);
		ProfilePanel.add(labelAdy);
		
		JLabel labelEy = new JLabel();
		labelEy.setText(vs.firstElement().getEngyear());
		labelEy.setHorizontalTextPosition(SwingConstants.LEFT);
		labelEy.setHorizontalAlignment(SwingConstants.LEFT);
		labelEy.setFont(new Font("Calibri", Font.PLAIN, 25));
		labelEy.setBounds(499, 328, 308, 31);
		ProfilePanel.add(labelEy);
		
		JLabel labelUID = new JLabel();
		labelUID.setText(Integer.toString(vs.firstElement().getId()));
		labelUID.setHorizontalTextPosition(SwingConstants.LEFT);
		labelUID.setHorizontalAlignment(SwingConstants.LEFT);
		labelUID.setFont(new Font("Calibri", Font.PLAIN, 25));
		labelUID.setBounds(499, 391, 308, 31);
		ProfilePanel.add(labelUID);
		
		JLabel labelPhone = new JLabel();
		labelPhone.setText(Long.toString(vs.firstElement().getPhone_no()));
		labelPhone.setHorizontalTextPosition(SwingConstants.LEFT);
		labelPhone.setHorizontalAlignment(SwingConstants.LEFT);
		labelPhone.setFont(new Font("Calibri", Font.PLAIN, 25));
		labelPhone.setBounds(499, 454, 308, 31);
		ProfilePanel.add(labelPhone);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(830, 0, 2, 500);
		ProfilePanel.add(separator);
		
		JSeparator PSeperatorV = new JSeparator();
		PSeperatorV.setOrientation(SwingConstants.VERTICAL);
		PSeperatorV.setBounds(220, 0, 1, 218);
		ProfilePanel.add(PSeperatorV);
		
		JSeparator PSeperatorH = new JSeparator();
		PSeperatorH.setBounds(0, 217, 220, 1);
		ProfilePanel.add(PSeperatorH);
		
		JLabel lblAdmissionStatus = new JLabel("Admission Status :");
		lblAdmissionStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmissionStatus.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblAdmissionStatus.setBounds(902, 188, 207, 46);
		ProfilePanel.add(lblAdmissionStatus);
		
		final String reply = getFeePaidStatus();
		JLabel labelStatus = new JLabel();
		if(reply.equals("P"))
			labelStatus.setText("Admitted!");
		else
			labelStatus.setText("Pending");
		labelStatus.setHorizontalAlignment(SwingConstants.CENTER);
		labelStatus.setFont(new Font("Calibri", Font.PLAIN, 28));
		labelStatus.setBounds(902, 265, 200, 46);
		ProfilePanel.add(labelStatus);
		tabbedPane.addTab("<html><body><table width='370'>Fee Report</table></body></html>",FeePanel);
		FeePanel.setLayout(null);
		
		JPanel InnerPanel = new JPanel();
		InnerPanel.setBounds(0, 0, 1176, 433);
		FeePanel.add(InnerPanel);
		
		Object[][] data ={
			{"01", "Admission Fee", "Rs. 1,000/-","Rs. 500/-", "Rs. 2,000/-"},
			{"02", "Tution Fee", "Rs. 6,000/-","Rs. 3,000/-", "Rs. 4,000/-"},
			{"03", "University Development Fee", "Rs. 2,200/-","Rs. 2,200/-", "Rs. 2,200/-"},
			{"04", "Identity Card", "Rs. 23/-","Rs. 23/-", "Rs. 23/-"},
			{"05", "Admission Form", "Rs. 10/-","Rs. 10/-", "Rs. 10/-"},
			{"06", "Medical Fee", "Rs. 50/-","Rs. 50/-", "Rs. 50/-"},
			{"07", "Athletic Fee", "Rs. 21/-","Rs. 21/-", "Rs. 21/-"},
			{"08", "Institute Fee", "Rs. 7/-","Rs. 7/-", "Rs. 7/-"},
			{"09", "College Magazine", "Rs. 125/-","Rs. 125/-", "Rs. 125/-"},
			{null, "TOTAL", "Rs. 9436/-","Rs. 5936/-", "Rs. 8436/-"}
		};
		
		String columns[] = {"", "", "CS", "IT", "ENTC"};
		
		DefaultTableModel model = new DefaultTableModel(data, columns);
		table = new JTable();
		table.setRowHeight(40);
		table.setModel(model); 
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		InnerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		table.setFont(new Font("Calibri", Font.PLAIN, 20));
		table.setBounds(0, 0, 1176, 360);
		
		Font bigFont = new Font("Calibri", Font.PLAIN, 20); 
		JTableHeader header = table.getTableHeader();
		header.setFont(bigFont);
		
		JScrollPane scrollPane = new JScrollPane(table);
		InnerPanel.add(scrollPane);
		table.setFillsViewportHeight(true);
		
		final JButton btnPayFee = new JButton("Pay Your Fee"); 
		if(reply.equals("P"))
		{
			btnPayFee.setText("Paid");
			btnPayFee.setEnabled(false);
		}
		btnPayFee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
						if(reply.equals("NP"))
						{
							String ans = updateFeeReport();
							JOptionPane.showMessageDialog(btnPayFee, ans);
							btnPayFee.setEnabled(false);
							btnPayFee.setText("Paid");
						}
					}
					catch (IOException | InterruptedException | ParseException e) {
					e.printStackTrace();
				}
			}
		});
		btnPayFee.setRequestFocusEnabled(false);
		btnPayFee.setForeground(Color.WHITE);
		btnPayFee.setFont(new Font("Calibri", Font.PLAIN, 25));
		btnPayFee.setFocusPainted(false);
		btnPayFee.setBackground(new Color(0, 102, 255));
		btnPayFee.setBounds(374, 446, 373, 40);
		FeePanel.add(btnPayFee);
		
		tabbedPane.addTab("<html><body><table width='370'>Update Details</table></body></html>",UpdatePanel);
		
		JLabel UFirstName = new JLabel("FirstName");
		UFirstName.setFont(new Font("Calibri", Font.PLAIN, 25));
		UFirstName.setBounds(76, 148, 104, 31);
		UpdatePanel.add(UFirstName);
		
		UFirstNameTextField = new JTextField();
		UFirstNameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		UFirstNameTextField.setFont(new Font("Calibri", Font.PLAIN, 25));
		UFirstNameTextField.setColumns(10);
		UFirstNameTextField.setBounds(215, 145, 206, 37);
		UpdatePanel.add(UFirstNameTextField);
		
		JLabel ULastName = new JLabel("LastName");
		ULastName.setFont(new Font("Calibri", Font.PLAIN, 25));
		ULastName.setBounds(675, 145, 101, 31);
		UpdatePanel.add(ULastName);
		
		ULastNameTextField = new JTextField();
		ULastNameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		ULastNameTextField.setFont(new Font("Calibri", Font.PLAIN, 25));
		ULastNameTextField.setColumns(10);
		ULastNameTextField.setBounds(849, 142, 206, 37);
		UpdatePanel.add(ULastNameTextField);
		
		JLabel UpdateUsername = new JLabel("Username");
		UpdateUsername.setFont(new Font("Calibri", Font.PLAIN, 25));
		UpdateUsername.setBounds(675, 220, 104, 31);
		UpdatePanel.add(UpdateUsername);
		
		UUsernameTextField = new JTextField();
		UUsernameTextField.setToolTipText("Used As Username\r\n");
		UUsernameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		UUsernameTextField.setFont(new Font("Calibri", Font.PLAIN, 25));
		UUsernameTextField.setColumns(10);
		UUsernameTextField.setBounds(849, 217, 206, 37);
		UpdatePanel.add(UUsernameTextField);
		
		JLabel UpdatePass = new JLabel("Password");
		UpdatePass.setFont(new Font("Calibri", Font.PLAIN, 25));
		UpdatePass.setBounds(675, 296, 98, 31);
		UpdatePanel.add(UpdatePass);
		
		UPassTextField = new JPasswordField();
		UPassTextField.setHorizontalAlignment(SwingConstants.LEFT);
		UPassTextField.setFont(new Font("Calibri", Font.PLAIN, 25));
		UPassTextField.setColumns(10);
		UPassTextField.setBounds(849, 293, 206, 37);
		UpdatePanel.add(UPassTextField);
		
		JLabel UpdateEmail = new JLabel("E-mail ID");
		UpdateEmail.setFont(new Font("Calibri", Font.PLAIN, 25));
		UpdateEmail.setBounds(76, 223, 91, 31);
		UpdatePanel.add(UpdateEmail);
		
		UEmailTextField = new JTextField();
		UEmailTextField.setHorizontalAlignment(SwingConstants.LEFT);
		UEmailTextField.setFont(new Font("Calibri", Font.PLAIN, 25));
		UEmailTextField.setColumns(10);
		UEmailTextField.setBounds(215, 220, 206, 37);
		UpdatePanel.add(UEmailTextField);
		
		JLabel UpdatePhone = new JLabel("Phone No");
		UpdatePhone.setFont(new Font("Calibri", Font.PLAIN, 25));
		UpdatePhone.setBounds(76, 299, 99, 31);
		UpdatePanel.add(UpdatePhone);
		
		UPhoneTextField = new JTextField();
		UPhoneTextField.setHorizontalAlignment(SwingConstants.LEFT);
		UPhoneTextField.setFont(new Font("Calibri", Font.PLAIN, 25));
		UPhoneTextField.setColumns(10);
		UPhoneTextField.setBounds(215, 296, 206, 37);
		UpdatePanel.add(UPhoneTextField);
		
		JLabel lblNewLabel = new JLabel("Enter the details to Update or leave blank.");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblNewLabel.setBounds(313, 13, 478, 56);
		UpdatePanel.add(lblNewLabel);
		
		final JButton btnUpdateDetails = new JButton("Update Details");
		btnUpdateDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
						String s = UpdateDetails();
						JOptionPane.showMessageDialog(btnUpdateDetails, s);
					
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdateDetails.setRequestFocusEnabled(false);
		btnUpdateDetails.setForeground(Color.WHITE);
		btnUpdateDetails.setFont(new Font("Calibri", Font.PLAIN, 25));
		btnUpdateDetails.setFocusPainted(false);
		btnUpdateDetails.setBackground(new Color(0, 102, 255));
		btnUpdateDetails.setBounds(407, 396, 274, 56);
		UpdatePanel.add(btnUpdateDetails);
	}
}
