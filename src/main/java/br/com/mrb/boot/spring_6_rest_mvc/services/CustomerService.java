package br.com.mrb.boot.spring_6_rest_mvc.services;

import br.com.mrb.boot.spring_6_rest_mvc.model.Beer;
import br.com.mrb.boot.spring_6_rest_mvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();

    Customer getCustomerById(UUID id);
}
