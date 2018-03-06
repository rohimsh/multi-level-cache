package models;

public class MultiLevelCache {

	Cache[] multiLevelCache;
	int size;
	int totalCapacity;
	
	public MultiLevelCache(int size, int diff) {
		this.size = size;
		this.totalCapacity = 0;
		this.multiLevelCache = new Cache[size];
		for(int i = 1; i <= size; i++) {
			int cacheSize = i * diff;
			multiLevelCache[i - 1] = new Cache(cacheSize);
			this.totalCapacity += cacheSize;
		}
	}

	public Cache[] getMultiLevelCache() {
		return multiLevelCache;
	}

	public void setMultiLevelCache(Cache[] multiLevleCache) {
		this.multiLevelCache = multiLevleCache;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
}
