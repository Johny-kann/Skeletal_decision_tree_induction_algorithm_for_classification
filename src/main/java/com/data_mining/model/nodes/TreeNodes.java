package com.data_mining.model.nodes;

import java.util.List;

import com.data_mining.exceptions.LeafNodeException;
import com.data_mining.exceptions.ParentNodeException;
import com.data_mining.model.attributes_records.DataTable;

public interface TreeNodes{
	
	
	
	public Double getError();
	
	public String getType();
	
	public void calculateError();
	
	public TreeNodes clone() throws CloneNotSupportedException;
	
	public DataTable getInputRecords();
	
	public void setInputRecords(DataTable inputRecords);
	
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
