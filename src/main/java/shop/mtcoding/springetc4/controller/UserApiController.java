package shop.mtcoding.springetc4.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.springetc4.model.User;
import shop.mtcoding.springetc4.model.UserJpaRepository;


@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    // private final UserRepository userRepository;
    private final UserJpaRepository userRepository;


    @PostMapping("/users") // user가 아니라 users 인 이유 : users라는 컬렉션에다가 내가 insert 한건 하겟다
    public ResponseEntity<?> addUser(User user){
        User userPS = userRepository.save(user);
        return new ResponseEntity<>(userPS, HttpStatus.CREATED);
    }


    @PutMapping("/users/{id}") 
    public ResponseEntity<?> updateUser(@PathVariable Long id, User user) { // 수정이나 이런걸 할때는 주소로 받는 값(ex)id 값)은 신뢰성 검사를 해야 함
        User userPS = userRepository.findById(id).get();
        if (userPS == null) {
            return new ResponseEntity<>("해당 유저가 없습니다", HttpStatus.BAD_REQUEST);
        }
        userPS.update(user.getPassword(), user.getEmail());
        User updateuserPS = userRepository.save(userPS);
        return new ResponseEntity<>(updateuserPS, HttpStatus.OK);
    }
    

    @DeleteMapping("/users/{id}") 
    public ResponseEntity<?> deleteUser(@PathVariable Long id) { // 수정이나 이런걸 할때는 주소로 받는 값(ex)id 값)은 신뢰성 검사를 해야 함
        User userPS = userRepository.findById(id).get();
        if (userPS == null) {
            return new ResponseEntity<>("해당 유저가 없습니다", HttpStatus.BAD_REQUEST);
        }
        userRepository.delete(userPS);
        return new ResponseEntity<>(userPS, HttpStatus.OK);
    }

    
    @GetMapping("/users")
    public ResponseEntity<?> findUsers(@RequestParam(defaultValue = "0") int page) {
        Page<User> userListPS = userRepository.findAll(PageRequest.of(page, 2)); // 조회된 값은 PS를 붙임
        return new ResponseEntity<>(userListPS, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUserOne(@PathVariable Long id) {
        User userPS = userRepository.findById(id).get(); // 조회된 값은 PS를 붙임
        if (userPS == null) {
            return new ResponseEntity<>("해당 유저가 없습니다", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userPS, HttpStatus.OK);
    }
}
