package fr.esgi.demo;

/**
 * Created by Pro on 17/03/2015.
 */
public class MockAuthorizationService implements IAuthorizationService {
    @Override
    public boolean isAuthorized(Account account) {
        return false;
    }
}
