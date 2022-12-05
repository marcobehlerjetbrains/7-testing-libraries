import com.jetbrains.marco.UserDto;
import com.jetbrains.marco.service.MailService;
import com.jetbrains.marco.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoExtensionTest {

    @Mock
    private MailService mailService;

    @InjectMocks
    private UserService userService;

    @Test
    public void test_register_user() {
        when(mailService.sendWelcomeEmail(any(UserDto.class))).thenReturn(true);
        boolean userRegistered = userService.register(new UserDto("Marco", 99, "marco@jetbrains.com"));
        assertThat(userRegistered).isTrue();
    }
}
