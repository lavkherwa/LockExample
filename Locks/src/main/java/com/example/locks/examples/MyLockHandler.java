package com.example.locks.examples;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

@Component
public class MyLockHandler implements AutoCloseable {

	private ReentrantLock lock;
	public static WeakHashMap<String, String> lockItems = new WeakHashMap<>();

	MyLockHandler() {
		lock = new ReentrantLock();
	}

	@Override
	public void close() throws Exception {
		System.out.println("currently holding locks: " + lock.getHoldCount());
		System.out.println("lockedItems count is: " + lockItems.size());
		if (lock.getHoldCount() >= 1) {
			lock.unlock();
		}
	}

	public boolean tryLock(String value) throws InterruptedException {
		if (lockItems.containsKey(value)) {
			System.out.println("item: " + value + " is already locked");
			return false;
		}
		System.out.println("currently holding locks: " + lock.getHoldCount());
		lockItems.put(value, value);
		System.out.println("locking item: " + value);
		/* Always lock with time to avoid dead locks */
		return this.lock.tryLock(1, TimeUnit.SECONDS);
	}

	public ReentrantLock getLock() {
		return lock;
	}

	public void setLock(String value) {
		this.lock.lock();
	}

}
