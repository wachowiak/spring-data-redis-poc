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

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CounterController.class)
public class CounterControllerIntegrationTest {

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
    public void getForInvalidKeyMustReturnNull() throws Exception {

        String key = "keyNull";

        mvc.perform(get("/get/" + key))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyOrNullString()));
    }

    @Test
    public void getAfterIncMustReturnOne() throws Exception {

        String key = "key";

        mvc.perform(post("/inc/" + key))
                .andExpect(status().isOk());

        mvc.perform(get("/get/" + key))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith("1")));
    }
}