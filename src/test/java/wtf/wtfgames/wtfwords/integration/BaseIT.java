package wtf.wtfgames.wtfwords.integration;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wtf.wtfgames.wtfwords.Application;
import wtf.wtfgames.wtfwords.repository.AcquiredRewardRepository;
import wtf.wtfgames.wtfwords.repository.PersonalRewardRepository;
import wtf.wtfgames.wtfwords.repository.RewardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public abstract class BaseIT {
    @Value("${local.server.port}")
    private int port;

    private final String baseUrl = "https://localhost";

    @Autowired @Qualifier("testRestTemplate")
    private RestTemplate rest;

    private String getBaseUrl() {
        return baseUrl + ":" + port + "/";
    }

    protected String post(String url, Object request) {
        String result = rest.postForObject(getBaseUrl() + url, request, String.class);
        System.out.println(result);
        return result;
    }

    @Autowired
    AcquiredRewardRepository acquiredRewardRepository;

    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    PersonalRewardRepository personalRewardRepository;

    @Before
    public void clearDataFromDatabase() {
        acquiredRewardRepository.deleteAll();
        rewardRepository.deleteAll();
        personalRewardRepository.deleteAll();
    }
}
