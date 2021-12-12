package jwt.prac.controller;


import jwt.prac.domain.SignVo;
import jwt.prac.entity.User;
import jwt.prac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public List<User> users() {
        List<User> users = userService.getUsers();
        return users;
    }

//    @PostMapping("/admin/user")
//    public User user(@RequestBody User user) {
//        User findUser = userService.getUser(user.getEmail());
//        return findUser;
//    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody SignVo signVo) {
        User user = User.builder()
                .email(signVo.getEmail())
                .password(signVo.getPassword())
                .build();
        userService.join(user);
        return ResponseEntity.ok("success");
    }
}
