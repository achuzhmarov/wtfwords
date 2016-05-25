package wtf.wtfgames.wtfwords.dao.exception;

/**
 * Created by Artem on 5/25/2016.
 */
public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String login) {
        super(String.format("No user found with username '%s'.", login));
    }
}
