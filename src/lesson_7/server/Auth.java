package lesson_7.server;

import java.util.ArrayList;
import java.util.List;

public class Auth implements AuthService {

    private class UserData {
        String login;
        String password;
        String nickname;

        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }


    }

    List<UserData> users;

    public Auth() {
        users = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            users.add(new UserData("user" + i, "password" + i, "name" + 1));
        }

        users.add(new UserData("qwe", "qwe", "qwe"));
        users.add(new UserData("asd", "asd", "asd"));
        users.add(new UserData("zxc", "zxc", "zxc"));
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        for (UserData user : users) {
            if (user.login.equals(login) && user.password.equals(password)) {
                return user.nickname;
            }
        }

        return null;
    }
}
