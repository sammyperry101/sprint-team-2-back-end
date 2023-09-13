package org.kainos.ea.api;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.User;
import org.kainos.ea.db.AuthDao;

public class AuthService {

    private final AuthDao authDao = new AuthDao();




    public ImmutablePair<User, String> login(LoginRequest login) throws Exception {
        try {
            User user = authDao.validLogin(login);
            if (user != null) {
                return new ImmutablePair<>(user, authDao.generateToken(user.getEmail()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception();
    }
}
