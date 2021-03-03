package com.urlapp.urlapp.controller;

import com.urlapp.urlapp.service.StatisticService;
import com.urlapp.urlapp.service.UrlShorteningService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.BDDMockito.given;
@RunWith(SpringRunner.class)
@WebMvcTest
public class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticService statisticService;

    @MockBean
    private UrlShorteningService urlShorteningService;

    @Test
    public void whenUrlCodeDoesNotExist() throws Exception {
        String nonExistingCode = "2saoww";

        given(urlShorteningService.getOriginalUrl(nonExistingCode))
                .willThrow(new NullPointerException());

        this.mockMvc.perform(get("/"+nonExistingCode+"/"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenCreatingShortLink() throws Exception {
        String existingCode = "acva12a";


        given(urlShorteningService.getOriginalUrl(existingCode)).willReturn("google.com");

        // When and Then
        this.mockMvc.perform(get("http://localhost:8080/acva12a")).andExpect(status().isOk());
    }
}
