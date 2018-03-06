package service;

import exceptions.CacheLevelNotFoundException;
import exceptions.DataNotFoundInCacheException;

public interface IMultiLevelCacheService {
	
	public String read(String key) throws DataNotFoundInCacheException;
	
	public void write(String key, String value, int cacheLevel) throws CacheLevelNotFoundException;
	
	public float currentUsage();
	
	public float currentAverageReadTime();
	
	public float currentAverageWriteTime();
	

}
