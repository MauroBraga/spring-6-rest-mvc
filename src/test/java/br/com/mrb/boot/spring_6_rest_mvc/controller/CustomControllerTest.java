package br.com.mrb.boot.spring_6_rest_mvc.controller;


import br.com.mrb.boot.spring_6_rest_mvc.model.Beer;
import br.com.mrb.boot.spring_6_rest_mvc.model.Customer;
import br.com.mrb.boot.spring_6_rest_mvc.services.CustomerService;
import br.com.mrb.boot.spring_6_rest_mvc.services.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    @Autowired
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setUp() {
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Test
    void testPatchBeer() throws Exception {
        var customer = Customer.builder().id(UUID.randomUUID()).name("customer1").createdDate(LocalDate.now()).lastModifieldData(LocalDate.now()).build();

    }

    @Test
    void testDeleteBeer() throws Exception {
        var customer = Customer.builder().id(UUID.randomUUID()).name("customer1").createdDate(LocalDate.now()).lastModifieldData(LocalDate.now()).build();

        mockMvc.perform(delete("/api/v1/customer/" + customer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(customerService).deleteById(uuidArgumentCaptor.capture());
         assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }


    @Test
    void testUpdate() throws Exception {
        var customer = Customer.builder().id(UUID.randomUUID()).name("customer1").createdDate(LocalDate.now()).lastModifieldData(LocalDate.now()).build();

        mockMvc.perform(put("/api/v1/customer/" + customer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent());

        verify(customerService).update(any(UUID.class), any(Customer.class));

    }

    @Test
    void testCreate() throws Exception {
        var customer = Customer.builder().id(UUID.randomUUID()).name("customer1").createdDate(LocalDate.now()).lastModifieldData(LocalDate.now()).build();

        given(customerService.save(any(Customer.class))).willReturn(customer);

        mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void testListCustomers() throws Exception {
        given(customerService.listCustomers()).willReturn(customerServiceImpl.listCustomers());

        mockMvc.perform(get("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()",is(3)));
    }


    @Test
    void getCustomerById() throws Exception {
        Customer customer = customerServiceImpl.listCustomers().getFirst();

        given(customerService.getCustomerById(customer.getId())).willReturn(customer);

        mockMvc.perform(get("/api/v1/customer/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(customer.getId().toString())))
                .andExpect(jsonPath("$.name", is(customer.getName())));
    }
}