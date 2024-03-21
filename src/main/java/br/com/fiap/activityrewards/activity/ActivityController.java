package br.com.fiap.activityrewards.activity;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activity")
public class ActivityController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("activitys", List.of(
                "Atividade 1",
                "Atividade 2",
                "Atividade 3",
                "Teste Unitário"));
        return "activity/index";
    }

}
