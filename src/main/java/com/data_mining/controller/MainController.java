package com.data_mining.controller;

import com.data_mining.constants.FilesList;
import com.data_mining.logic.AttributeAndRecordLoaders;
import com.data_mining.model.attributes_records.DataTable;

public class MainController {

	DataTable mainAttributes;
	DataTable testAttributes;
	
	public MainController()
	{
		mainAttributes = new DataTable();
		testAttributes = new DataTable();
	}
	
	public void loadAttributesAndRecords()
	{
		
		AttributeAndRecordLoaders.loadAttributeFromFile(mainAttributes, FilesList.ATTRIBUTES_FILES, FilesList.ATTRIBUTES_FILES);
		
		AttributeAndRecordLoaders.loadRecordsFromFile(mainAttributes, FilesList.RECORD_FILES);
	}
	
	public void testData()
	{
		AttributeAndRecordLoaders.loadAttributeFromFile(testAttributes, FilesList.ATTRIBUTES_FILES, FilesList.ATTRIBUTES_FILES);
		
		AttributeAndRecordLoaders.loadRecordsFromFile(testAttributes, FilesList.TEST_RECORD_FILES);
		
	}
	
	
	public void output()
	{
		System.out.println(mainAttributes.getClassName());
		System.out.println(mainAttributes.sizeOfRecords());
		System.out.println(mainAttributes.numberOfAttributes());
	}
	
	public DataTable getMainTable()
	{
		return mainAttributes;
	}
	
	public void loadAttributes()
	{
		
	}
	public void loadRecords()
	{
		
	}

	public DataTable getTestAttributes() {
		return testAttributes;
	}
	
	
}
