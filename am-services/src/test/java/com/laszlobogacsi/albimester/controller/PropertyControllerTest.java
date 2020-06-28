package com.laszlobogacsi.albimester.controller;

import com.laszlobogacsi.albimester.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void canRegisterANewProperty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/property/register")
                .content(asJsonString(newPropertyDetails().build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


}
