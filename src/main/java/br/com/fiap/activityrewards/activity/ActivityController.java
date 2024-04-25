package br.com.fiap.activityrewards.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
    MessageSource messageSource;

    @GetMapping
    public String index(Model model,@AuthenticationPrincipal DefaultOAuth2User user) {
        User myuser = (User) user;
        log.info("usuario carregado: "+ myuser);
        
        model.addAttribute("activitys", repository.findAll());
        model.addAttribute("user", user.getAttribute("name"));
        model.addAttribute("avatar", user.getAttribute("avatar_url"));
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
