package focus.quickstart.service;

import java.util.concurrent.CompletableFuture;

import focus.quickstart.api.FooService;

public class FooServiceImpl implements FooService {

	public String hello(String name) {
		return "hello " + name;
	}

	public CompletableFuture<String> async(String name) {
		return CompletableFuture.completedFuture("async hello " + name);
	}
}
