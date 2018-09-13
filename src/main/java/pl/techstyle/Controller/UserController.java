package pl.techstyle.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.techstyle.Service.UserService;
import pl.techstyle.model.User;

@Controller
@RequestMapping(value="/User")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping(value="/all")
	public ModelAndView getAllUser() {
		ModelAndView m = new ModelAndView();
		List<User> users = new ArrayList<>();
		users=service.getAll();
		m.addObject("users",users);
		m.addObject("delete","no");
		m.setViewName("allUsers");
		return m;
	}
	@GetMapping(value="/add")
	public ModelAndView requestAddUser() {
		ModelAndView m = new ModelAndView();
		m.addObject("user",new User());
		m.setViewName("add");
		return m;
	}
	@DeleteMapping(value="/")
	public ModelAndView deleteUser( @RequestParam(value="id", required=true) int id) {
		ModelAndView m = new ModelAndView();
		System.out.println("usuwamy");
		service.deleteUser(id);
		m.addObject("users",service.getAll());
		m.addObject("delete","yes");
		m.setViewName("allUsers");
		return m;
	}
	@PutMapping(value="/")
	public ModelAndView putUser( @RequestParam(value="id", required=true) int id) {
		ModelAndView m = new ModelAndView();
		m.addObject("user",service.getUserById(id));
		m.setViewName("edit");
		return m;
	}
	@PostMapping(value="/")
	public ModelAndView addUser( @Valid User user, BindingResult validator) {
		ModelAndView m = new ModelAndView();
		user.setActive(true);
		if(!validator.hasFieldErrors()) {
			service.addUser(user);
			m.addObject("add","yes");
			m.addObject("user",new User());
		}
		else {
			m.addObject("user",user);
			m.addObject("error","yes");
		}
		m.setViewName("add");
		return m;
		
	}
	
	@PutMapping(value="/edit")
	public ModelAndView putDataUser( @Valid User user, BindingResult validator) {
		ModelAndView m = new ModelAndView();
		user.setActive(true);
		System.out.println(user.toString());
		if(!validator.hasFieldErrors()) {
			service.update(user);
		}
		else {
		}
		m.addObject("users",service.getAll());
		m.setViewName("allUsers");
		return m;
		
	}
}
