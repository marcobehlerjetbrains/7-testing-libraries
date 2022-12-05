import com.jetbrains.marco.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyFirstTest {

    User user = new User("Marco", 99);

    List<User> users = new ArrayList<>(List.of(user));

    List<User> awaitilityUsers = new ArrayList<>(List.of(user));

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
    private void publishAddUserMessage() {
        new Thread(() -> {
            try {
                Thread.sleep(4000);
                awaitilityUsers.add(new User("Lisa", 99));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private Callable<Boolean> newUserWasAdded() {
        return () -> awaitilityUsers.size() == 2;
    }

}

