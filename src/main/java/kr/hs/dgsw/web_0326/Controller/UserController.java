package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listUser")
    public List<User> listUser(){
        return this.userService.listAllUser();
    }

    @GetMapping("/findUser/{id}")
    public User findUser(@PathVariable Long id){
        return this.userService.findUser(id);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return this.userService.updateUser(id, user);
    }

    @PostMapping("/insultUser")
    public User init(@RequestBody User user){
        return this.userService.init(user);
    }

    @DeleteMapping("/delUser/{id}")
    public boolean delUser(@PathVariable Long id){
        return this.userService.delUser(id);
    }



}
