package br.com.fiap.activityrewards.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.activityrewards.user.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("activity")
@Slf4j
public class ActivityController {

    @Autowired
    ActivityRepository repository;

    @Autowired
    ActivityService service;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index(Model model,@AuthenticationPrincipal DefaultOAuth2User user) {
        User myuser = (User) user;
        log.info("usuario carregado: "+ myuser);
        
        model.addAttribute("activitys", repository.findAll());
        model.addAttribute("user", user.getAttribute("name"));
        model.addAttribute("avatar", user.getAttribute("avatar_url"));
        model.addAttribute("principal", myuser);
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

    @PostMapping("/catch/{id}")
    public String catchActivity(@PathVariable Long id, @AuthenticationPrincipal DefaultOAuth2User user){
        User myuser = (User) user;
         service.catchActivity(id, myuser);
         return "redirect:/activity";
    }

    @PostMapping("/drop/{id}")
    public String dropcAtivity(@PathVariable Long id, @AuthenticationPrincipal DefaultOAuth2User user){
        User myuser = (User) user;
        service.dropActivity(id, myuser);
        return "redirect:/activity";

    }

    @PostMapping("/inc/{id}")
    public String inc(@PathVariable Long id, @AuthenticationPrincipal DefaultOAuth2User user){
        User myuser = (User) user;
        service.inc(id, myuser);
        return "redirect:/activity";

    }

    @PostMapping("/dec/{id}")
    public String dec(@PathVariable Long id, @AuthenticationPrincipal DefaultOAuth2User user){
        User myuser = (User) user;
        service.dec(id, myuser);
        return "redirect:/activity";

    }
}
