package wtf.wtfgames.wtfwords.integration;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;
import wtf.wtfgames.wtfwords.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public abstract class BaseIT {
    @Value("${local.server.port}")
    private int port;

    private final String baseUrl = "https://localhost";

    @Autowired
    private RestTemplate rest;

    @Autowired
    TransactionTemplate transactionTemplate;

    private String getBaseUrl() {
        return baseUrl + ":" + port + "/";
    }

    protected String post(String url, Object request) {
        return rest.postForObject(getBaseUrl() + url, request, String.class);
    }

    protected void runInTransaction(Runnable function) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    function.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionStatus.setRollbackOnly();
                }
            }
        });
    }
}
