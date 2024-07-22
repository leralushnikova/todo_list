package com.lushnikova.controller.web;

import com.lushnikova.model.command.TaskCommand;
import com.lushnikova.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ModelAndView getTask(ModelAndView modelAndView, @RequestParam(name = "p", defaultValue = "1") int  pageNumber) {
        modelAndView.setViewName("tasks");
        modelAndView.addObject("tasks", taskService.findAll(Pageable.ofSize( 10)).getContent());
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView showCreateForm(ModelAndView model) {
        model.addObject("task", new TaskCommand());
        model.setViewName("task_form");
        return model;
    }

    @PostMapping("/save")
    public ModelAndView savePerson(TaskCommand taskCommand) {
        if (taskCommand.getId() != null) {
            log.info("Updating task");
            taskService.update(taskCommand.getId(), taskCommand);
        } else {
            log.info("Saving task");
            taskService.save(taskCommand);
        }
        return new ModelAndView("redirect:/tasks");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id, ModelAndView modelAndView) {
        var task = taskService.findById(id);
        if (task.isPresent()) {
            modelAndView.addObject("task", task.get());
            modelAndView.setViewName("task_form");
        } else {
            modelAndView.setViewName("redirect:/tasks");
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView ModelAndView(@PathVariable("id") Long id,  ModelAndView modelAndView) {
        taskService.delete(id);
        modelAndView.setViewName("redirect:/tasks");
        return modelAndView;
    }
}
