package com.practice.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.demo.TestDataUtil;
import com.practice.demo.domain.dto.AuthorDto;
import com.practice.demo.domain.dto.BookDto;
import com.practice.demo.domain.entities.AuthorEntity;
import com.practice.demo.domain.entities.BookEntity;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
//這是 Spring Boot 提供的一個註解，用來標記一個整合測試類。它會啟動完整的 Spring 應用程式上下文（Application Context），包括 Web 環境、資料庫連線等，讓你可以在測試中模擬實際應用程式的運行環境。
@ExtendWith(SpringExtension.class)
//是 JUnit 5（Jupiter）提供的註解，用來註冊測試擴展（Extension）。這裡使用了 SpringExtension.class，表示將 Spring 的測試支持整合到 JUnit 5 中。
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//這是 Spring Test 框架提供的註解，用來標記當測試方法執行後，應用程式上下文（Application Context）被「污染」（dirty）了，需在每個測試方法後重新加載上下文。
//classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD：指定上下文在每個測試方法執行完後被標記為「污染」，並在下一個測試方法開始前重新創建。這樣可以確保每個測試方法運行在一個乾淨的上下文環境中，避免測試之間的狀態干擾。
@AutoConfigureMockMvc
//自動配置一個 MockMvc 實例，並將其注入到你的測試類中，這樣你可以在整合測試中模擬對 Web 控制器的請求（例如 GET、POST 等），而無需啟動完整的伺服器。
public class BookControllerIntegrationTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


    @Test
    public void testThatCreateBookSuccessfullyReturnHttp201Created() throws Exception {

        AuthorDto testAuthorA = TestDataUtil.createTestAuthorA();

        BookDto testbookA = TestDataUtil.createTestBookA(testAuthorA);


        String bookJson = objectMapper.writeValueAsString(testbookA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + testbookA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(testbookA.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(testbookA.getTitle())
        );

    }



    @Test
    public void testThatListBooksSuccessfullyReturnHttp200() throws Exception {


        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }


    @Test
    void testThatListBooksReturnsListOfBooks() throws Exception {


        mockMvc.perform(
                        MockMvcRequestBuilders.get("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }



}
