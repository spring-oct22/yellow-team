package com.example.winery.service;

import com.example.winery.dto.WineDTO;
import com.example.winery.entity.Wine;
import com.example.winery.mapper.WineMapper;
import com.example.winery.repository.WineRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc

class WineServiceTest {

    @Test
    void getAll() {
        //Given
        WineRepository mock = Mockito.mock(WineRepository.class);
        Wine wineTest1 = new Wine();
        Wine wineTest2 = new Wine();

        wineTest1.setId(1);
        wineTest2.setId(2);

        Mockito
                .when(mock.findAll())
                .thenReturn(Arrays.asList(wineTest1, wineTest2));

        WineService wineService = new WineService(mock,new WineMapper());
        //when
        Collection<WineDTO> wines = wineService.getAll();
        //then
        assertTrue(wines.size()>0);
        assertEquals(2,wines.size());
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user("ben")
                .password("benspassword");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("ben"));
    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user("invalid")
                .password("invalidpassword");

        mockMvc.perform(login)
                .andExpect(unauthenticated());
    }

}
