package com.lushnikova;

import com.lushnikova.config.AppConfig;
import com.lushnikova.model.enums.Status;
import com.lushnikova.repository.TaskRepository;
import com.lushnikova.model.Task;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
        TaskRepository repository = context.getBean(TaskRepository.class);

        Task task = Task.builder()
                .description("dddddd")
                .status(Status.DONE)
                .build();

        repository.save(task);



        context.close();
    }
}