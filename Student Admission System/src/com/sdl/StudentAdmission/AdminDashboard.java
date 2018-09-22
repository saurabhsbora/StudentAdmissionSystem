package com.sdl.StudentAdmission;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;

public class AdminDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField SearchTextField;
	private JTable table;
	private static ThreadedClient threadedClient;
	private JFrame adframe;
	private DefaultTableModel model;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard frame = new AdminDashboard();
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
	public void loadTable(DefaultTableModel model) throws ClassNotFoundException, IOException, SQLException, InterruptedException
	{
		Wrapper wsend,wrecv;
		bindToServer();
		Vector<Student> vs = null;
		wsend = new Wrapper(vs);
		threadedClient.sendObjectToServer(wsend);
		wrecv = threadedClient.recieveObjectFromServer();
		vs = wrecv.getStudentVector();
		for(int i=0; i<vs.size();i++)
		{
			model.addRow(new Object[]{vs.get(i).getFname(), vs.get(i).getLname(), vs.get(i).getEmail_id(),vs.get(i).getDept(),vs.get(i).getEngyear(),vs.get(i).getId(),vs.get(i).getadmYear(),vs.get(i).getPhone_no()});
		}
		threadedClient.close();
	}
	public void performDeletion() throws IOException, InterruptedException
	{
		int row, modelRow, column = 5, selected_uid;
		Wrapper wsend;
		row = table.getSelectedRow();
		selected_uid = (int) table.getModel().getValueAt(row, column);
		modelRow = table.convertRowIndexToModel(row);
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.removeRow(modelRow);
		wsend = new Wrapper(selected_uid, 6);
		bindToServer();
		threadedClient.sendObjectToServer(wsend);
		threadedClient.close();
	}
	public void performUpdation() throws IOException, InterruptedException
	{
		int row, modelRow,NewadmissionYear = 0,uid;
		long NewphoneNo = 0;
		Wrapper wsend;
		row = table.getSelectedRow();
		modelRow = table.convertRowIndexToModel(row);
		uid = (int) table.getModel().getValueAt(row, 5);
		
		System.out.println(table.getModel().getValueAt(row, 5).toString());
		
		String fname = model.getValueAt(modelRow, 0).toString();
		String lname = model.getValueAt(modelRow, 1).toString();
		String email = model.getValueAt(modelRow, 2).toString();
		String dept =  model.getValueAt(modelRow, 3).toString();
		String engYear = model.getValueAt(modelRow, 4).toString();
		String admissionYear = model.getValueAt(modelRow, 6).toString();
		String phoneNo = model.getValueAt(modelRow, 7).toString();
		
		
		String Newfname = JOptionPane.showInputDialog(null,"Enter the New First Name", fname);
		String Newlname = JOptionPane.showInputDialog(null,"Enter the New Last Name", lname);
		String Newemail = JOptionPane.showInputDialog(null,"Enter the New Email", email);
		String Newdept = JOptionPane.showInputDialog(null,"Enter the New Department-CS/IT/ENTC", dept);
		String NewengYear = JOptionPane.showInputDialog(null,"Enter the New Engineering Year-FE/SE/TE/BE", engYear);
		String NewAYString = JOptionPane.showInputDialog(null,"Enter the New Admission Year", admissionYear);
		if(NewAYString != null)
			NewadmissionYear = Integer.parseInt(NewAYString);
		String NewphoneNoString = JOptionPane.showInputDialog(null,"Enter the New Phone No", phoneNo);
		if(NewphoneNoString != null)	
			NewphoneNo = Long.parseLong(NewphoneNoString);
			
		model.setValueAt(Newfname, modelRow, 0);
		model.setValueAt(Newlname, modelRow, 1);
		model.setValueAt(Newemail, modelRow, 2);
		model.setValueAt(Newdept, modelRow, 3);
		model.setValueAt(NewengYear, modelRow, 4);
		model.setValueAt(NewAYString, modelRow, 6);
		model.setValueAt(NewphoneNoString, modelRow, 7);
		
		Student s = new Student(Newfname,Newlname,Newemail,Newdept,NewengYear,NewadmissionYear,uid,NewphoneNo);
		wsend = new Wrapper(s);
		bindToServer();
		threadedClient.sendObjectToServer(wsend);
		threadedClient.close();
	}
	public AdminDashboard() throws ClassNotFoundException, IOException, SQLException, InterruptedException {
		adframe = this;
		setUndecorated(true);
		setBounds(100, 100, 1159, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				WindowDragger.panelMouseDragged(e, adframe);
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
		panel.setBounds(0, 0, 1159, 78);
		contentPane.add(panel);
		
		JLabel AdminDashboardLabel = new JLabel("Admin Dashboard");
		AdminDashboardLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AdminDashboardLabel.setForeground(Color.WHITE);
		AdminDashboardLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
		AdminDashboardLabel.setBounds(0, 0, 322, 78);
		panel.add(AdminDashboardLabel);
		
		JLabel CloseLabel = new JLabel("X");
		CloseLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int a=JOptionPane.showConfirmDialog(adframe,"Want to Logout?");
				if(a==JOptionPane.YES_OPTION)
				{ 
					dispose();
					StudentAdmissionPortal.parent.setVisible(true);
				}
			}
		});
		CloseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CloseLabel.setForeground(Color.WHITE);
		CloseLabel.setFont(new Font("Calibri", Font.PLAIN, 45));
		CloseLabel.setBounds(1082, 0, 77, 78);
		panel.add(CloseLabel);
		
		JLabel MinimizeLabel = new JLabel("-");
		MinimizeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		MinimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MinimizeLabel.setForeground(Color.WHITE);
		MinimizeLabel.setFont(new Font("Calibri", Font.PLAIN, 45));
		MinimizeLabel.setBounds(1017, 2, 77, 76);
		panel.add(MinimizeLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 78, 1159, 610);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel SearchLabel = new JLabel("Search");
		SearchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SearchLabel.setBounds(12, 26, 130, 37);
		SearchLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
		panel_1.add(SearchLabel);
		
		SearchTextField = new JTextField();
		SearchTextField.setBounds(154, 26, 460, 37);
		SearchTextField.setHorizontalAlignment(SwingConstants.LEFT);
		SearchTextField.setFont(new Font("Calibri", Font.PLAIN, 25));
		SearchTextField.setColumns(10);
		panel_1.add(SearchTextField);
		
		String column[] = {"FirstName","LastName","Email-id","Dept","EngYear","UID","AdmissionYear","PhoneNo"}; 
		
		model = new DefaultTableModel(null, column);
		loadTable(model);
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setRowMargin(2);
		table.setRowHeight(34);
		table.setFont(new Font("Calibri", Font.PLAIN, 20));
		final TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		
		
		Font bigFont = new Font("Calibri", Font.PLAIN, 20); 
		JTableHeader header = table.getTableHeader();
		header.setFont(bigFont);
		
		
		JScrollPane ScrollPane = new JScrollPane();
		ScrollPane.setBounds(0, 266, 1159, 344);
		panel_1.add(ScrollPane);
		ScrollPane.setViewportView(table);
		
		JButton UpdateButton = new JButton("Update");
		UpdateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					performUpdation();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		UpdateButton.setRequestFocusEnabled(false);
		UpdateButton.setForeground(Color.WHITE);
		UpdateButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		UpdateButton.setFocusPainted(false);
		UpdateButton.setBackground(new Color(0, 102, 255));
		UpdateButton.setBounds(905, 20, 163, 48);
		panel_1.add(UpdateButton);
		
		JButton DeleteButton = new JButton("Delete");
		DeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					performDeletion();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		DeleteButton.setRequestFocusEnabled(false);
		DeleteButton.setForeground(Color.WHITE);
		DeleteButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		DeleteButton.setFocusPainted(false);
		DeleteButton.setBackground(new Color(0, 102, 255));
		DeleteButton.setBounds(905, 105, 163, 48);
		panel_1.add(DeleteButton);
		
		JButton ExportButton = new JButton("Export");
		ExportButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try 
				{
					JTabletoExcel jtoe = new JTabletoExcel();
					jtoe.exportTable(table, new File ("C:/Users/Saurabh/Desktop/StudentRecords.xls"));
					JOptionPane.showMessageDialog(null, "File exported to location : C:/Users/Saurabh/Desktop/StudentRecords.xls");
				} 
				catch (IOException e) 
				{	
					e.printStackTrace();
				}
			}
		});
		ExportButton.setRequestFocusEnabled(false);
		ExportButton.setForeground(Color.WHITE);
		ExportButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		ExportButton.setFocusPainted(false);
		ExportButton.setBackground(new Color(0, 102, 255));
		ExportButton.setBounds(905, 188, 163, 48);
		panel_1.add(ExportButton);
		
		SearchTextField.getDocument().addDocumentListener(new DocumentListener(){
			@Override
            public void insertUpdate(DocumentEvent e) {
                String text = SearchTextField.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
			 @Override
	            public void removeUpdate(DocumentEvent e) {
	                String text = SearchTextField.getText();

	                if (text.trim().length() == 0) {
	                    rowSorter.setRowFilter(null);
	                } else {
	                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	                }
	            }
			 @Override
	            public void changedUpdate(DocumentEvent e) {
	                throw new UnsupportedOperationException("Not supported yet.");
	            }

	        });
	}
}
