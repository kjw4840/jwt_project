package jwt.prac.service;

import jwt.prac.entity.Role;
import jwt.prac.entity.User;
import jwt.prac.error.EmailDuplicateException;
import jwt.prac.error.ErrorCode;
import jwt.prac.repository.RoleRepository;
import jwt.prac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUser(String email) {
        return userRepository.findByEmail(email);
    }

    public void join(User user) {
        Optional<User> findUser = this.findUser(user.getEmail());
        if (findUser.isPresent()) {
            throw new EmailDuplicateException("email already exists", ErrorCode.EMAIL_DUPLICATION);
        }

        Role role = roleRepository.findById(2L).get();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
