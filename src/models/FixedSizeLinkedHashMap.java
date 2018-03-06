package models;

import java.util.LinkedHashMap;
import java.util.Map;

public class FixedSizeLinkedHashMap<K, V> extends LinkedHashMap<K, V>{

	private final int maxSize;
	
	public FixedSizeLinkedHashMap(int cacheSize) {
		this.maxSize = cacheSize;
	}
	
	@Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }
}
