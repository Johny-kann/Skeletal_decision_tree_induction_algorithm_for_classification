package com.data_mining.model.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.loader.OuterJoinableAssociation;

import com.data_mining.constants.NodeConstants;
import com.data_mining.constants.Notations;
import com.data_mining.exceptions.LeafNodeException;
import com.data_mining.logic.ChoosingAttributes;
import com.data_mining.logic.CommonLogics;
import com.data_mining.logic.SearchingLogics;
import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.model.errors.PassingAttribute;
import com.data_mining.view.console.Outputs;

/**
 * @author Janakiraman
 * 
 * Internal node model
 *
 */
public class InternalNode implements TreeNodes , Cloneable{

	private TreeNodes parentNode;
	
	private DataTable inputRecords;
	private List<TreeNodes> childNodes;
	
	private String nodeType;
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
	
	public InternalNode(DataTable input,String title,int level) 
	{
//		System.out.println("Internal node "+title+" "+level);
//		InputGetter.getEnter();
		;
		nodeType = NodeConstants.INTERNAL_NODE;
		inputRecords = input;
		childNodes = new ArrayList<TreeNodes>();
		nodeTitle = title;
		decisionTreeLevel = level;
		
	
		
		
		try {
			start();
		} catch (LeafNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public TreeNodes clone() throws CloneNotSupportedException {
	      TreeNodes clonedCustomer = (TreeNodes) super.clone();
	 
	      // Clone the object referenced objects
	      
	      return clonedCustomer;
	   }
	
	@Override
	public void testData(DataTable table)
	{
		SearchingLogics sl = new SearchingLogics();
		inputRecords = sl.extractDataBasedOnCondition(table, nodeTitle);
		calculateError();
		
		try {
			sendToChildNodes();
		} catch (LeafNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void sendToChildNodes() throws LeafNodeException {
		// TODO Auto-generated method stub
		for(TreeNodes child:childNodes)
		{
			child.testData(inputRecords);
			
		}
	}
	
	public TreeNodes getParentNode()
	{
		return parentNode;
	}
	
	
	@Override
	public Double getError() {
		// TODO Auto-generated method stub
		return error;
	}

	@Override
	public void calculateError() {
		// TODO Auto-generated method stub
		ChoosingAttributes err = new ChoosingAttributes(inputRecords);
		error = err.calculateErrorForTable(inputRecords);
		assignClassAndCounts();
		
	}

	@Override
	public DataTable getInputRecords() {
		// TODO Auto-generated method stub
		return inputRecords;
	}

	@Override
	public void setInputRecords(DataTable inputRecords) {
		// TODO Auto-generated method stub
		this.inputRecords = inputRecords;
		
	}

	@Override
	public List<TreeNodes> getChildren() {
		// TODO Auto-generated method stub
		return childNodes;
	}


	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return nodeType;
	}


	@Override
	public void setAllChildren(List<TreeNodes> childs) throws LeafNodeException {
		// TODO Auto-generated method stub
		childNodes = childs;
		
	}


	@Override
	public String getChosenNodeAttribute() {
		// TODO Auto-generated method stub
		return nodeChosenAttribute;
	}


	@Override
	public String getChosenNodeCondition() {
		// TODO Auto-generated method stub
		return nodeChosenCondition;
	}


	@Override
	public void setNodeAttribute(String name) {
		// TODO Auto-generated method stub
		nodeChosenAttribute = name;
	}


	@Override
	public void setNodeCondition(String cond) {
		// TODO Auto-generated method stub
		nodeChosenCondition = cond;
	}


	@Override
	public void findBestAttribute() throws LeafNodeException {
		// TODO Auto-generated method stub
		ChoosingAttributes alg = new ChoosingAttributes(inputRecords);
		PassingAttribute pass = alg.findBestAttribute(inputRecords,error); 
		nodeChosenAttribute = pass.getAttrName();
		nodeChosenCondition = pass.getAttrCondition();
		nodeChosenIndex = pass.getAttrIndex();
		errorSplit = pass.getErrorSplit();
		
		nodeSplit = pass.getSplitMode();
//		System.out.println("Cntns " + pass.getCntnsValue());
		cntnsValue = pass.getCntnsValue();
	}


	@Override
	public void start() throws LeafNodeException {
		// TODO Auto-generated method stub
		
		calculateError();
		
		CommonLogics cl = new CommonLogics();
		if(!(cl.isleafNode(inputRecords) || inputRecords.numberOfAttributes()<1))
		{
		
		findBestAttribute();
		
		}
		
		initiateChildNodes();
		
	
		
	}


	@Override
	public void findNodeCondition() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Double getErrorSplit() throws LeafNodeException {
		// TODO Auto-generated method stub
		return errorSplit;
	}


	@Override
	public void initiateChildNodes() throws LeafNodeException {
		// TODO Auto-generated method stub
		if(nodeSplit == Notations.FULL_SPLIT)
		{
			List<String> values = new ArrayList<String>();
			
			values = inputRecords.getAttributeValues(nodeChosenAttribute);
			SearchingLogics sl = new SearchingLogics();
			CommonLogics cl = new CommonLogics();
			
			
			for(String cond:values)
			{
				DataTable table = sl.refiningSetDiscrete(inputRecords, nodeChosenIndex, cond);
	//			sl.recordRefinment(table, inputRecords, nodechosenIndex, cond);
				String condition = nodeChosenAttribute+" "+cond;
//				System.out.println("\n\nOutput before Creating child");
//				new Outputs().outPutTable(table);
				
				createChildNodes(table,condition);
			}
		}
		else if(nodeSplit == Notations.SEMI_SPLIT)
		{
			SearchingLogics sl = new SearchingLogics();
			CommonLogics cl = new CommonLogics();
			
			DataTable table1 = sl.refiningSetContinuousLeft(inputRecords,nodeChosenIndex,cntnsValue);
			DataTable table2 = sl.refiningSetContinuousRight(inputRecords,nodeChosenIndex,cntnsValue);
			
//			new Outputs().outPutTable(table1);
			createChildNodes(table1, nodeChosenAttribute+"<"+cntnsValue);
//			new Outputs().outPutTable(table2);
			createChildNodes(table2, nodeChosenAttribute+">="+cntnsValue);
			
					
		}
		
		
	}

	private void createChildNodes(DataTable table,String cond)
	{
		CommonLogics cl = new CommonLogics();
	
		if(cl.isleafNode(table) || table.numberOfAttributes()<1)
		{
			
		childNodes.add(new LeafNode(table,cond,decisionTreeLevel+1));
	
		}
	else
	{
		
		childNodes.add(new InternalNode(table,cond,decisionTreeLevel+1));
	}
	}
	@Override
	public String getNodeTitle() {
		// TODO Auto-generated method stub
		return nodeTitle;
	}


	@Override
	public Integer getDecisionTreeLevel() {
		// TODO Auto-generated method stub
		return decisionTreeLevel;
	}


	@Override
	public String getMaxClass() {
		// TODO Auto-generated method stub
		
			CommonLogics cl = new CommonLogics();
			classSelected = cl.findMaxClass(inputRecords, error);
		
		return classSelected;
	}


	@Override
	public void assignClassAndCounts() {
		
		// TODO Auto-generated method stub
				CommonLogics cl = new CommonLogics();
				Map<String,Integer> map = cl.classAndCounts(inputRecords);
				countOfCorrectClass = 0;
				countOfWrongClass = 0;
				
				Set<String> keys = map.keySet();
				
				for(String str:keys)
				{
					if(str.equals(getMaxClass()))
					{
						countOfCorrectClass = map.get(str);
					}
					else
					{
						countOfWrongClass += map.get(str);
					}
				}
				
	}
	
	@Override
	public Integer getCorrectClassCount() {
		// TODO Auto-generated method stub
		return countOfCorrectClass;
	}

	@Override
	public Integer getWrongClassCount() {
		// TODO Auto-generated method stub
		return countOfWrongClass;
	}


}
