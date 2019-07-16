package edu.msudenver.cs.jdnss;

import com.sun.net.httpserver.*;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.security.KeyStore;

// Source: https://stackoverflow.com/a/34483734

public class HTTPS {
    public HTTPS(final String[] parts) {
        int port = Integer.parseInt(parts[2]);
        try {
            InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(parts[1]), port);

            HttpsServer httpsServer = HttpsServer.create(address, JDNSS.jargs.backlog);
            setSSLParameters(httpsServer);
            httpsServer.createContext("/test", new MyHandler());
            httpsServer.setExecutor(null);
            httpsServer.start();
        } catch (Exception exception) {
            System.out.println("Failed to create HTTPS server on port " + port + " of localhost");
            exception.printStackTrace();
        }
    }

    private void setSSLParameters(final HttpsServer httpsServer) throws Exception {
        SSLContext context = getSslContext();
        httpsServer.setHttpsConfigurator(new HttpsConfigurator(context) {
            public void configure(HttpsParameters params) {
                try {
                    SSLEngine engine = context.createSSLEngine();
                    params.setNeedClientAuth(false);
                    params.setCipherSuites(engine.getEnabledCipherSuites());
                    params.setProtocols(engine.getEnabledProtocols());

                    SSLParameters sslParameters = context.getSupportedSSLParameters();
                    params.setSSLParameters(sslParameters);

                } catch (Exception ex) {
                    System.out.println("Failed to create SSL parameters");
                }
            }

        });
    }

    private class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            HttpsExchange httpsExchange = (HttpsExchange) t;
            t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            t.sendResponseHeaders(200, response.getBytes().length);

            try (OutputStream os = t.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    private SSLContext getSslContext() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        char[] password = JDNSS.jargs.keystorePassword.toCharArray();
        KeyStore ks = KeyStore.getInstance("JKS");

        try (FileInputStream fis = new FileInputStream(JDNSS.jargs.keystoreFile)) {
            ks.load(fis, password);
        }

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, password);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ks);

        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        return sslContext;
    }
}
