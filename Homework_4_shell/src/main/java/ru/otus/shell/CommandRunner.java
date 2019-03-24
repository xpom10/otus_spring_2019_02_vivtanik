package ru.otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.runner.TestRunnerService;

@ShellComponent
public class CommandRunner {

    private final TestRunnerService testRunnerService;
    private boolean testIsPassed = false;

    @Autowired
    public CommandRunner(TestRunnerService testRunnerService) {
        this.testRunnerService = testRunnerService;
    }

    @ShellMethod("Run a test")
    public void test() {
        testRunnerService.test();
        testIsPassed = true;
    }

    @ShellMethod("Stop a test")
    @ShellMethodAvailability("testIsPassed")
    public void stop() {
        System.exit(0);
    }

    public Availability testIsPassed() {
        if (!testIsPassed) {
            return Availability.unavailable("test not passed");
        }
        return Availability.available();
    }
}
