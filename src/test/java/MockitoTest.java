import com.jetbrains.marco.UserDto;
import com.jetbrains.marco.service.MailService;
import com.jetbrains.marco.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MockitoTest {

    UserService userService;

    MailService mailService;


    @BeforeEach
    public void setup() {
        mailService = mock(MailService.class);
        userService = new UserService(mailService);
    }

    @Test
    public void test_register_user() {
        when(mailService.sendWelcomeEmail(any(UserDto.class))).thenReturn(true);
        boolean userRegistered = userService.register(new UserDto("Marco", 99, "marco@jetbrains.com"));
        assertThat(userRegistered).isTrue();

        // doThrow(RuntimeException.class).when(userService).register();
    }
}
