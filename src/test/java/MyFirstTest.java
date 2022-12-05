import com.github.marschall.memoryfilesystem.MemoryFileSystemBuilder;
import com.jetbrains.marco.UserDto;
import com.jetbrains.marco.service.MailService;
import com.jetbrains.marco.service.UserService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyFirstTest {

    UserDto userDto = new UserDto("Marco", 99, "marco@jetbrains.com");

    List<UserDto> userDtos = new ArrayList<>(List.of(userDto));

    List<UserDto> awaitilityUserDtos = new ArrayList<>(List.of(userDto));

    @Test
    public void assertj() {
       /* assertEquals(user.name(), "Marco");
        assertEquals(user.age(), 99);

        assertThat(user.name()).isEqualTo("Marco");
        assertThat(user.name()).doesNotContainAnyWhitespaces();
        assertThat(user.name()).startsWith("Ma");


        assertThat(users)
                .hasSize(1)
                .extracting(User::name)
                .doesNotContain("Ocram");

        assertThat(user.age()).isNotEqualTo(22);*/
    }


 /*   @Test
    public void awaitility() {  // kotlin / scala / groovy DSLs available
        publishAddUserMessage();
        await().atMost(5, SECONDS).until(newUserWasAdded());

        //
      //  await().atMost(5, SECONDS).untilAsserted(() -> assertThat(awaitilityUsers.size()).isEqualTo(2));
    }
*/

    @Test
    public void mailTest() {
        UserService userService = new UserService(new MailService());
        boolean userRegistered = userService.register(new UserDto("Marco", 99, "marco@jetbrains.com"));
        assertThat(userRegistered).isTrue();
    }


    @Test
    public void fileSystemTest() throws IOException {
        try (FileSystem fileSystem = MemoryFileSystemBuilder.newEmpty().build()) {
            Path txt = fileSystem.getPath("test.txt");
            Files.write(txt, "Hello World".getBytes());

            assertThat(Files.exists(txt)).isTrue();
            assertThat(Files.readString(txt)).isEqualTo("Hello World");
        }
    }

    private void publishAddUserMessage() {
        new Thread(() -> {
            try {
                Thread.sleep(4000);
                awaitilityUserDtos.add(new UserDto("Lisa", 99, "lisa@jetbrains.com"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }



    private Callable<Boolean> newUserWasAdded() {
        return () -> awaitilityUserDtos.size() == 2;
    }

}

