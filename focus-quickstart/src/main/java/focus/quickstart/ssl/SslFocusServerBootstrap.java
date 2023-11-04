package focus.quickstart.ssl;

import java.io.IOException;
import java.security.cert.X509Certificate;

import com.dinstone.focus.server.FocusServer;
import com.dinstone.focus.server.ServerOptions;
import com.dinstone.focus.transport.photon.PhotonAcceptOptions;

import focus.quickstart.api.FooService;
import focus.quickstart.service.FooServiceImpl;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class SslFocusServerBootstrap {

	public static void main(String[] args) throws Exception {

		// setting ssl
		SelfSignedCertificate cert = new SelfSignedCertificate();
		PhotonAcceptOptions acceptOptions = new PhotonAcceptOptions();
		acceptOptions.setEnableSsl(true);
		acceptOptions.setPrivateKey(cert.key());
		acceptOptions.setCertChain(new X509Certificate[] { cert.cert() });

		// setting accept options
		ServerOptions serverOptions = new ServerOptions("focus.quickstart.server").listen("localhost", 3333)
				.setAcceptOptions(acceptOptions);

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
