import com.jetbrains.marco.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class AwaitilityTest {

    UserDto userDto = new UserDto("Marco", 99, "marco@jetbrains.com");

    List<UserDto> awaitilityUserDtos = new ArrayList<>(List.of(userDto));

       /*   @Test
    public void awaitility() {  // kotlin / scala / groovy DSLs available
        publishAddUserMessage();
        await().atMost(5, SECONDS).until(newUserWasAdded());

        //
        await().atMost(5, SECONDS).untilAsserted(() -> assertThat(awaitilityUsers.size()).isEqualTo(2));
    }
*/


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
