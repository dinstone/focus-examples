package focus.quickstart.generic;

import java.util.concurrent.CompletableFuture;

import com.dinstone.focus.client.ClientOptions;
import com.dinstone.focus.client.FocusClient;
import com.dinstone.focus.client.GenericService;

public class FocusClientGenericCallBootstrap {

	public static void main(String[] args) throws Exception {
		ClientOptions option = new ClientOptions("focus.quickstart.client").connect("localhost", 3333);
		FocusClient client = new FocusClient(option);
		try {
			GenericService genericService = client.generic("focus.quickstart.server",
					"focus.quickstart.api.FooService");

			String reply = genericService.sync(String.class, "hello", "dinstone");
			System.out.println("sync call reply : " + reply);

			CompletableFuture<String> replyFuture = genericService.async(String.class, "hello", "dinstone");
			System.out.println("async call reply : " + replyFuture.get());
		} finally {
			client.destroy();
		}
	}
}
