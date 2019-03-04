package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.runner.TestRunnerService;
import ru.otus.services.RegisterService;

public class Main {

    private static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(Config.class);


    public static void main(String[] args) {
        TestRunnerService runner = context.getBean(TestRunnerService.class);
        runner.test();
    }


}
