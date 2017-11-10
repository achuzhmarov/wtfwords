package wtf.wtfgames.wtfwords.integration.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wtf.wtfgames.wtfwords.controller.type.BaseIdRequest;
import wtf.wtfgames.wtfwords.repository.PersonalRewardRepository;
import wtf.wtfgames.wtfwords.integration.BaseIT;
import wtf.wtfgames.wtfwords.model.PersonalReward;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonalRewardControllerIT extends BaseIT {
    private static String url = "personal_reward";

    @Autowired
    PersonalRewardRepository personalRewardRepository;

    @Test
    public void checkReward_NoReward_ReturnFalse() throws Exception {
        BaseIdRequest request = new BaseIdRequest("TEST_ID");


        String result = post(url, request);


        assertThat(result, containsString("false"));
    }

    @Test
    public void checkReward_OneReward_GetReward() throws Exception {
        runInTransaction(() -> {
            PersonalReward personalReward = PersonalReward.builder().userId("TEST_ID").message("Thank you!").wtfs(50).acquired(false).build();
            personalRewardRepository.save(personalReward);
        });

        BaseIdRequest request = new BaseIdRequest("TEST_ID");


        String result = post(url, request);


        assertThat(result, containsString("true"));
        assertThat(result, containsString("Thank you!"));
        assertThat(result, containsString("50"));
    }

    @Test
    public void checkReward_MultipleRewards_GetRewardsConsequently() throws Exception {
        runInTransaction(() -> {
            PersonalReward personalReward = PersonalReward.builder().userId("TEST_ID").message("Thank you!").wtfs(50).acquired(false).build();
            PersonalReward personalReward2 = PersonalReward.builder().userId("TEST_ID").message("Thank you too!").wtfs(100).acquired(false).build();
            personalRewardRepository.save(personalReward);
            personalRewardRepository.save(personalReward2);
        });

        BaseIdRequest request = new BaseIdRequest("TEST_ID");


        String result = post(url, request);
        String result2 = post(url, request);


        assertThat(result, containsString("true"));
        assertThat(result, containsString("Thank you!"));
        assertThat(result, containsString("50"));

        assertThat(result2, containsString("true"));
        assertThat(result2, containsString("Thank you too!"));
        assertThat(result2, containsString("100"));
    }

    @Test
    public void checkReward_OneReward_GetOnlyOnce() throws Exception {
        runInTransaction(() -> {
            PersonalReward personalReward = PersonalReward.builder().userId("TEST_ID").acquired(false).build();
            personalRewardRepository.save(personalReward);
        });

        BaseIdRequest request = new BaseIdRequest("TEST_ID");


        post(url, request);
        String result = post(url, request);


        assertThat(result, containsString("false"));
    }

    @Test
    public void checkReward_AquiredReward_CanNotGetIt() throws Exception {
        runInTransaction(() -> {
            PersonalReward personalReward = PersonalReward.builder().userId("TEST_ID").acquired(true).build();
            personalRewardRepository.save(personalReward);
        });

        BaseIdRequest request = new BaseIdRequest("TEST_ID");


        String result = post(url, request);


        assertThat(result, containsString("false"));
    }
}
