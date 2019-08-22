package com.example.locks.examples;

import org.springframework.stereotype.Component;

@Component
public class Lock1 {

	String resource = "Hello I am resource you're trying to access";

	public String getResource() {
		/*- Get a new lock handler
		 *  NOTE: This is auto closable means our lock will be unlocked after success automatically
		 */
		try (MyLockHandler lockHandler = new MyLockHandler();) {
			/*
			 * Trying to set a lock on the resource so that two threads can't access at same
			 * time
			 */

			/*- HOLD ON - we don't want to lock same resource multiple times if
			 *  it is already locked so, we will use WeakHashMap to capture the 
			 *  resources that we are locking and avoid locking them again
			 */
			String value = "lock-name";
			if (lockHandler.tryLock(value)) {
				System.out.println("lock on the resource was success");
			}
			value = null;
			return resource;

		} catch (Exception e) {

			System.out.println("something went worng! Details: " + e.getMessage());

		}
		return null;
	}

}
