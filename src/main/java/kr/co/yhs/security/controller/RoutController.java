package kr.co.yhs.security.controller;

import kr.co.yhs.security.entity.Member;
import kr.co.yhs.security.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@AllArgsConstructor
@Slf4j
public class RoutController {
    MemberService memberService;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "gogogo index");
        return "index";
    }
    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "gogogo admin : "+principal.getName());
        return "admin";
    }
    @GetMapping("/board")
    public String board(Model model, Principal principal) {
        model.addAttribute("message", "gogogo board : "+principal.getName());
        return "board";
    }
}
