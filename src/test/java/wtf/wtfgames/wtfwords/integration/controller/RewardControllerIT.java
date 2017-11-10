package wtf.wtfgames.wtfwords.integration.controller;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wtf.wtfgames.wtfwords.controller.type.RewardRequest;
import wtf.wtfgames.wtfwords.repository.AcquiredRewardRepository;
import wtf.wtfgames.wtfwords.repository.RewardRepository;
import wtf.wtfgames.wtfwords.integration.BaseIT;
import wtf.wtfgames.wtfwords.model.Reward;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class RewardControllerIT extends BaseIT {
    private static String url = "reward_code";

    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    AcquiredRewardRepository acquiredRewardRepository;

    @Test
    public void getReward_NoReward_ReturnFalse() throws Exception {
        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");


        String result = post(url, request);


        assertThat(result, containsString("\"hasReward\" : false"));
    }

    @Test
    public void getReward_UnlimitedReward_RewardClaimed() throws Exception {
        Reward reward = Reward.builder().message("Thank you!").code("TEST_CODE").wtfs(50).build();
        rewardRepository.save(reward);

        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");


        String result = post(url, request);


        assertThat(result, containsString("true"));
        assertThat(result, containsString("Thank you"));
        assertThat(result, containsString("50"));
    }

    @Test
    public void getReward_ClaimedSecondTime_ReturnAlreadyClaimed() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").build();
        rewardRepository.save(reward);

        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");

        post(url, request);
        String result = post(url, request);


        assertThat(result, containsString("\"alreadyClaimed\" : true"));
    }

    @Test
    public void getReward_UsesExceeded_ReturnExpired() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").usesLimit(0l).build();
        rewardRepository.save(reward);

        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");


        String result = post(url, request);


        assertThat(result, containsString("\"expired\" : true"));
    }

    @Test
    public void getReward_LastUseRemained_ReturnReward() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").wtfs(50).usesLimit(1l).build();
        rewardRepository.save(reward);

        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");


        String result = post(url, request);


        assertThat(result, containsString("50"));
    }

    @Test
    public void getReward_ManyUsesRemained_ReturnReward() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").wtfs(50).usesLimit(10000l).build();
        rewardRepository.save(reward);

        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");


        String result = post(url, request);


        assertThat(result, containsString("50"));
    }

    @Test
    public void getReward_TimeLimitExpired_ReturnExpired() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").timeLimit(LocalDate.now().minusDays(1)).build();
        rewardRepository.save(reward);

        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");


        String result = post(url, request);


        assertThat(result, containsString("\"expired\" : true"));
    }

    @Test
    public void getReward_TimeLimitForToday_ReturnReward() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").wtfs(50).timeLimit(LocalDate.now()).build();
        rewardRepository.save(reward);

        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");


        String result = post(url, request);


        assertThat(result, containsString("50"));
    }

    @Test
    public void getReward_TimeLimitForTommorow_ReturnReward() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").wtfs(50).timeLimit(LocalDate.now().plusDays(1)).build();
        rewardRepository.save(reward);

        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");


        String result = post(url, request);


        assertThat(result, containsString("50"));
    }

    @Test
    public void getReward_TimeLimitOkButNoUsesLeft_ReturnExpired() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").usesLimit(0l).timeLimit(LocalDate.now()).build();
        rewardRepository.save(reward);

        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");


        String result = post(url, request);


        assertThat(result, containsString("\"expired\" : true"));
    }

    @Test
    public void getReward_OneUseLeftButTimeLimitExpired_ReturnExpired() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").usesLimit(1l).timeLimit(LocalDate.now().minusDays(1)).build();
        rewardRepository.save(reward);

        RewardRequest request = new RewardRequest("TEST_ID", "TEST_CODE");


        String result = post(url, request);


        assertThat(result, containsString("\"expired\" : true"));
    }

    @Test
    public void getReward_TwoUsesLimit_ExpiredAfterTwoRequests() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").usesLimit(2l).build();
        rewardRepository.save(reward);

        RewardRequest request1 = new RewardRequest("TEST_ID1", "TEST_CODE");
        RewardRequest request2 = new RewardRequest("TEST_ID2", "TEST_CODE");
        RewardRequest request3 = new RewardRequest("TEST_ID3", "TEST_CODE");


        post(url, request1);
        post(url, request2);
        String result = post(url, request3);


        assertThat(result, containsString("\"expired\" : true"));
    }

    @Test
    public void getReward_TwoUsesLimit_SameUserRequestsDoNotCount() throws Exception {
        Reward reward = Reward.builder().code("TEST_CODE").usesLimit(2l).build();
        rewardRepository.save(reward);

        RewardRequest request1 = new RewardRequest("TEST_ID1", "TEST_CODE");
        RewardRequest request2 = new RewardRequest("TEST_ID2", "TEST_CODE");


        post(url, request1);
        post(url, request1);
        String result = post(url, request2);


        assertThat(result, containsString("\"hasReward\" : true"));
    }
}
