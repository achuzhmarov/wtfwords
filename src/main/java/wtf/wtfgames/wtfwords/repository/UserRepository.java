package wtf.wtfgames.wtfwords.repository;

import wtf.wtfgames.wtfwords.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByLogin(String login);
}