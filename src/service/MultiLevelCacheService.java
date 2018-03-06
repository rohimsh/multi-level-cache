package service;

import exceptions.CacheLevelNotFoundException;
import exceptions.DataNotFoundInCacheException;
import models.Cache;
import models.MultiLevelCache;

public class MultiLevelCacheService implements IMultiLevelCacheService{

	private MultiLevelCache multiLevelCache;
	private int readTime;
	private int writeTime;
	private int numberOfItemsRead;
	private int numberOfItemsWrite;


	@Override
	public float currentUsage() {
		int totalSizeBeingUsed = 0;
		
		for(int i = 0; i < multiLevelCache.getSize(); i++) {
			totalSizeBeingUsed += multiLevelCache.getMultiLevelCache()[i].getStore().size();
		}
		return totalSizeBeingUsed  * 100 / multiLevelCache.getTotalCapacity();
	}

	@Override
	public float currentAverageReadTime() {
		return readTime / numberOfItemsRead;
	}

	@Override
	public float currentAverageWriteTime() {
		return writeTime / numberOfItemsWrite;
	}
	
	@Override
	public String read(String key) throws DataNotFoundInCacheException {
		String value = null;
		int levelAtWhichFound = 0;
		boolean found = false;
		long startReading = System.nanoTime();
		for(int i = 0; i < multiLevelCache.getSize(); i++) {
			Cache cache = multiLevelCache.getMultiLevelCache()[i];
			if(cache.containsKey(key)) {
				value = cache.read(key);
				levelAtWhichFound = i;
				found = true;
				break;
			}
		}
		
		if(found) {
			System.out.println(key +" was found on Cache Level: " + levelAtWhichFound);//To Debug and Log
			while(levelAtWhichFound-- > 0) {
				Cache cache = multiLevelCache.getMultiLevelCache()[levelAtWhichFound];
				cache.write(key, value);
			}
		} else {
			throw new DataNotFoundInCacheException(key);
		}
		readTime += System.nanoTime() - startReading;
		numberOfItemsRead++;
		return value;
	}

	@Override
	public void write(String key, String value, int cacheLevel) throws CacheLevelNotFoundException {
		if(cacheLevel > multiLevelCache.getSize())
			throw new CacheLevelNotFoundException(cacheLevel);
		long startWriting = System.nanoTime();
		Cache cache = (multiLevelCache.getMultiLevelCache())[cacheLevel];
		cache.write(key, value);
		writeTime += System.nanoTime() - startWriting;
		numberOfItemsWrite++;
	}
	
	public MultiLevelCache getMultiLevelCache() {
		return multiLevelCache;
	}

	public void setMultiLevelCache(MultiLevelCache multiLevelCache) {
		this.multiLevelCache = multiLevelCache;
	}

	public int getReadTime() {
		return readTime;
	}

	public void setReadTime(int readTime) {
		this.readTime = readTime;
	}

	public int getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(int writeTime) {
		this.writeTime = writeTime;
	}

	public int getNumberOfItemsRead() {
		return numberOfItemsRead;
	}

	public void setNumberOfItemsRead(int numberOfItemsRead) {
		this.numberOfItemsRead = numberOfItemsRead;
	}

	public int getNumberOfItemsWrite() {
		return numberOfItemsWrite;
	}

	public void setNumberOfItemsWrite(int numberOfItemsWrite) {
		this.numberOfItemsWrite = numberOfItemsWrite;
	}


}
