package com.example.winery;

import com.example.winery.entity.Wine;
import com.example.winery.repository.WineRepository;
import com.example.winery.service.WineService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;


@SpringBootTest
@AutoConfigureMockMvc //need this in Spring Boot test
class Winery2WineControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private WineRepository wineRepository;



    @Test
    void getWines() throws Exception {


        Wine wineTest1 = new Wine();
        Wine wineTest2 = new Wine();

        wineTest1.setId(1);
        wineTest2.setId(2);

        Mockito
                .when(wineRepository.findAll())
                .thenReturn(Arrays.asList(wineTest1, wineTest2));

        mockMvc
                .perform(MockMvcRequestBuilders.get("/wine"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].id").value(2))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
