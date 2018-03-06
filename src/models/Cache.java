package models;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {

	private Map<String, String> store;
	private int cacheSize;
	private ReadWriteLock readWriteLock;
	
	Cache(int cacheSize){
		this.cacheSize = cacheSize;
		this.store = new FixedSizeLinkedHashMap<String, String>(cacheSize);
		this.readWriteLock = new ReentrantReadWriteLock();
	}
	
	public boolean containsKey(String key) {
		Lock readLock = readWriteLock.readLock();
		boolean found = false;
		try {
			readLock.lock();
			found = this.store.containsKey(key);
		} finally {
			readLock.unlock();
		}
		return found;
	}
	
	public String read(String key) {
		Lock readLock = readWriteLock.readLock();
		String value = null;
		try {
			readLock.lock();
			value = this.store.get(key);
		} finally {
			readLock.unlock();
		}
		return value;
	}
	
	public String write(String key, String value) {
		Lock writeLock = readWriteLock.writeLock();
		String valueCached = null;
		try {
			writeLock.lock();
			valueCached = this.store.put(key, value);
		} finally {
			writeLock.unlock();
		}
		return valueCached;
	}	

	public Map<String, String> getStore() {
		return store;
	}

	public void setStore(Map<String, String> store) {
		this.store = store;
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

}
