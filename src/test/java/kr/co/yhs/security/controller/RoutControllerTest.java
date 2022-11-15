package kr.co.yhs.security.controller;

import kr.co.yhs.security.entity.Member;
import kr.co.yhs.security.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoutControllerTest {
    @Autowired
    MockMvc  mockMvc;
    @Autowired
    MemberService service;
    @Test
    @DisplayName("index page not login")
    public void index_any() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("board page not login")
    public void board_any() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("ADMIN 권한 페이지 접속 성공")
    @WithMockUser(username = "yhs", password = "123", roles = "ADMIN")
    public void admin_page_success() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("ADMIN 권한 페이지 접속 오류(USER)")
    @WithMockUser(username = "yhs", password = "123", roles = "USER")
    public void admin_page_fail_user() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    @WithMockUser
    @DisplayName("login 성공")
    @Transactional
    public void login_success() throws  Exception {
        Member member = createUser("yhs", "123", "USER");
        mockMvc.perform(formLogin().user(member.getUserName()).password("123")).andDo(print()).andExpect(authenticated());
    }
    @Test
    @WithMockUser
    @DisplayName("login 오류")
    @Transactional
    public void login_fail() throws  Exception {
        Member member = createUser("yhs", "123", "USER");
        mockMvc.perform(formLogin().user(member.getUserName()).password("1234")).andDo(print()).andExpect(unauthenticated());
    }


    @DisplayName("web security ignore Test")
    @Transactional
    @Test
    public void web_ignore() throws  Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/web/ignore/"))
                .andDo(print())
                .andExpect(status().isOk());
    }



    public Member createUser(String userName, String pass, String role) {
        Member member = new Member();
        member.setUserName(userName);
        member.setPasswrod(pass);
        member.setRole(role);
        return service.createMember(member);
    }
}