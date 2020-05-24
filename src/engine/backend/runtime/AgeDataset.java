package engine.backend.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.entitiy.SystemEntity;

public class AgeDataset implements RuntimeDataset {

	// can hold age -> any runtime data mapping
	// currently  holding age -> interests
	private Map<String, RuntimeDataset> ageMap;
	
	public AgeDataset()	{
		ageMap = new HashMap<>();
	}
	
	@Override
	public void addRuntimeEntityToDataset(SystemEntity user)	{
		RuntimeDataset runtimeData;
		String age = String.valueOf(user.getAge());
		
		if (ageMap.containsKey(age))	{
			runtimeData = ageMap.get(age);
		}
		else	{
			// coupling here, extend this part
			// Age is rule 2, currently holds entry to Interests
			runtimeData = new InterestsDataset();
			ageMap.put(age, runtimeData);
		}
		runtimeData.addRuntimeEntityToDataset(user);
	}
	
	
	
	public List<RuntimeDataset> queryDataset(SystemEntity key, boolean inclusive, int nResults, List<RuntimeDataset> filteredData, List<SystemEntity> results)	{
		List<RuntimeDataset> result = new ArrayList<RuntimeDataset>();
		
		int age = Integer.parseInt(key.getAge());
		String keyAge = String.valueOf(age);
		
		if(ageMap.containsKey(keyAge))	{
			RuntimeDataset interestMatchesInHigherAge = ageMap.get(keyAge);
			filteredData.addAll(interestMatchesInHigherAge.queryDataset(key, true, nResults, filteredData, results));
		}
		
		for (int i = 0; i <= 50; i++) {
			age = Integer.parseInt(key.getAge());
			String keyHighAge = String.valueOf(age - i);
			String keyLowAge = String.valueOf(age + i);
			
			if(ageMap.containsKey(keyHighAge))	{
				RuntimeDataset interestMatchesInHigherAge = ageMap.get(keyHighAge);
				List<RuntimeDataset> resultNow = interestMatchesInHigherAge.queryDataset(key, true, nResults, filteredData, results);
				result.addAll(resultNow);
			}
				
			
			if(ageMap.containsKey(keyLowAge))	{
				RuntimeDataset interestMatchesInLowerAge = ageMap.get(keyLowAge);
				List<RuntimeDataset> resultNow = interestMatchesInLowerAge.queryDataset(key, true, nResults, filteredData,  results);
				result.addAll(resultNow);
			}
			
			// top n check
			if (results.size() >= nResults) {
				return result;
			}
		}
		
		return result;
	}
	
	
//	public String toString()	{
//		return ageMap.toString();
//	}
}
