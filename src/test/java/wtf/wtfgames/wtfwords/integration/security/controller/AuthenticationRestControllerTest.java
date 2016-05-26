package wtf.wtfgames.wtfwords.integration.security.controller;

import org.junit.Test;
import wtf.wtfgames.wtfwords.integration.BaseIntegrationTest;
import wtf.wtfgames.wtfwords.security.JwtAuthenticationRequest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuthenticationRestControllerTest extends BaseIntegrationTest {

    @Test
    public void testLogin() throws Exception {
        String message = post("login", new JwtAuthenticationRequest("tigra", "tigrik"));

        assertThat(message, containsString("token"));
    }
}
