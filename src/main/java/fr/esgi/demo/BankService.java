package fr.esgi.demo;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Pro on 16/03/2015.
 */
public class BankService {
    private IAuthorizationService authorizationService;

    public Account pushMoney(Account account, int money) {
        checkArgument(null != account);

        if (!authorizationService.isAuthorized(account)) {
            throw new NotAuthorizedException();
        }

        money += account.getMoney();

        if (money < 0) {
            throw new CreditNotAuthorizedException();
        }

        account.setMoney(money);

        return account;
    }

    public IAuthorizationService getAuthorizationService() {
        return authorizationService;
    }

    public void setAuthorizationService(IAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }
}
