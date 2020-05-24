package engine.backend.queryproceessor;

import java.util.ArrayList;
import java.util.List;

import engine.backend.entitiy.SystemEntity;
import engine.backend.runtime.RuntimeDataset;

public class QueryExecutor {
	
	private static QueryExecutor e;
	private static RuntimeDataset parentDataset;
	
	public static QueryExecutor getRuntimeInstance(RuntimeDataset dataset)	{
		if (e == null)	{
			e = new  QueryExecutor();
		}
		parentDataset = dataset;
		return e;
	}
	
	public List<SystemEntity> queryMatchesFor(SystemEntity user, int topN)	{
				
		List<SystemEntity> result = new ArrayList<>();
		List<String> resultNames = new  ArrayList<>();
		
		// dataset not prepared
		if (parentDataset == null) { return result; }

		
		// 1. Get gender of a, go to opposite gender
		// 2. Get age n of user, search for (n +/- i) in ages for interests
		// 3. Get Interest matches, and order them
		
		String gender = user.getGender();
		
		// problem statement says opposite match
		// parentDataset is a gender dataset currently
		// 1. filter by gender, get matched ages set
		List<RuntimeDataset> matchedData = parentDataset.queryDataset(user, false, topN, new ArrayList<>(), result);
		
		return result;
	}
	
}
