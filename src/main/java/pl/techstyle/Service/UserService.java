package pl.techstyle.Service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.techstyle.model.User;
import pl.techstyle.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	EntityManager manager;
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public void deleteUser(int id) {
		userRepository.delete(id);
	}
	
	public User getUserById(int id) {
		return userRepository.findOne(id);
	}
	public void update(User user ) {
	userRepository.save(user);
	}
	
}
