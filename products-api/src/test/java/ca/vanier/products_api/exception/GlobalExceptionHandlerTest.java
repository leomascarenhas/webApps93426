package ca.vanier.products_api.exception;

import ca.vanier.products_api.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class) // Only load ProductController and GlobalExceptionHandler
class GlobalExceptionHandlerTest {
    /**
     * Self Explanatory
     */
    @Autowired
    private MockMvc mockMvc;

    @Test
    void handleProductNotFoundException() throws Exception {
        mockMvc.perform(get("/product/999")) // Assuming this ID doesn't exist
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Product Not Found"))
                .andExpect(jsonPath("$.message").value("Product with id 999 not found"));
    }

    @Test
    void handleProductValidationException() throws Exception {
        String invalidProductJson = """
            {
                "description": "",
                "price": -10.0,
                "category": ""
            }
        """;

        mockMvc.perform(post("/product")
                        .contentType("application/json")
                        .content(invalidProductJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Product Validation Failed"))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void handleRuntimeException() throws Exception {
        mockMvc.perform(get("/product/trigger-runtime")) // Assuming this triggers a RuntimeException
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Runtime Exception"))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }
}
