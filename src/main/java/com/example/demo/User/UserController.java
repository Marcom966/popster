package com.example.demo.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<UserOfPopster> getStudents(){
        return userService.getStudents();
    }

    @PostMapping
    public void registerNewUser(@RequestBody UserOfPopster user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{user_id}")
    public void deleteUser(@PathVariable("user_id") Long id){
        userService.deleteUser(id);
    }

    @PutMapping(path = "{user_id}")
    public void putUser(@PathVariable("user_id") Long userId, @RequestParam(required = false) String name, @RequestParam(required = false) String eMail){
        userService.updateUser(userId, name, eMail);
    }


}
