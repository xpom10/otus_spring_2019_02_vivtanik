package ru.otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.runner.TestRunnerService;

@ShellComponent
public class CommandRunner {

    private final TestRunnerService testRunnerService;

    @Autowired
    public CommandRunner(TestRunnerService testRunnerService) {
        this.testRunnerService = testRunnerService;
    }

    @ShellMethod("Run a test")
    public void test() {
        testRunnerService.test();
    }

    @ShellMethod("Stop a test")
    public void stop() {
        System.exit(0);
    }
}
