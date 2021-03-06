package engine.backend.runtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.backend.entitiy.SystemEntity;

public class InterestsDataset implements RuntimeDataset {
	
	// can hold interest -> any runtime data mapping
	private Map<String, RuntimeDataset> interetsMap;
	private List<SystemEntity> users;
	private Map<SystemEntity,List<String>> userInterestMap;
	
	
	public InterestsDataset()	{
		interetsMap = new HashMap<>();
		userInterestMap = new HashMap<>();
		users = new ArrayList<>();
	}
	
	@Override
	public void addRuntimeEntityToDataset(SystemEntity user)	{
		userInterestMap.put(user, Arrays.asList(user.getInterests()));
		users.add(user);
	}

	
	@Override
	public List<RuntimeDataset> queryDataset(SystemEntity key, boolean includeExclude, int nResults, List<RuntimeDataset> filteredData, List<SystemEntity> results) {
		List<RuntimeDataset> result = new ArrayList<RuntimeDataset>();
		
		// for each interest of user key, search for matches and return
		for (String interest : key.getInterests()) {
			
			// can be optimized a lot
			for (SystemEntity mapKey : userInterestMap.keySet())	{
				List<String> interestList = userInterestMap.get(mapKey);
				if (interestList.contains(interest) && !results.contains(mapKey)) {
					results.add(mapKey);
				}
			}
			
			if (results.size() >= nResults) {
				break;
			}
		}
		
		return result;
	}
}
