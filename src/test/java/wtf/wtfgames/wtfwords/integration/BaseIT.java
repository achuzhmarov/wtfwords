package wtf.wtfgames.wtfwords.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;
import wtf.wtfgames.wtfwords.Application;
import wtf.wtfgames.wtfwords.dao.AcquiredRewardDao;
import wtf.wtfgames.wtfwords.dao.PersonalRewardDao;
import wtf.wtfgames.wtfwords.dao.RewardDao;
import wtf.wtfgames.wtfwords.model.Reward;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public abstract class BaseIT {
    @Value("${local.server.port}")
    private int port;

    private final String baseUrl = "https://localhost";

    @Autowired @Qualifier("testRestTemplate")
    private RestTemplate rest;

    @Autowired
    TransactionTemplate transactionTemplate;

    private String getBaseUrl() {
        return baseUrl + ":" + port + "/";
    }

    protected String post(String url, Object request) {
        String result = rest.postForObject(getBaseUrl() + url, request, String.class);
        System.out.println(result);
        return result;
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

    @Autowired
    AcquiredRewardDao acquiredRewardDao;

    @Autowired
    RewardDao rewardDao;

    @Autowired
    PersonalRewardDao personalRewardDao;

    @Before
    public void clearDataFromDatabase() {
        runInTransaction(() -> {
            acquiredRewardDao.clear();
            rewardDao.clear();
            personalRewardDao.clear();
        });
    }
}
