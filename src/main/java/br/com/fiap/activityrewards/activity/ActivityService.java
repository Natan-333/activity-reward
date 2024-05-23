package br.com.fiap.activityrewards.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.activityrewards.user.User;
import br.com.fiap.activityrewards.user.UserService;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository repository;

    @Autowired
    UserService userService;

    public void catchActivity(Long id, User myuser) {
        var activity = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("tarefa não encontrada")
        );

        if(activity.getUser() != null)
        throw new IllegalArgumentException("tarefa já atribuída");

        activity.setUser(myuser);
        repository.save(activity);
    }

    public void dropActivity(Long id, User myuser) {
        var activity = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("tarefa não encontrada")
        );

        if(activity.getUser() != myuser)
            throw new IllegalArgumentException("tarefa atribuída a outro usuário");

        activity.setUser(null);
        repository.save(activity);
    }

    public void inc(Long id, User myuser) {
        var activity = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("tarefa não encontrada")
        );
        if (activity.getStatus() + 10 > 100) return;
         activity.setStatus(activity.getStatus() + 10);

        if (activity.getStatus() == 100){
            userService.addScore(myuser, activity.getScore());
        }
        
        repository.save(activity);
    }

    public void dec(Long id, User myuser) {
        var activity = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("tarefa não encontrada")
        );
        if (activity.getStatus() - 10 < 0) return;

        activity.setStatus(activity.getStatus() - 10);
        repository.save(activity);
    }
  
}
