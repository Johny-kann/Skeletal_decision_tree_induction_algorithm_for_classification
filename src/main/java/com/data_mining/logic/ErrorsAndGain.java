package com.data_mining.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ErrorsAndGain {

	public double roundOff(double x, int position)
    {
        double a = x;
        double temp = Math.pow(10.0, position);
        a *= temp;
        a = Math.round(a);
        return (a / (double)temp);
    }

	
	public Double classificationError(Map<String,Integer> categories)
	{
		
		List<Integer> list = mapToList(categories);
		Integer max = Collections.max(list);
		Integer tot = listToTotal(list);
		
		double error =  1 - (double) max/tot;
		return roundOff(error, 8);
	}
	
	public Double giniError(Map<String,Integer> categories)
	{
		
		List<Integer> list = mapToList(categories);
		Integer tot = listToTotal(list);
		
		Double gini = 1.0;
		
		for(Integer i : list)
		{
			gini -= (double)i/tot*(double)i/tot;
		}
		
//		double error =  1 - (double) max/tot;
		return roundOff(gini, 8);
	}
	
	public List<Integer> mapToList(Map<String,Integer> categor)
	{
		Set<String> keys = categor.keySet();
		List<Integer> classes = new ArrayList<Integer>();
		
		for(String key:keys)
		{
			classes.add(categor.get(key));
		}
		
		return classes;
	}
	
	public Integer listToTotal(List<Integer> nums)
	{
		Integer sum = 0;
		
		for(Integer num:nums)
		{
			sum+=num;
		}
		
		return sum;
	}
	
	
	public Double errorSplit(List<Double> errors,List<Integer> records,Integer totalParentRecords)
	{
		Double error = 0.0;
		Double numRec = 0.0;
		Double totRec = 0.0;
		
		for(int i=0;i<errors.size();i++)
		{
			numRec = (double)records.get(i);
//			System.out.println(numRec);
			totRec = (double)totalParentRecords;
//			System.out.println(totRec);
			error += numRec/totalParentRecords*errors.get(i);
//			System.out.println("Error" + error);
		}
		
	
		return roundOff(error, 8);
	}
	
	public Double gainRatio(List<Integer> records,Integer totalParentRecords,Double pError,Double errorSplit)
	{
		Double gain = pError - errorSplit;
		return gain/splitInfo(records,totalParentRecords);
		
	}
	
	public Double splitInfo(List<Integer> records,Integer totalParentRecords)
	{
		Double error = 0.0;
		Double numRec = 0.0;
		Double totRec = 0.0;
		
		for(int i=0;i<records.size();i++)
		{
		 error -= ((double) records.get(i)/totalParentRecords) * Math.log(
				 (double) records.get(i)/totalParentRecords)/
				 Math.log(2);
		}
		
		return roundOff(error, 8);
	}
	
	public Double gainCalculator(Double errorParent,Double errorSplit)
	{
		return errorParent - errorSplit;
	}
	
	public Double gainCalculator(double errorParent,Integer parentRecords,List<Integer> listChildRecordsNum,List<Double> listChildErrors)
	{
		double errorSplit = 0;
		for(int i = 0; i<listChildRecordsNum.size();i++)
		{
			errorSplit += listChildErrors.get(i)*listChildRecordsNum.get(i);
		}
		
//		System.out.println(errorSplit/parentRecords);
		return roundOff(errorParent - errorSplit/parentRecords,4);
	}
}
