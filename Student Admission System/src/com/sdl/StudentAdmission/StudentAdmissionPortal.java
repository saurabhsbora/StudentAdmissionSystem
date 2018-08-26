package com.sdl.StudentAdmission;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentAdmissionPortal extends JFrame {

	private JPanel contentPane;
	private static ThreadedClient threadedClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		StudentAdmissionPortal frame = new StudentAdmissionPortal();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * Create the frame.
	 */
	public StudentAdmissionPortal() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 255));
		panel.setBounds(0, 0, 916, 78);
		contentPane.add(panel);
		
		JLabel StudentAdmissionPortalLabel = new JLabel("            Student Admission Portal");
		StudentAdmissionPortalLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		StudentAdmissionPortalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		StudentAdmissionPortalLabel.setForeground(Color.WHITE);
		StudentAdmissionPortalLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		StudentAdmissionPortalLabel.setBounds(12, 13, 819, 52);
		panel.add(StudentAdmissionPortalLabel);
		
		JLabel CloseLabel = new JLabel("X");
		CloseLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		CloseLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CloseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CloseLabel.setForeground(Color.WHITE);
		CloseLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		CloseLabel.setBounds(861, 17, 43, 45);
		panel.add(CloseLabel);
		
		JLabel MinimizeLabel = new JLabel("-");
		MinimizeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setState(JFrame.ICONIFIED);
			}
		});
		MinimizeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MinimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MinimizeLabel.setForeground(Color.WHITE);
		MinimizeLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
		MinimizeLabel.setBounds(819, 17, 43, 41);
		panel.add(MinimizeLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("EditorPane.border"));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 78, 916, 429);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton StudentLogin = new JButton("Student Login");
		StudentLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentLogin sl = new StudentLogin();
				sl.setVisible(true);
				sl.setLocationRelativeTo(null);
				sl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		StudentLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		StudentLogin.setFocusPainted(false);
		StudentLogin.setRequestFocusEnabled(false);
		StudentLogin.setForeground(Color.WHITE);
		StudentLogin.setFont(new Font("Calibri", Font.PLAIN, 25));
		StudentLogin.setBackground(new Color(0, 102, 255));
		StudentLogin.setBounds(81, 153, 202, 60);
		panel_1.add(StudentLogin);
		
		JButton AdminLogin = new JButton("Admin Login");
		AdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin al = new AdminLogin();
				al.setVisible(true);
				al.setLocationRelativeTo(null);
				al.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		AdminLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AdminLogin.setRequestFocusEnabled(false);
		AdminLogin.setFocusPainted(false);
		AdminLogin.setForeground(Color.WHITE);
		AdminLogin.setFont(new Font("Calibri", Font.PLAIN, 25));
		AdminLogin.setBackground(new Color(0, 102, 255));
		AdminLogin.setBounds(652, 153, 185, 60);
		panel_1.add(AdminLogin);
		
		JLabel ImgLabel = new JLabel("");
		ImgLabel.setBackground(Color.WHITE);
		ImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		ImgLabel.setIcon(new ImageIcon(img));
		ImgLabel.setBounds(0, 0, 916, 429);
		panel_1.add(ImgLabel);
		}
}
