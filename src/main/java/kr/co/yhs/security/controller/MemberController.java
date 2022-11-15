package kr.co.yhs.security.controller;

import kr.co.yhs.security.entity.Member;
import kr.co.yhs.security.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {
    MemberService memberService;
    @GetMapping("/acount/{userName}/{passwrod}/{role}")
    public Member createMember(@ModelAttribute Member member) {
        log.info(member.toString());
        return memberService.createMember(member);
    }
    @GetMapping("/web/ignore/")
    public String webSecurityTest() {
        log.info("web Security Ignore Test");
        return "success";
    }
}
