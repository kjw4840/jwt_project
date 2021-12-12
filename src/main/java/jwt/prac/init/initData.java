package jwt.prac.init;

import jwt.prac.entity.Role;
import jwt.prac.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initData {

    private final initService initService;

    @PostConstruct
    public void init() {
        initService.roleInit();
    }


    @Component
    @RequiredArgsConstructor
    @Transactional
    static class initService {

        private final PasswordEncoder passwordEncoder;
        private final EntityManager em;

        public void roleInit() {
            Role roleAdmin = new Role();
            roleAdmin.setName("ADMIN");
            em.persist(roleAdmin);

            Role roleUser = new Role();
            roleUser.setName("USER");
            em.persist(roleUser);

            User user1 = new User();
            user1.setEmail("test1@t.t");
            user1.setPassword(passwordEncoder.encode("asdf1234"));
            user1.getRoles().add(roleAdmin);
            em.persist(user1);

            User user2 = new User();
            user2.setEmail("test2@t.t");
            user2.setPassword(passwordEncoder.encode("asdf"));
            user2.getRoles().add(roleUser);
            em.persist(user2);
        }

    }
}
