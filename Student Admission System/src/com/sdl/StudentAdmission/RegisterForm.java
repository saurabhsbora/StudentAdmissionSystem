package com.sdl.StudentAdmission;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;

public class RegisterForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FirstName;
	private JPasswordField Password;
	private JTextField Username;
	private JTextField LastName;
	private JTextField EmailID;
	private JTextField AdmissionYear;
	private JTextField PhoneNo;
	private Student s;
	private String dept,ey;
	private JTextField UniqueID;
	private ButtonGroup DeptGroup,EYGroup;
	private final JButton Register;
	private JFrame RegisterFrame;
	private static ThreadedClient threadedClient;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm frame = new RegisterForm();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
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
	
	public RegisterForm() {
		RegisterFrame = this;
		setUndecorated(true);
		setBounds(100, 100, 518, 884);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				WindowDragger.panelMouseDragged(e, RegisterFrame);
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
		panel.setBounds(0, 0, 518, 78);
		contentPane.add(panel);
		
		JLabel RegisterLabel = new JLabel("Register");
		RegisterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RegisterLabel.setForeground(Color.WHITE);
		RegisterLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		RegisterLabel.setBounds(12, 13, 130, 52);
		panel.add(RegisterLabel);
		
		JLabel CloseLabel = new JLabel("X");
		CloseLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CloseLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					dispose();
					StudentLogin.firstchild.setVisible(true);
			}
		});
		CloseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CloseLabel.setForeground(Color.WHITE);
		CloseLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		CloseLabel.setBounds(463, 13, 43, 45);
		panel.add(CloseLabel);
		
		JLabel MinimizeLabel = new JLabel("-");
		MinimizeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MinimizeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		MinimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MinimizeLabel.setForeground(Color.WHITE);
		MinimizeLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		MinimizeLabel.setBounds(420, 17, 43, 41);
		panel.add(MinimizeLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 78, 518, 807);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel FirstNameLabel = new JLabel("FirstName");
		FirstNameLabel.setBounds(38, 35, 104, 31);
		FirstNameLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(FirstNameLabel);
		
		FirstName = new JTextField();
		FirstName.setBounds(249, 32, 206, 37);
		FirstName.setHorizontalAlignment(SwingConstants.LEFT);
		FirstName.setFont(new Font("Calibri", Font.PLAIN, 25));
		FirstName.setColumns(10);
		panel_1.add(FirstName);
		
		JLabel LastNameLabel = new JLabel("LastName");
		LastNameLabel.setBounds(38, 109, 101, 31);
		LastNameLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(LastNameLabel);
		
		LastName = new JTextField();
		LastName.setBounds(249, 109, 206, 37);
		LastName.setHorizontalAlignment(SwingConstants.LEFT);
		LastName.setFont(new Font("Calibri", Font.PLAIN, 25));
		LastName.setColumns(10);
		panel_1.add(LastName);
		
		Username = new JTextField();
		Username.setToolTipText("Used As Username\r\n");
		Username.setBounds(249, 180, 206, 37);
		Username.setHorizontalAlignment(SwingConstants.LEFT);
		Username.setFont(new Font("Calibri", Font.PLAIN, 25));
		Username.setColumns(10);
		panel_1.add(Username);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setBounds(38, 258, 98, 31);
		PasswordLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(PasswordLabel);
		
		Password = new JPasswordField();
		Password.setBounds(249, 255, 206, 37);
		Password.setHorizontalAlignment(SwingConstants.LEFT);
		Password.setFont(new Font("Calibri", Font.PLAIN, 25));
		Password.setColumns(10);
		panel_1.add(Password);
		
		JLabel EmailIDLable = new JLabel("E-mail ID");
		EmailIDLable.setBounds(38, 330, 91, 31);
		EmailIDLable.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(EmailIDLable);
		
		EmailID = new JTextField();
		EmailID.setBounds(249, 330, 206, 37);
		EmailID.setHorizontalAlignment(SwingConstants.LEFT);
		EmailID.setFont(new Font("Calibri", Font.PLAIN, 25));
		EmailID.setColumns(10);
		panel_1.add(EmailID);
		
		JLabel DepartmentLabel = new JLabel("Department");
		DepartmentLabel.setBounds(38, 399, 122, 31);
		DepartmentLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(DepartmentLabel);
		
		JLabel AdmissionYearLabel = new JLabel("Admission Year");
		AdmissionYearLabel.setBounds(38, 468, 156, 31);
		AdmissionYearLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(AdmissionYearLabel);
		
		JLabel PhoneNoLabel = new JLabel("Phone No");
		PhoneNoLabel.setBounds(38, 537, 99, 31);
		PhoneNoLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(PhoneNoLabel);
		
		JLabel EngineeringYearLabel = new JLabel("Enginnering Year");
		EngineeringYearLabel.setBounds(38, 611, 206, 31);
		EngineeringYearLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(EngineeringYearLabel);
		
		AdmissionYear = new JTextField();
		AdmissionYear.setBounds(249, 465, 206, 37);
		AdmissionYear.setHorizontalAlignment(SwingConstants.LEFT);
		AdmissionYear.setFont(new Font("Calibri", Font.PLAIN, 25));
		AdmissionYear.setColumns(10);
		panel_1.add(AdmissionYear);
		
		PhoneNo = new JTextField();
		PhoneNo.setBounds(249, 534, 206, 37);
		PhoneNo.setHorizontalAlignment(SwingConstants.LEFT);
		PhoneNo.setFont(new Font("Calibri", Font.PLAIN, 25));
		PhoneNo.setColumns(10);
		panel_1.add(PhoneNo);
		
		JRadioButton CS_rb = new JRadioButton("CS");
		CS_rb.setBounds(249, 399, 60, 31);
		CS_rb.setBackground(Color.WHITE);
		CS_rb.setFont(new Font("Calibri", Font.PLAIN, 22));
		panel_1.add(CS_rb);
		
		JRadioButton IT_rb = new JRadioButton("IT");
		IT_rb.setBounds(322, 400, 49, 31);
		IT_rb.setBackground(Color.WHITE);
		IT_rb.setFont(new Font("Calibri", Font.PLAIN, 22));
		panel_1.add(IT_rb);
		
		JRadioButton ENTC_rb = new JRadioButton("ENTC");
		ENTC_rb.setBounds(378, 400, 77, 31);
		ENTC_rb.setBackground(Color.WHITE);
		ENTC_rb.setFont(new Font("Calibri", Font.PLAIN, 22));
		panel_1.add(ENTC_rb);
		
		JRadioButton FE_rb = new JRadioButton("FE");
		FE_rb.setBounds(249, 612, 49, 31);
		FE_rb.setFont(new Font("Calibri", Font.PLAIN, 22));
		FE_rb.setBackground(Color.WHITE);
		panel_1.add(FE_rb);
		
		JRadioButton SE_rb = new JRadioButton("SE");
		SE_rb.setBounds(314, 612, 49, 31);
		SE_rb.setFont(new Font("Calibri", Font.PLAIN, 22));
		SE_rb.setBackground(Color.WHITE);
		panel_1.add(SE_rb);
		
		JRadioButton TE_rb = new JRadioButton("TE");
		TE_rb.setBounds(375, 612, 51, 31);
		TE_rb.setFont(new Font("Calibri", Font.PLAIN, 22));
		TE_rb.setBackground(Color.WHITE);
		panel_1.add(TE_rb);
		
		JRadioButton BE_rb = new JRadioButton("BE");
		BE_rb.setBounds(439, 612, 51, 31);
		BE_rb.setFont(new Font("Calibri", Font.PLAIN, 22));
		BE_rb.setBackground(Color.WHITE);
		panel_1.add(BE_rb);
		
		Register = new JButton("REGISTER");
		Register.setBounds(38, 746, 452, 48);
		Register.setRequestFocusEnabled(false);
		Register.setFocusPainted(false);
		Register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bindToServer();
					register(Register);
				} catch (NoSuchAlgorithmException | IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		Register.setForeground(Color.WHITE);
		Register.setFont(new Font("Calibri", Font.PLAIN, 25));
		Register.setBackground(new Color(0, 102, 255));
		panel_1.add(Register);
		
		DeptGroup = new ButtonGroup();
		DeptGroup.add(CS_rb);
		DeptGroup.add(IT_rb);
		DeptGroup.add(ENTC_rb);
		CS_rb.setActionCommand("CS");
		IT_rb.setActionCommand("IT");
		ENTC_rb.setActionCommand("ENTC");
		
		
		EYGroup = new ButtonGroup();
		EYGroup.add(FE_rb);
		EYGroup.add(SE_rb);
		EYGroup.add(TE_rb);
		EYGroup.add(BE_rb);
		FE_rb.setActionCommand("FE");
		SE_rb.setActionCommand("SE");
		TE_rb.setActionCommand("TE");
		BE_rb.setActionCommand("BE");
		
		JLabel UniqueIDlabel = new JLabel("Unique ID");
		UniqueIDlabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		UniqueIDlabel.setBounds(38, 673, 100, 31);
		panel_1.add(UniqueIDlabel);
		
		UniqueID = new JTextField();
		UniqueID.setToolTipText("");
		UniqueID.setHorizontalAlignment(SwingConstants.LEFT);
		UniqueID.setFont(new Font("Calibri", Font.PLAIN, 25));
		UniqueID.setColumns(10);
		UniqueID.setBounds(249, 670, 206, 37);
		panel_1.add(UniqueID);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		UsernameLabel.setBounds(38, 183, 104, 31);
		panel_1.add(UsernameLabel);
		
	}
	public void register(JButton btn) throws NoSuchAlgorithmException, IOException
	{
		String fn,ln,eid,pass,usern;
		Wrapper w;
		char [] p;
		int uid,ay;
		long pno;
		
		dept = DeptGroup.getSelection().getActionCommand();
		ey = EYGroup.getSelection().getActionCommand();
		fn = FirstName.getText();
		ln = LastName.getText();
		eid = EmailID.getText();
		p = Password.getPassword();
		pass = new String(p);
		usern = Username.getText();
		uid = Integer.parseInt(UniqueID.getText());
		pno = Long.parseLong(PhoneNo.getText());
		ay = Integer.parseInt(AdmissionYear.getText());
		s = new Student(fn,ln,eid,dept,ey,ay,uid,pno);
		
		FeeReport fee = new FeeReport(uid, dept);
		w = new Wrapper(s,uid, usern, pass,fee);
		threadedClient.sendObjectToServer(w);
		JOptionPane.showMessageDialog(null, "Successfully Registered");
		threadedClient.close();
		dispose();
		StudentLogin.firstchild.setVisible(true);
	}
}
