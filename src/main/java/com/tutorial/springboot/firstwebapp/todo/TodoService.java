package com.tutorial.springboot.firstwebapp.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {

		static List<Todo> todos= new ArrayList<>();
		static int todoCount=1;
		static {
			todos.add(new Todo(todoCount++,"udemy1","spring",LocalDate.now().plusYears(1),false));
			todos.add(new Todo(todoCount++,"udemy1","spring boot",LocalDate.now().plusYears(2),false));
			todos.add(new Todo(todoCount++,"udemy1","aws",LocalDate.now().plusYears(3),false));
		}
		
		public List<Todo> findByUsername(String username){
			Predicate<? super Todo> predicate= Todo -> Todo.getUsername().equalsIgnoreCase(username);
			return todos.stream().filter(predicate).toList();
		}
		
		public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
			Todo todo= new Todo(todoCount++,username,description,targetDate,done);
			todos.add(todo);
		}
		
		public void deleteById(int id) {
			Predicate<? super Todo> predicate= Todo -> Todo.id== id;
			todos.removeIf(predicate);
		}

		public Todo findById(int id) {
			Predicate<? super Todo> predicate= Todo -> Todo.id== id;
			Todo todo=todos.stream().filter(predicate).findFirst().get();
			return todo;
		}

		public void updateTodo(@Valid Todo todo) {
			deleteById(todo.getId());
			todos.add(todo);
		}
}
