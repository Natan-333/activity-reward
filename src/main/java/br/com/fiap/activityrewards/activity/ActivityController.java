package br.com.fiap.activityrewards.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    ActivityRepository repository;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("activitys", repository.findAll());
        return "activity/index";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirect) {
        var activity = repository.findById(id);

        if (activity.isEmpty()) {
            redirect.addFlashAttribute("message", "Error ao apagar, Tarefa não encontrada");
            return "redirect:/activity";
        }

        repository.deleteById(id);
        redirect.addFlashAttribute("message", messageSource.getMessage("activity.delete", null, LocaleContextHolder.getLocale()));
        return "redirect:/activity";
    }

    @GetMapping("new")
    public String form(Activity activity) {
        return "activity/form";
    }

    @PostMapping
    public String create(@Valid Activity activity,BindingResult result, RedirectAttributes redirect) {
        if(result.hasErrors()) return "activity/form";
        
        repository.save(activity);
        redirect.addFlashAttribute("message", "Tarefa cadastrada com sucesso");
        return "redirect:/activity";
    }
}
