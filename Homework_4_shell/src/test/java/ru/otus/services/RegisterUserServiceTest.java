package ru.otus.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class RegisterUserServiceTest {

    @Autowired
    private RegisterService registerService;

    @Test
    public void testRegister() {
        User expectedUser = new User("Ivan", "Ivanov");
        User actualUser = registerService.registerUser("Ivan", "Ivanov");
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testFailRegister() {
        User expectedUser = new User("Vadim", "Vadimov");
        User actualUser = registerService.registerUser("Ivan", "Ivanov");
        Assert.assertNotEquals(expectedUser, actualUser);
    }

}
