package br.com.fiap.activityrewards.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    ActivityRepository repository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("activitys", repository.findAll());
        return "activity/index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirect) {
        var activity = repository.findById(id);

        if (activity.isEmpty()) {
            redirect.addFlashAttribute("message", "Error ao apagar, Tarefa não encontrada");
            return "redirect:/activity";
        }

        repository.deleteById(id);
        redirect.addFlashAttribute("message", "Tarefa apagada com sucesso");
        return "redirect:/activity";
    }

    @GetMapping("new")
    public String form() {
        return "activity/form";
    }

    @PostMapping
    public String create(Activity activity, RedirectAttributes redirect) {
        repository.save(activity);
        redirect.addFlashAttribute("message", "Tarefa cadastrada com sucesso");
        return "redirect:/activity";
    }
}
