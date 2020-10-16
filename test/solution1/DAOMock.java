package solution1;

import solution1.business.PlayerAverage;
import solution1.business.User;
import solution1.integration.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DAOMock implements DAO {
    Map<String, Integer> users;

    public DAOMock() {
        users = new HashMap<>();
        users.put("existing_user", 1);
    }

    @Override
    public Optional<User> loginByName(String name) {
        Optional<User> user = Optional.empty();
        if (users.containsKey(name)) {
            User foundUser = new User(name, users.get(name));
            user = Optional.of(foundUser);
        }
        return user;
    }

    @Override
    public void postResult(int result, int playerId) {

    }

    @Override
    public List<PlayerAverage> getTopTen() {
        return null;
    }

    @Override
    public void setGameTitle(String gameTitle) {

    }
}
