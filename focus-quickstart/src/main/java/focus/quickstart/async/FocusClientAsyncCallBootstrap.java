package focus.quickstart.async;

import java.util.concurrent.CompletableFuture;

import com.dinstone.focus.client.ClientOptions;
import com.dinstone.focus.client.FocusClient;
import com.dinstone.focus.client.ImportOptions;

import focus.quickstart.api.FooAsyncService;

public class FocusClientAsyncCallBootstrap {

	public static void main(String[] args) throws Exception {
		ClientOptions option = new ClientOptions("focus.quickstart.client").connect("localhost", 3333);
		FocusClient client = new FocusClient(option);
		try {
			ImportOptions importOptions = new ImportOptions("focus.quickstart.api.FooService");
			FooAsyncService fooService = client.importing(FooAsyncService.class, importOptions);
			CompletableFuture<String> replyFuture = fooService.hello("dinstone");
			System.out.println(replyFuture.get());
		} finally {
			client.close();
		}
	}

}
