package controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ClinicController {

    @RequestMapping(value = "clinic", method = RequestMethod.GET)
    public String login(ModelMap model, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ? "redirect:/users.do" : "";
    }
}
