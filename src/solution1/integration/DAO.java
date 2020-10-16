package solution1.integration;

import solution1.business.PlayerAverage;
import solution1.business.User;

import java.util.List;
import java.util.Optional;

public interface DAO {
    Optional<User> loginByName(String name);

    void postResult(int result, int playerId);

    List<PlayerAverage> getTopTen();

    void setGameTitle(String gameTitle);
}
