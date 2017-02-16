
package com.jdap.auction.application.model;

import java.util.Arrays;
import java.util.Comparator;

import javax.faces.model.DataModel;

public class SortFilterModel extends DataModel
{
	private DataModel model;
	
	private Integer[] rows;
	
	public SortFilterModel()
	{ // mandated by JSF spec
		setWrappedData(null);
	}
	
	public SortFilterModel(UserMBean[] names)
	{ // recommended by JSF spec
		setWrappedData(names);
	}
	
	public SortFilterModel(DataModel model)
	{
		this.model = model;
		initializeRows();
	}
	
	private UserMBean getData(int row)
	{
		int originalIndex = model.getRowIndex();
		model.setRowIndex(row);
		UserMBean thisRowData = (UserMBean) model.getRowData();
		model.setRowIndex(originalIndex);
		return thisRowData;
	}
	
	public void sortBy(final Comparator<UserMBean> dataComp)
	{
		Comparator<Integer> rowComp = new Comparator<Integer>()
		{
			public int compare(Integer r1, Integer r2)
			{
				UserMBean e1 = getData(r1);
				UserMBean e2 = getData(r2);
				return dataComp.compare(e1, e2);
			}
		};
		Arrays.sort(rows, rowComp);
	}
	
	public void setRowIndex(int rowIndex)
	{
		if (0 <= rowIndex && rowIndex < rows.length)
			model.setRowIndex(rows[rowIndex]);
		else
			model.setRowIndex(rowIndex);
	}
	
	// The following methods delegate to the decorated model
	
	public boolean isRowAvailable()
	{
		return model.isRowAvailable();
	}
	
	public int getRowCount()
	{
		return model.getRowCount();
	}
	
	public UserMBean getRowData()
	{
		return (UserMBean) model.getRowData();
	}
	
	public int getRowIndex()
	{
		return model.getRowIndex();
	}
	
	public Object getWrappedData()
	{
		return model.getWrappedData();
	}
	
	public void setWrappedData(Object data)
	{
		model.setWrappedData(data);
		initializeRows();
	}
	
	private void initializeRows()
	{
		int rowCnt = model.getRowCount();
		if (rowCnt != -1)
		{
			rows = new Integer[rowCnt];
			for (int i = 0; i < rowCnt; ++i)
				rows[i] = i;
		}
	}
}
