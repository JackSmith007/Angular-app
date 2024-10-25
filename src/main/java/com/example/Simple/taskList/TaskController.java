/*
 * package com.example.Simple.taskList;
 * 
 * import java.time.LocalDate;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.validation.BindingResult; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.ResponseBody;
 * 
 * import jakarta.validation.Valid;
 * 
 * @Controller public class TaskController {
 * 
 * 
 * @Autowired private TaskService taskservice;
 * 
 * public TaskController(TaskService taskservice) { this.taskservice =
 * taskservice;
 * 
 * 
 * }
 * 
 * 
 * @GetMapping("/tasks") public String gettasklist(Model model) {
 * model.addAttribute("ready", taskservice.getalltasks()); return "tasks"; //
 * Thymeleaf template 'tasklist.html' should be in /resources/templates }
 * 
 * @GetMapping("/addtask") public String showAddTaskForm(Model model) {
 * model.addAttribute("add",new Task(0, "", "", LocalDate.now(), false)); //
 * Adding an empty Task object to bind the form return "addnewtasks"; // This
 * maps to add-task.html in the templates directory }
 * 
 * @PostMapping("/addtask") public String addTasks(@Valid @ModelAttribute("add")
 * Task task, BindingResult result) { if (result.hasErrors()) { // Log the
 * errors for debugging System.out.println("Validation Errors: " +
 * result.getAllErrors()); return "addnewtasks"; // Return to the form if there
 * are errors }
 * 
 * taskservice.addTask(task.getUsername(), task.getDescription(),
 * task.getLocaldate(), task.isDone()); // Call the service to add a new task
 * return "redirect:/tasks"; // Redirect to the task list view after adding }
 * 
 * @GetMapping("/deletetask") public String deleteTask(@RequestParam int id) {
 * taskservice.deleteTask(id); return "redirect:/tasks"; }
 * 
 * @GetMapping("/updatetask") public String showUpdateTaskForm(@RequestParam int
 * id, Model model) { Task taskToUpdate = taskservice.getTaskById(id);
 * model.addAttribute("task", taskToUpdate); return "update"; // This maps to
 * update-task.html
 * 
 * }
 * 
 * @PostMapping("/updatetask") public String
 * updateTask(@Valid @ModelAttribute("task") Task task, BindingResult result) {
 * taskservice.updateTask(task.getId(), task.getUsername(),
 * task.getDescription(), task.getLocaldate(), task.isDone()); return
 * "redirect:/tasks";
 * 
 * }}
 */