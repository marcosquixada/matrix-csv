package com.matrix.api.controller;

import com.matrix.api.service.MatrixService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MatrixController.class)
class MatrixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatrixService matrixService;

    @Test
    void shouldEcho() throws Exception {
        MockMultipartFile mockMultipartFile
                = new MockMultipartFile(
                "file",
                "matrix.csv",
                "text/csv",
                new ClassPathResource("matrix.csv").getInputStream()
        );

        when(matrixService.echo(mockMultipartFile)).thenReturn("1, 2, 3\n" +
                "4, 5, 6\n" +
                "7, 8, 9");

        mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.GET, "/echo")
                        .file(mockMultipartFile))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSum() throws Exception {
        MockMultipartFile mockMultipartFile
                = new MockMultipartFile(
                "file",
                "matrix.csv",
                "text/csv",
                new ClassPathResource("matrix.csv").getInputStream()
        );

        when(matrixService.sum(mockMultipartFile)).thenReturn(45L);

        mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.GET, "/sum")
                        .file(mockMultipartFile))
                .andExpect(status().isOk());
    }

    @Test
    void shouldMultiply() throws Exception {
        MockMultipartFile mockMultipartFile
                = new MockMultipartFile(
                "file",
                "matrix.csv",
                "text/csv",
                new ClassPathResource("matrix.csv").getInputStream()
        );

        when(matrixService.multiply(mockMultipartFile)).thenReturn(0L);

        mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.GET, "/multiply")
                        .file(mockMultipartFile))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFlatten() throws Exception {
        MockMultipartFile mockMultipartFile
                = new MockMultipartFile(
                "file",
                "matrix.csv",
                "text/csv",
                new ClassPathResource("matrix.csv").getInputStream()
        );

        System.out.println(matrixService.flatten(mockMultipartFile));

        when(matrixService.flatten(mockMultipartFile)).thenReturn("1, 2, 3, 4, 5, 6, 7, 8, 9");

        mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.GET, "/flatten")
                        .file(mockMultipartFile))
                .andExpect(status().isOk());
    }

    @Test
    void shouldInvert() throws Exception {
        MockMultipartFile mockMultipartFile
                = new MockMultipartFile(
                "file",
                "matrix.csv",
                "text/csv",
                new ClassPathResource("matrix.csv").getInputStream()
        );

        when(matrixService.invert(mockMultipartFile)).thenReturn("1, 4, 7\n" +
                "2, 5, 8\n" +
                "3, 6, 9");

        mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.GET, "/invert")
                        .file(mockMultipartFile))
                .andExpect(status().isOk());
    }
}
