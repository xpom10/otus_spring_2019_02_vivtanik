package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.runner.TestRunnerService;

public class Main {

    private static ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/spring.xml");


    public static void main(String[] args) {
        TestRunnerService runner = context.getBean(TestRunnerService.class);
        runner.test();
    }


}
