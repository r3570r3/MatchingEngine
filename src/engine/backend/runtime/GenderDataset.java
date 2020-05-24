package engine.backend.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.entitiy.SystemEntity;

public class GenderDataset implements RuntimeDataset {
	
	// can hold gender -> any runtime data mapping
	private Map<String, RuntimeDataset> genderMap;
	
	public  GenderDataset()	{
		genderMap = new HashMap<>();
	}
	
	public void addRuntimeEntityToDataset(SystemEntity user)	{
		RuntimeDataset runtimeData;
		
		if (genderMap.containsKey(user.getGender()))	{
			runtimeData = genderMap.get(user.getGender());
		}
		else	{
			// coupling here, extend this parts
			// Gender is Rule 1, currently holds entry to age
			runtimeData = new AgeDataset();
			genderMap.put(user.getGender(), runtimeData);
		}
		runtimeData.addRuntimeEntityToDataset(user);
		//System.out.println(genderMap);
	}
	
	public List<RuntimeDataset> queryDataset(SystemEntity key, boolean inclusive, int nResults, List<RuntimeDataset> prevResult, List<SystemEntity> results)	{
		List<RuntimeDataset> result = new ArrayList<RuntimeDataset>();
		
		for (String k : genderMap.keySet())	{
			if (k.equals(key.getGender()))	{continue;}
			
			RuntimeDataset ageSet = genderMap.get(k);
			// query for interests in each age
			
			List<RuntimeDataset> resultNow = ageSet.queryDataset(key, true, nResults, prevResult,results);
			
			result.addAll(resultNow);
		}
		
		return result;
	}
	
//	public String toString()	{
//		return genderMap.toString();
//	}
}
