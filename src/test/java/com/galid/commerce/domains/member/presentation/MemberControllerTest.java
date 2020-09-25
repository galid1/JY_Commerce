package com.galid.commerce.domains.member.presentation;

import com.galid.commerce.domains.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(value = MemberController.class)
class MemberControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private MemberService service;

    @BeforeEach
    public void init() {
        given(service.signUp(any())).willReturn(null);
    }

    @Test
    public void 회원가입_페이지_로딩() throws Exception {
        //given, when
        ResultActions resultActions = mvc.perform(get("/auth/signUp"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("members/signUp"));
    }

    @Test
    public void 회원가입() throws Exception {
        //given, when
        ResultActions resultActions = mvc.perform(post("/auth/signUp")
                .param("authId", "GALID")
                .param("password", "TEST")
                .param("name", "TEST")
                .param("phone", "TEST")
                .param("city", "TEST")
                .param("street", "TEST")
        );

        //then
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/auth/signIn"));
    }

    @Test
    public void 로그인_페이지_로딩() throws Exception {
        //given, when
        ResultActions resultActions = mvc.perform(get("/auth/signIn"));

        //then
        resultActions.andExpect(status().isOk())
                     .andExpect(view().name("members/signIn"));
    }
}