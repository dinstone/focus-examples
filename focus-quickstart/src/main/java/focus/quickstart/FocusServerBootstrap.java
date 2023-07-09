package focus.quickstart;

import java.io.IOException;

import com.dinstone.focus.server.FocusServer;
import com.dinstone.focus.server.ServerOptions;
import com.dinstone.loghub.Logger;
import com.dinstone.loghub.LoggerFactory;

import focus.quickstart.api.FooService;
import focus.quickstart.service.FooServiceImpl;

public class FocusServerBootstrap {

	private static final Logger LOG = LoggerFactory.getLogger(FocusServerBootstrap.class);

	public static void main(String[] args) {
		ServerOptions serverOptions = new ServerOptions("focus.quickstart.server").listen("localhost", 3333);
		FocusServer server = new FocusServer(serverOptions);

		// exporting service
		server.exporting(FooService.class, new FooServiceImpl());

		server.start();
		LOG.info("server start");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.stop();
		LOG.info("server stop");
	}
}
