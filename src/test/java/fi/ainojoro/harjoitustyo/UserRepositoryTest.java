package fi.ainojoro.harjoitustyo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import fi.ainojoro.harjoitustyo.domain.User;
import fi.ainojoro.harjoitustyo.domain.UserRepository;

@DataJpaTest
@ActiveProfiles ("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void findbyUsernameFindsUser(){
        User user =  userRepository.findByUsername("maijam");

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("Maija Meikäläinen");

    }

    @Test
    public void findByUsernameWhenUserNotFound(){
        User user = userRepository.findByUsername("kuka");

        assertThat(user).isNull();
    }

}
