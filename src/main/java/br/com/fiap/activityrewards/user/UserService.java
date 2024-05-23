package br.com.fiap.activityrewards.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public void addScore(User user, Integer score) {
        repository.findById(user.getId()).orElseThrow(
            () -> new IllegalArgumentException("usuário não encontrado")
        );
            user.setScore(user.getScore() + score);
            System.out.println(user);
            repository.save(user);
        }
    
}
