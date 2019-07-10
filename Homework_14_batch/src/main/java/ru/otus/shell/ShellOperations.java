package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.UUID;

@ShellComponent
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ShellOperations {

    private final JobLauncher jobLauncher;
    private final Job migrateJob;

    @ShellMethod(value = "Migrate from Mongo to MySQL", key = "migrate")
    public void migrate() {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", UUID.randomUUID().toString())
                .toJobParameters();
        try {
            jobLauncher.run(migrateJob, params);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }


}