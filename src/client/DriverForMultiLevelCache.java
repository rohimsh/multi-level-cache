package client;

import java.util.Scanner;

import exceptions.CacheLevelNotFoundException;
import exceptions.DataNotFoundInCacheException;
import models.MultiLevelCache;
import service.MultiLevelCacheService;

public class DriverForMultiLevelCache {

	public static void main(String[] args) throws DataNotFoundInCacheException, CacheLevelNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter size of Multi Level Cache :");
		int size = sc.nextInt();
		sc.close();
		
		MultiLevelCache multiLevelCache = new MultiLevelCache(size, size);
		MultiLevelCacheService multiLevelCacheService = new MultiLevelCacheService();
		multiLevelCacheService.setMultiLevelCache(multiLevelCache);
		
		String key = "Hello";
		String value = "World";
		
//		System.out.println(multiLevelCacheService.read(key));
		multiLevelCacheService.write(key, value, 3);
		
		System.out.println(multiLevelCacheService.read(key));
		System.out.println(multiLevelCacheService.read(key));

		multiLevelCacheService.write(key, value, 4);
		multiLevelCacheService.write(key, value, 6);

		
		System.out.println("Current Usage: " + multiLevelCacheService.currentUsage());
		System.out.println("Current Average Read Time: " + multiLevelCacheService.currentAverageReadTime());
		System.out.println("Current Average Write Time: " + multiLevelCacheService.currentAverageWriteTime());
		
	}
}
