package com.tutorial.springboot.firstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
	
	TodoService ts;
	

	public TodoController(TodoService ts) {
		super();
		this.ts = ts;
	}
	
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap mp) {
		String username= getLoggedUsername(mp);
		List<Todo> todos= ts.findByUsername(username);
		mp.addAttribute("todos",todos);
		return "listTodos";
	}
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap mp) {
		String username= getLoggedUsername(mp);
		Todo todo= new Todo(0,username, "", LocalDate.now().plusYears(1), false);
		mp.put("todo", todo);
		return "todo";
	}
	
//	@RequestMapping(value="add-todo", method=RequestMethod.POST)
//	public String addNewTodoPage(@RequestParam String description, ModelMap mp) {
//		String username= (String)mp.get("name");
//		ts.addTodo(username, description, LocalDate.now().plusYears(1), false);
//		return "redirect:list-todos";
//	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodoPage(ModelMap mp, @Valid Todo todo, BindingResult res) {
		if(res.hasErrors()) {
			return "todo";
		}
		String username= getLoggedUsername(mp);
		ts.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}

	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		ts.deleteById(id);
		return "redirect:list-todos";
		
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String showupdateTodo(@RequestParam int id,ModelMap mp) {
		Todo todo= ts.findById(id);
		mp.addAttribute("todo",todo);
		return "todo";
		
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodo(ModelMap mp, @Valid Todo todo, BindingResult res) {
		if(res.hasErrors()) {
			return "todo";
		}
		String username= getLoggedUsername(mp);
		todo.setUsername(username);
		ts.updateTodo(todo);
		return "redirect:list-todos";
		
	}
	private String getLoggedUsername(ModelMap mp) {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
	}
}
