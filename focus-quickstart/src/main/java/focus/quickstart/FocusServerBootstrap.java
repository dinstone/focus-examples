package focus.quickstart;

import java.io.IOException;

import com.dinstone.focus.server.FocusServer;
import com.dinstone.focus.server.ServerOptions;

import focus.quickstart.api.FooService;
import focus.quickstart.service.FooServiceImpl;

public class FocusServerBootstrap {

	public static void main(String[] args) {
		ServerOptions serverOptions = new ServerOptions("focus.quickstart.server").listen("localhost", 3333);
		FocusServer server = new FocusServer(serverOptions);

		// exporting service
		server.exporting(FooService.class, new FooServiceImpl());

		server.start();
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.close();
	}
}
