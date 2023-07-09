package focus.quickstart.api;

import java.util.concurrent.CompletableFuture;

public interface FooAsyncService {
	
	public CompletableFuture<String> hello(String name);
	
}
