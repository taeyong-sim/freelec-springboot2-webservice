package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)    //스프링 부트 테스트와 JUnit 사이의 연결자 역할
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired  //스프링이 관리하는 Bean을 주입받는다.
    private MockMvc mvc;    //웹 API를 테스트할 때 사용

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))            // /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk())             // mvc.perform의 결과 검증. HTTP Header의 Status 검증.(200인지 아닌지)
                .andExpect(content().string(hello));    // 응답 본문의 내용 검증. Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount))
            )
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name", is(name)))
           .andExpect(jsonPath("$.amount", is(amount)));


    }
}
