package com.example.Simple.taskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class TaskService {

	
	
	public List<Task>tasks=new ArrayList<>();
	private int nextId = 1;
	
	public TaskService() {
		
		// Adding tasks with 'done' status and due dates
		tasks.add(new Task(nextId++, "Awais", "Learn to dance", LocalDate.now(), false));
        tasks.add(new Task(nextId++, "Pallavi", "Learn to drive", LocalDate.now().plusDays(1), true));
        tasks.add(new Task(nextId++, "Cat", "learn to meow", LocalDate.now().plusDays(2), false));
	}
	
	public List<Task> getalltasks(){
		return tasks;
	}
		
	public void addTask(String username, String description, LocalDate targetDate, boolean done) {
        Task newTask = new Task(nextId++, username, description, targetDate, done); // Create new task with auto-incremented ID
        tasks.add(newTask); // Add to the list
	}
	
	public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id); // Removes task by id
    }
	
	
	public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null; // Or throw an exception if preferred}
	}
	public void updateTask(int id, String username, String description, LocalDate targetDate, boolean done) {
		Task task = getTaskById(id);
		if(task!=null) {
	        
	            task.setUsername(username);
	            task.setDescription(description);
	            task.setLocaldate(targetDate);
	            task.setDone(done);
	            
	        }
	    }
}
