package com.ppcredit.bamboo.backend.web.rest;

import com.ppcredit.bamboo.backend.BambooBackendserviceApp;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.staticdocs.SwaggerResultHandler;

import javax.inject.Inject;
import java.io.IOException;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BambooBackendserviceApp.class)
@ActiveProfiles("dev")
public class Swagger2MarkupIntTest {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/docs/asciidoc/snippets");

    @Inject
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() throws IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(documentationConfiguration(this.restDocumentation)).build();
    }

//    @Test
//    public void getAllUsersSamples() throws Exception {
//        this.mockMvc.perform(get("/api/books")
//          .accept(MediaType.APPLICATION_JSON))
//          .andDo(document("getAllUsersUsingGET", preprocessResponse(prettyPrint())))
//          .andExpect(status().isOk());
//    }
    @Test
    public void convertSwaggerToAsciiDoc() throws Exception {
        this.mockMvc.perform(get("/v2/api-docs")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(SwaggerResultHandler.outputDirectory("target/swagger")
            .build())
            .andExpect(status().isOk());
    }
}
