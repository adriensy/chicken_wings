package fr.esgi.demo;

import fr.esgi.demo.domain.User;
import fr.esgi.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static fr.esgi.demo.domain.Role.ADMIN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by Pro on 18/03/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User user;

    @Before
    public void setUp() {
        // Given
        user = new User();
        String login = "dsfdsf";
        String password = "sdfsdf";

        user.setLogin(login);
        user.setPassword(password);
        user.setRole(ADMIN);

        User savedUser = userRepository.save(user);
    }

    @Test
    public void Should_FindById_SavedUser() {
        // When
        User findedUser = userRepository.findOne(1L);

        // Then
        assertThat(findedUser.getLogin(), is("dsfdsf"));
    }

    @Test
    public void Should_FindByLogin_SavedUser() {
        User findedUser = userRepository.findByLogin("dsfdsf");

        // Then
        assertThat(findedUser.getId(), is(1L));
    }

    @Test
    public void Should_InsertUser_AndReturnNewId() {
        // Then
        assertThat(user, notNullValue());
    }
}
