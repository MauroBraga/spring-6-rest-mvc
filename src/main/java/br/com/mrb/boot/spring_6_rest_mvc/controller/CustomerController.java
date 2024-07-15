package br.com.mrb.boot.spring_6_rest_mvc.controller;

import br.com.mrb.boot.spring_6_rest_mvc.model.Beer;
import br.com.mrb.boot.spring_6_rest_mvc.model.Customer;
import br.com.mrb.boot.spring_6_rest_mvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity deleteById(@PathVariable("customerId") UUID id){
        service.deleteById(id);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity handlePut(@PathVariable("customerId") UUID id,@RequestBody Customer customer){
        service.update(id,customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + id.toString());
        return  new ResponseEntity(headers,HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody  Customer customer){
        Customer savedCustomer = service.save(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return  new ResponseEntity(headers, HttpStatus.CREATED);
    }

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
