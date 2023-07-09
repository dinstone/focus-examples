package focus.quickstart.api;

import java.util.concurrent.CompletableFuture;

public interface FooService {

	public String hello(String name);

	public CompletableFuture<String> async(String name);
}
