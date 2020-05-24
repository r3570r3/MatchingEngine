package engine.backend.runtime;

import java.util.List;

import engine.backend.entitiy.SystemEntity;

public interface RuntimeDataset {
	void addRuntimeEntityToDataset(SystemEntity u);
	public List<RuntimeDataset> queryDataset(SystemEntity key, boolean includeExclude, int nResults, List<RuntimeDataset> filteredData, List<SystemEntity> result);
}