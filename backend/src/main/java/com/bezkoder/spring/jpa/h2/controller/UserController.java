package com.bezkoder.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;  

import com.bezkoder.spring.jpa.h2.model.User;
import com.bezkoder.spring.jpa.h2.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
// @RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    //   @GetMapping("/users")
    //   public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String id) {
    //     try {
    //       List<User> users = new ArrayList<User>();

    //       if (id == null)
    //         userRepository.findAll().forEach(users::add);
    //     //   else
    //     //     userRepository.findByTitleContainingIgnoreCase(id).forEach(users::add);

    //       if (users.isEmpty()) {
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //       }

    //       return new ResponseEntity<>(users, HttpStatus.OK);
    //     } catch (Exception e) {
    //       return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    //   }

    // Get User Profile 
    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        System.out.println("id --" + id);
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //   User Register
    @PostMapping("/api/user")
    public String createUser(@RequestBody User user) {
        try {
            List<User> existList = userRepository.findByEmail(user.getEmail());
            if (existList.size() == 0) {
                userRepository.save(new User(user.getName(), user.getSurname(), user.getBirthday(),
                        user.getEmail(), user.getPassword(), user.getMemberType()));
                return "REGISTER_SUCCESS";
            } else {
                return "EXIST";
            }

        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString();
        }
    }

    //   User Login
    @PostMapping("/api/user/login")
    public String loginUser(@RequestParam String email, @RequestParam String password) {
        try {
            List<User> existList = userRepository.findByEmail(email);
            if (existList.size() != 0) {
                if (existList.get(0).getPassword().equals(password)) {
                    String member = !existList.get(0).getMemberType() ? "client" : "owner";
                    long userId = existList.get(0).getId();
                    return "LOGIN_SUCCESS&" + member + "&" + userId;
                } else {
                    return "PASSWORD_INCORRECT";
                }
                
            } else {
                return "NOT_EXIST";
            }
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString();
        }
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
          User _user = userData.get();
          _user.setName(user.getName());
          _user.setSurname(user.getSurname());
          _user.setBirthday(user.getBirthday());
          _user.setEmail(user.getEmail());
          _user.setPassword(user.getPassword());
          _user.setMemberType(user.getMemberType());
          return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // @DeleteMapping("/users/{id}")
    // public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
    //     try {
    //         userRepository.deleteById(id);
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @DeleteMapping("/users")
    // public ResponseEntity<HttpStatus> deleteAllUsers() {
    //     try {
    //         userRepository.deleteAll();
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }

    // }

    // @GetMapping("/users/published")
    // public ResponseEntity<List<User>> findByPublished() {
    //     try {
    //         List<User> users = userRepository.findAll();
    //         //   List<User> users = userRepository.findByPublished(true);

    //         if (users.isEmpty()) {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }
    //         return new ResponseEntity<>(users, HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

}
