package focus.quickstart;

import java.util.concurrent.CompletableFuture;

import com.dinstone.focus.client.ClientOptions;
import com.dinstone.focus.client.FocusClient;

import focus.quickstart.api.FooService;

public class FocusClientBootstrap {

	public static void main(String[] args) throws Exception {
		ClientOptions option = new ClientOptions("focus.quickstart.client").connect("localhost", 3333);
		FocusClient client = new FocusClient(option);
		try {
			FooService fooService = client.importing(FooService.class);
			String reply = fooService.hello("dinstone");
			System.out.println(reply);

			CompletableFuture<String> rf = fooService.async("dinstone");
			System.out.println(rf.get());
		} finally {
			client.destroy();
		}
	}

}
