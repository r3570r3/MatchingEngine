package engine.backend.test;

import java.util.ArrayList;
import java.util.List;

import engine.backend.entitiy.SystemEntity;
import engine.backend.entitiy.User;
import engine.backend.queryproceessor.QueryExecutor;
import engine.backend.runtime.GenderDataset;
import engine.backend.runtime.RuntimeDataset;

public class TestAppRuntime {

	public static void main(String[] args) {
		
		RuntimeDataset parentDataset = new GenderDataset();
		
		SystemEntity a = new User("UserA", "Female", 25, new String[]{"Cricket"});
		SystemEntity b = new User("UserB", "Male", 27, new String[]{"Cricket", "Football", "Movies"});
		SystemEntity c = new User("UserC", "Male", 26, new String[]{"Movies", "Tennis", "Football", "Cricket"});
		SystemEntity d = new User("UserD", "Female", 24, new String[]{"Tennis", "Football", "Badminton"});
		SystemEntity e = new User("UserE", "Female", 32, new String[]{"Cricket", "Football", "Movies", "Badminton"});
		
		
		// passing data to gender dataset  will in turn create further data in age and interest dataset 
		parentDataset.addRuntimeEntityToDataset(a);
		parentDataset.addRuntimeEntityToDataset(b);
		parentDataset.addRuntimeEntityToDataset(c);
		parentDataset.addRuntimeEntityToDataset(d);
		parentDataset.addRuntimeEntityToDataset(e);
		
		
		
		QueryExecutor executor = QueryExecutor.getRuntimeInstance(parentDataset);
		
		
		SystemEntity queryUser = b;
		int numMatches = 2;
		
		List<SystemEntity> matchedUsers = executor.queryMatchesFor(queryUser, numMatches);
		List<String> result = new ArrayList<String>();
		
		int i = 1;
		for (SystemEntity user : matchedUsers)	{
			result.add(user.getName());
			if (i++ >= 2)	{
				break;
			}
		}
		System.out.println(result);
	}

}
