package org.wachowiak.spring.cache.app.demo;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.wachowiak.spring.cache.app.demo.MathController.E;
import static org.wachowiak.spring.cache.app.demo.MathController.PI;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {

    private WebApplicationContext context;
    private MockMvc mvc;

    @Autowired
    private void setWebApplicationContext(WebApplicationContext context) {
        this.context = context;
    }

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void piMustReturnCorrectValue() throws Exception {
        mvc.perform(get("/pi"))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith(PI)));
    }

    @Test
    public void eMustReturnCorrectValue() throws Exception {
        mvc.perform(get("/e"))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith(E)));
    }
}
