package focus.quickstart.ssl;

import java.util.concurrent.CompletableFuture;

import com.dinstone.focus.client.ClientOptions;
import com.dinstone.focus.client.FocusClient;
import com.dinstone.focus.transport.photon.PhotonConnectOptions;

import focus.quickstart.api.FooService;

public class SslFocusClientBootstrap {

	public static void main(String[] args) throws Exception {
		PhotonConnectOptions connectOptions = new PhotonConnectOptions();
		// setting ssl
		connectOptions.setEnableSsl(true);
		ClientOptions clientOptions = new ClientOptions("focus.quickstart.client").connect("localhost", 3333)
				.setConnectOptions(connectOptions);
		FocusClient client = new FocusClient(clientOptions);
		try {
			FooService fooService = client.importing(FooService.class);
			String reply = fooService.hello("dinstone");
			System.out.println(reply);

			CompletableFuture<String> rf = fooService.async("dinstone");
			System.out.println(rf.get());
		} finally {
			client.close();
		}
	}

}
