package com.blgr.blogapp.service;

import com.blgr.blogapp.domain.User;
import com.blgr.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public void setBlogEntryRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getBloggerByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

    public void saveUser(User user){userRepository.save(user);}

    public List<User> getAllBloggers(){return userRepository.findAll();}

    public void deleteUserById(Long id){userRepository.deleteUserById(id);}

    public User getUserById(Long id){ return userRepository.findById(id).get();}

    public String registerUser(User userToRegister){
        User userCheckUsername = userRepository.findByUsername(userToRegister.getUsername()); //check if username is not already taken
        User userCheckEmail = userRepository.findByEmail(userToRegister.getEmail());
        if(userCheckUsername != null)
            return "wrongUsername";
        if(userCheckEmail != null)
            return "wrongEmail";
       userToRegister.setEnabled(true);
        String encodedPassword = encoder.encode(userToRegister.getPassword());
        userToRegister.setRoles("USER");
        userToRegister.setPassword(encodedPassword);
        userRepository.save(userToRegister);

        return "ok";
    }
}
