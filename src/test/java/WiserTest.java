import com.jetbrains.marco.UserDto;
import com.jetbrains.marco.service.MailService;
import com.jetbrains.marco.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.smtp.helper.SimpleMessageListener;
import org.subethamail.smtp.server.SMTPServer;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WiserTest {

    private UserService userService = new UserService(new MailService());


    static Wiser wiser;

    @BeforeAll
    public static void setup() {
        wiser = Wiser.port(25);

        //start server asynchronously
        wiser.start();
    }



    @AfterAll
    public static void destroy() {
        wiser.stop();
    }

    @Test
    public void test_register_user() {
        boolean userRegistered = userService.register(new UserDto("Marco", 99, "marco@jetbrains.com"));
        assertThat(userRegistered).isTrue();

        assertThat(wiser.getMessages()).hasSize(1)
                .extracting(WiserMessage::getEnvelopeReceiver)
                .contains("marco@jetbrains.com");

    }
}
