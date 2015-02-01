package com.data_mining.model.nodes;

import java.util.List;

import com.data_mining.exceptions.LeafNodeException;
import com.data_mining.exceptions.ParentNodeException;
import com.data_mining.logic.ChoosingAttributes;
import com.data_mining.model.attributes_records.DataTable;

public class TreeNodes{
	
private TreeNodes parentNode;
	
	private DataTable inputRecords;
	private List<TreeNodes> childNodes;
	
	private Integer nodeChosenIndex;
	private String nodeTitle;
	private String nodeSplit;
	private Double error;
	private Double errorSplit;
	private String nodeChosenAttribute;
	private String nodeChosenCondition;
	private Integer decisionTreeLevel;
	private Double cntnsValue;

	private String classSelected;
	private Integer countOfCorrectClass;
	private Integer countOfWrongClass;
	
	
	public Double getError() {
		// TODO Auto-generated method stub
		return error;
	}
	
	
	
	public void calculateError() {
		// TODO Auto-generated method stub
		ChoosingAttributes err = new ChoosingAttributes(inputRecords);
		error = err.calculateErrorForTable(inputRecords);
		assignClassAndCounts();
		
	}
	
	
	public TreeNodes clone() throws CloneNotSupportedException {
	      TreeNodes clonedCustomer = (TreeNodes) super.clone();
	 
	      // Clone the object referenced objects
	      
	      return clonedCustomer;
	   }
	
	
	
	public DataTable getInputRecords() {
		// TODO Auto-generated method stub
		return inputRecords;
	}
	
	
	public void setInputRecords(DataTable inputRecords) {
		// TODO Auto-generated method stub
		this.inputRecords = inputRecords;
		
	}
	
	public List<TreeNodes> getChildren() throws LeafNodeException;
	
	public TreeNodes getParentNode() throws ParentNodeException;
	
	public void setAllChildren(List<TreeNodes> childs) throws LeafNodeException;
	
	public String getChosenNodeAttribute();
	
	public String getChosenNodeCondition();
	
	public String getMaxClass();
	
	public Integer getDecisionTreeLevel();
	
	public void setNodeAttribute(String name);
	
	public void setNodeCondition(String cond);
	
	public void findBestAttribute() throws LeafNodeException;
	
	public void start() throws LeafNodeException;
	
	public void findNodeCondition();
	
	public Double getErrorSplit() throws LeafNodeException;
	
	public void initiateChildNodes() throws LeafNodeException;;
	
	public String getNodeTitle();
	
	public void assignClassAndCounts();
	
	public void testData(DataTable table);
	
	public void sendToChildNodes() throws LeafNodeException;
	
	public Integer getCorrectClassCount();
	
	public Integer getWrongClassCount();
	
		

}
