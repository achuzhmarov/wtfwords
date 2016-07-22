package wtf.wtfgames.wtfwords.integration;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
public class TestAppConfig {
    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager ptm) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(ptm);
        return transactionTemplate;
    }

    @Bean
    RestTemplate testRestTemplate() throws Exception {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        HttpClient httpClient = HttpClientBuilder.create()
                .setSSLSocketFactory(connectionFactory)
                .build();

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }
}  
 