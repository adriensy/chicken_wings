package fr.esgi.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankingServiceTest {

    private BankService bankService = new BankService();
    private Account account = new Account();
    private EntityManager em;

    @Mock
    private IAuthorizationService iAuthorizationService;

    @Before
    public void setUp() {
        when(iAuthorizationService.isAuthorized(any(Account.class))).thenReturn(true);
        bankService.setAuthorizationService(iAuthorizationService);
    }

    @Test
    public void should_AddMoneyToAccount_Nominal() {
        // Given : préparer les donnéees
        account.setMoney(0);

        // When
        account = bankService.pushMoney(account, 1000);
        account = bankService.pushMoney(account, 1000);

        // Then
        assertThat(account.getMoney(), is(2000));
    }

    @Test
    public void should_RemoveMoneyToAccount() {
        // Given : préparer les donnéees
        account.setMoney(0);

        // When
        account = bankService.pushMoney(account, 2000);
        account = bankService.pushMoney(account, -1000);

        // Then
        assertThat(account.getMoney(), is(1000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ShouldNot_AddMoney_WhenAccountIsNull() {
        bankService.pushMoney(null, 1000);
    }

    @Test(expected = CreditNotAuthorizedException.class)
    public void ShouldNot_RemoveMoney_WhenAccountUnderZero() {
        // Given : préparer les donnéees
        account.setMoney(-10);

        // When
        account = bankService.pushMoney(account, -1000);
    }

    @Test(expected = NotAuthorizedException.class)
    public void ShouldNot_AddMoney_WhenAccountIsBlocked() {
        when(iAuthorizationService.isAuthorized(any(Account.class))).thenReturn(false);
        account.setBlocked(true);

        bankService.pushMoney(account, 1000);
    }
}
