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
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;

public class AdminDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField SearchTextField;
	private JTable table;
	private static ThreadedClient threadedClient;
	private JFrame adframe;
	
	
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
	}
	public void performUpdation()
	{
		int row = table.getSelectedRow();
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
		
		DefaultTableModel model = new DefaultTableModel(null, column);
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
				performUpdation();
			}
		});
		UpdateButton.setRequestFocusEnabled(false);
		UpdateButton.setForeground(Color.WHITE);
		UpdateButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		UpdateButton.setFocusPainted(false);
		UpdateButton.setBackground(new Color(0, 102, 255));
		UpdateButton.setBounds(905, 58, 163, 48);
		panel_1.add(UpdateButton);
		
		JButton DeleteButton = new JButton("Delete");
		DeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		DeleteButton.setRequestFocusEnabled(false);
		DeleteButton.setForeground(Color.WHITE);
		DeleteButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		DeleteButton.setFocusPainted(false);
		DeleteButton.setBackground(new Color(0, 102, 255));
		DeleteButton.setBounds(905, 142, 163, 48);
		panel_1.add(DeleteButton);
		
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
