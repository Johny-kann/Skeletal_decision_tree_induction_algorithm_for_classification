package com.data_mining.view.console.decision_tree;

import com.data_mining.constants.NodeConstants;
import com.data_mining.constants.Notations;
import com.data_mining.exceptions.LeafNodeException;
import com.data_mining.model.errors.Accuracy;
import com.data_mining.model.nodes.TreeNodes;

public class TreeBuilder {

	private TreeNodes rootNode;
	private Accuracy accuracy;
	
	public TreeBuilder(TreeNodes node)
	{
		rootNode = node;
		accuracy = new Accuracy();
	}
	
	public void printTree(TreeNodes node)
	{
		if(node.getType()==NodeConstants.LEAF_NODE)
		{
			System.out.println(getSpaces(node)+node.getNodeTitle()+" Class "+node.getMaxClass()
					+" correct "+node.getCorrectClassCount()
					+" wrong "+node.getWrongClassCount());
			accuracy.addRightClass(node.getCorrectClassCount());
			accuracy.addWrongClass(node.getWrongClassCount());
		}
		else
		{
			System.out.println(getSpaces(node)+node.getNodeTitle());
		}
	//	InputGetter.getEnter();
		if(node.getType()!=NodeConstants.LEAF_NODE)
		try {
			for(TreeNodes child:node.getChildren())
			{
				printTree(child);
				
			}
		} catch (LeafNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSpaces(TreeNodes node)
	{
		String space="";
		for(int i=1;i<node.getDecisionTreeLevel();i++)
		{
			space+="|\t";
		}
		return space;
	}
	
	public Double getAccuracy()
	{
		return accuracy.getAccuracy();
	}
}
