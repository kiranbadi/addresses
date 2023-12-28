package com.vasanti.web.addresses.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.SslServerCustomizer;
import org.springframework.boot.web.server.Http2;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class NettyWebServerCustomizer implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

    @Value("${server.ssl.enabled}")
    private Boolean sslEnabled ;

    @Value("${server.ssl.key-store}")
    private String sslKeyStore;

    @Value("${server.ssl.key-alias}")
    private String sslAlias;

    @Value("${server.ssl.key-password}")
    private String sslKeypassword;

    @Value("${server.ssl.key-password}")
    private String sslKeyStorepassword;

    @Value("${server.ssl.trust-store}")
    private String sslTrustStore;

    @Value("${server.ssl.trust-store-password}")
    private String sslTrustStorepassword;

    @Value("${server.port}")
    private int sslPort;




    @Override
    public void customize(NettyReactiveWebServerFactory factory) {
        Ssl ssl = new Ssl();
        ssl.setEnabled(sslEnabled);
        ssl.setKeyStore(sslKeyStore);
        ssl.setKeyAlias(sslAlias);
        ssl.setKeyStorePassword(sslKeyStorepassword);
        Http2 http2 = new Http2();
        http2.setEnabled(false);
        factory.setHttp2(http2);
        factory.setPort(sslPort);

    }
}
