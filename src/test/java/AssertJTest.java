import com.jetbrains.marco.UserDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertJTest {

    UserDto userDto = new UserDto("Marco", 99, "marco@jetbrains.com");

    List<UserDto> userDtos = new ArrayList<>(List.of(userDto));


    @Test
    public void assertj() {

        assertEquals(userDto.name(), "Marco");
        assertTrue(userDto.name().startsWith("Ma"));

        assertThat(userDto.name()).doesNotContainAnyWhitespaces();

        assertThat(userDtos).hasSize(1)
                .extracting(UserDto::name)
                .doesNotContain("ocram");

        /*    */

       /*

        assertThat(user.name()).isEqualTo("Marco");
        assertThat(user.name()).doesNotContainAnyWhitespaces();
        assertThat(user.name()).startsWith("Ma");


        assertThat(users)
                .hasSize(1)
                .extracting(User::name)
                .doesNotContain("Ocram");

        assertThat(user.age()).isNotEqualTo(22);*/
    }




}

