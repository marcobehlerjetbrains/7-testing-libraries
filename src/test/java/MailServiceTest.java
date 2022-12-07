import com.jetbrains.marco.UserDto;
import com.jetbrains.marco.service.MailService;
import com.jetbrains.marco.service.UserService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MailServiceTest {

    @Test
    public void mailTest() {
        UserService userService = new UserService(new MailService());
        boolean userRegistered = userService.register(new UserDto("Marco", 99, "marco@jetbrains.com"));
        assertThat(userRegistered).isTrue();
    }


}
