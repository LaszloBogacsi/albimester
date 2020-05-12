package com.laszlobogacsi.albimester.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BillUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void canUploadAMultipartFile() throws Exception {
        MockMultipartFile billXmlFile = new MockMultipartFile("data", "some-filename.xml", "text/xml", "<?xml version=\"1.0\"?><aNode>test text</aNode>".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload-bill").file(billXmlFile))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}
