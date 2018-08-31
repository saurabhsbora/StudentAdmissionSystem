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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.JSeparator;

public class StudentDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
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
	
	public StudentDashboard() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1181, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 255));
		panel.setBounds(0, 0, 1181, 78);
		contentPane.add(panel);
		
		JLabel TitleLabel = new JLabel("Welcome ");
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setForeground(Color.WHITE);
		TitleLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		TitleLabel.setBounds(0, 0, 322, 78);
		panel.add(TitleLabel);
		
		JLabel CloseLabel = new JLabel("X");
		CloseLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
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
		FeePanel.setLayout(null);
		
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
		
		JLabel label = new JLabel("");
		label.setHorizontalTextPosition(SwingConstants.LEFT);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Calibri", Font.PLAIN, 25));
		label.setBounds(499, 13, 308, 31);
		ProfilePanel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalTextPosition(SwingConstants.LEFT);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_1.setBounds(499, 76, 308, 31);
		ProfilePanel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setHorizontalTextPosition(SwingConstants.LEFT);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_2.setBounds(499, 139, 308, 31);
		ProfilePanel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setHorizontalTextPosition(SwingConstants.LEFT);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_3.setBounds(499, 202, 308, 31);
		ProfilePanel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setHorizontalTextPosition(SwingConstants.LEFT);
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_4.setBounds(499, 265, 308, 31);
		ProfilePanel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setHorizontalTextPosition(SwingConstants.LEFT);
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_5.setBounds(499, 328, 308, 31);
		ProfilePanel.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setHorizontalTextPosition(SwingConstants.LEFT);
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_6.setBounds(499, 391, 308, 31);
		ProfilePanel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setHorizontalTextPosition(SwingConstants.LEFT);
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_7.setBounds(499, 454, 308, 31);
		ProfilePanel.add(label_7);
		
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
		
		JLabel lblAdmissionStatus = new JLabel("Admission Status");
		lblAdmissionStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmissionStatus.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblAdmissionStatus.setBounds(902, 188, 200, 55);
		ProfilePanel.add(lblAdmissionStatus);
		
		JLabel label_8 = new JLabel("");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Calibri", Font.PLAIN, 28));
		label_8.setBounds(902, 288, 200, 55);
		ProfilePanel.add(label_8);
		tabbedPane.addTab("<html><body><table width='370'>Fee Report</table></body></html>",FeePanel);
		tabbedPane.addTab("<html><body><table width='370'>Update Details</table></body></html>",UpdatePanel);
	}
}
