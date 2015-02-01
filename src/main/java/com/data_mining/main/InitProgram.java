package com.data_mining.main;

import java.util.ArrayList;
import java.util.List;

import com.data_mining.controller.MainController;
import com.data_mining.logic.ErrorsAndGain;
import com.data_mining.model.nodes.RootTreeNode;
import com.data_mining.model.nodes.TreeNodes;
import com.data_mining.view.console.Outputs;
import com.data_mining.view.console.decision_tree.TreeBuilder;

public class InitProgram {

	public static void main(String[] args) {
		
		MainController mc = new MainController();
		mc.loadAttributesAndRecords();
				
		TreeNodes tn = new RootTreeNode(mc.getMainTable());
	
		System.out.println("Train Data");
		new Outputs().outPutTable(tn.getInputRecords());
			
		TreeBuilder tb = new TreeBuilder(tn);
		
		tb.printTree(tn);
		System.out.println("Accuracy "+tb.getAccuracy());
	
		TreeBuilder tb2 = new TreeBuilder(tn);
		System.out.println("Test Data");
		
		try {
			
			TreeNodes tn2 = (TreeNodes)tn.clone();
			
			mc.testData();
			tn2.testData(mc.getTestAttributes());
			new Outputs().outPutTable(tn2.getInputRecords());
			tb2.printTree(tn2);
			
			System.out.println("Accuracy "+tb2.getAccuracy());
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
