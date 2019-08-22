package com.example.locks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.locks.examples.Lock1;

@RestController
@RequestMapping("/resource")
public class LockApi {

	@Autowired
	Lock1 lock1Example;

	@GetMapping
	public String getMessage() {
		// lock the resource here
		return lock1Example.getResource();
	}

	@GetMapping(path = "/clear")
	public String clear() {
		System.gc();
		return "garbage collector called to clear data";
	}
}
