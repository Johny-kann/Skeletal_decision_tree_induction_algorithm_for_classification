package com.data_mining.exceptions;

import com.data_mining.model.nodes.LeafNode;

public class LeafNodeException extends Exception {

	public LeafNodeException()
	{
	 
	
	}
	
	public String getInfo()
	{
		return "It is a Leaf Node";
	}
}
