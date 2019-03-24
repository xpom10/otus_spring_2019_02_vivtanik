package ru.otus;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.model.User;

@RunWith(SpringRunner.class)
public class UserTests {

    private User user;

    @Before
    public void initUser() {
        user = new User("Ivan", "Ivanov");
    }

    @Test
    public void checkName() {
        Assert.assertEquals("Ivan", user.getName());
    }

    @Test
    public void checkFamily() {
        Assert.assertEquals("Ivanov", user.getFamily());
    }

    @Test
    public void checkFirstCount() {
        user.increaseCountRightAnswers();
        Assert.assertEquals(1, user.getCountRightAnswers());
    }

    @Test
    public void checkSomeCount() {
        user.increaseCountRightAnswers();
        user.increaseCountRightAnswers();

        Assert.assertEquals(2, user.getCountRightAnswers());
    }
}
