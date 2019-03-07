package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.runner.TestRunnerService;

public class Main {



    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        TestRunnerService runner = context.getBean(TestRunnerService.class);
        runner.test();
    }


}
