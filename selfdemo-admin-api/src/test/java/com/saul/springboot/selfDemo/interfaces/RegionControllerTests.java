package com.saul.springboot.selfDemo.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegionController.class)
public class RegionControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void list() throws Exception {
        mvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[")))
                .andExpect(content().string(containsString("\"name\":")))
        ;
    }

}
