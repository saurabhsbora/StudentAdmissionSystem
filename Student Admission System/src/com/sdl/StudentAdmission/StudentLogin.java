package com.sdl.StudentAdmission;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;

public class StudentLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextField UsernameField;
	private JPasswordField passwordField;
	private ThreadedClient threadedClient;
	public static JFrame firstchild;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLogin frame = new StudentLogin();
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
	public void setDashboard() throws ClassNotFoundException, IOException, SQLException, InterruptedException
	{
		StudentDashboard sd = new StudentDashboard();
		sd.setVisible(true);
		sd.setLocationRelativeTo(null);
		firstchild.setVisible(false);
	}
	public StudentLogin() throws IOException, InterruptedException {
		firstchild = this;
		setBounds(100, 100, 518, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				WindowDragger.panelMouseDragged(e, firstchild);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				WindowDragger.panelMousePressed(e);
			}
		});
		panel.setBackground(new Color(0, 102, 255));
		panel.setBounds(0, 0, 518, 78);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel LoginLabel = new JLabel("Student Login");
		LoginLabel.setForeground(new Color(255, 255, 255));
		LoginLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		LoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LoginLabel.setBounds(12, 13, 198, 52);
		panel.add(LoginLabel);
		
		JLabel CloseLabel = new JLabel("X");
		CloseLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CloseLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				StudentAdmissionPortal.parent.setVisible(true);
			}
		});
		CloseLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		CloseLabel.setForeground(new Color(255, 255, 255));
		CloseLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
		MinimizeLabel.setForeground(new Color(255, 255, 255));
		MinimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MinimizeLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		MinimizeLabel.setBounds(420, 17, 43, 41);
		panel.add(MinimizeLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 77, 518, 315);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel UsernameField_1 = new JLabel("Username");
		UsernameField_1.setBounds(38, 61, 104, 31);
		UsernameField_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(UsernameField_1);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(180, 58, 206, 37);
		UsernameField.setHorizontalAlignment(SwingConstants.LEFT);
		UsernameField.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(UsernameField);
		UsernameField.setColumns(10);
		
		JLabel PasswordField = new JLabel("Password");
		PasswordField.setBounds(38, 135, 104, 31);
		PasswordField.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(PasswordField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 132, 206, 37);
		passwordField.setColumns(10);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 25));
		panel_1.add(passwordField);
		
		final JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
					try {
						bindToServer();
						check(LoginButton);
					} catch (IOException | InterruptedException | ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
		});
		LoginButton.setFocusPainted(false);
		LoginButton.setRequestFocusEnabled(false);
		LoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LoginButton.setBackground(new Color(0, 102, 255));
		LoginButton.setForeground(new Color(255, 255, 255));
		LoginButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		LoginButton.setBounds(141, 208, 129, 48);
		panel_1.add(LoginButton);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.setRequestFocusEnabled(false);
		RegisterButton.setFocusPainted(false);
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				RegisterForm rgr = new RegisterForm();
				rgr.setVisible(true);
				rgr.setLocationRelativeTo(null);
				firstchild.setVisible(false);
			}
		});
		RegisterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		RegisterButton.setForeground(Color.WHITE);
		RegisterButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		RegisterButton.setBackground(new Color(0, 102, 255));
		RegisterButton.setBounds(322, 208, 129, 48);
		panel_1.add(RegisterButton);
		setUndecorated(true);
	}
	public void check(JButton btn) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		String ans;
		String uname = UsernameField.getText();
		char[] a = passwordField.getPassword();
		String pass = new String(a);
		Wrapper w = new Wrapper("Student",uname, pass);
		threadedClient.sendObjectToServer(w);
		ans = threadedClient.receiveMsgFromServer();	
		if(ans.equals("Authorization Successfull"))
		{
			JOptionPane.showMessageDialog(null, "Authorization Successfull");
			setDashboard();
		}
		else	
			JOptionPane.showMessageDialog(null, "Username or password does not match!");
		threadedClient.close();
	}
}
