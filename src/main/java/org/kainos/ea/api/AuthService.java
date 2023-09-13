package org.kainos.ea.api;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;
import org.kainos.ea.db.AuthDao;

public class AuthService {

    public AuthService(AuthDao authDao) {
        this.authDao = authDao;

    }

    private AuthDao authDao;




    public ImmutablePair<User, String> login(LoginRequest login) throws FailedLoginException {
        try {
            User user = authDao.validLogin(login);
            if (user != null) {
                return new ImmutablePair<>(user, authDao.generateToken(user.getEmail()));
            }else {
                throw new FailedLoginException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new FailedLoginException();
    }
}
