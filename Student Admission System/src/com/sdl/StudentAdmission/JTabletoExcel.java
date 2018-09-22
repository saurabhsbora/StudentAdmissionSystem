package com.sdl.StudentAdmission;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class JTabletoExcel 
{
	private TableModel model;
	private FileWriter out;
	private BufferedWriter bw;
	
	public JTabletoExcel()
	{
		
	}
	public void exportTable(JTable table, File file) throws IOException
	{
		model = table.getModel();
		out = new FileWriter(file);
		bw = new BufferedWriter(out);
		for(int i =0; i<model.getColumnCount(); i++)
		{
			bw.write(model.getColumnName(i)+"\t");
		}
		
		bw.write("\n");
		
		for(int i =0; i < model.getRowCount();i++)
		{
			for(int j=0; j < model.getColumnCount(); j++)
			{
				bw.write(model.getValueAt(i, j).toString()+"\t");
			}
			bw.write("\n");
		}
		bw.close();
		System.out.println("File Exported Successfully");
	}
}
