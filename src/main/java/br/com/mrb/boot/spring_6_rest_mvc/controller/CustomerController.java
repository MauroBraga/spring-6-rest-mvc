package br.com.mrb.boot.spring_6_rest_mvc.controller;

import br.com.mrb.boot.spring_6_rest_mvc.model.Beer;
import br.com.mrb.boot.spring_6_rest_mvc.model.Customer;
import br.com.mrb.boot.spring_6_rest_mvc.services.BeerService;
import br.com.mrb.boot.spring_6_rest_mvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers(){
        return service.listCustomers();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID id){

        log.debug("Get Consumer by Id - in controller ");

        return service.getCustomerById(id);
    }
}
