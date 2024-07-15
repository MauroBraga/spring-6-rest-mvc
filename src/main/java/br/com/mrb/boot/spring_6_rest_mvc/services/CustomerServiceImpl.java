package br.com.mrb.boot.spring_6_rest_mvc.services;

import br.com.mrb.boot.spring_6_rest_mvc.model.Beer;
import br.com.mrb.boot.spring_6_rest_mvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        this.customerMap =  new HashMap<>();

        var customer1 = Customer.builder().id(UUID.randomUUID()).name("customer1").createdDate(LocalDate.now()).lastModifieldData(LocalDate.now()).build();
        var customer2 = Customer.builder().id(UUID.randomUUID()).name("customer2").createdDate(LocalDate.now()).lastModifieldData(LocalDate.now()).build();
        var customer3 = Customer.builder().id(UUID.randomUUID()).name("customer3").createdDate(LocalDate.now()).lastModifieldData(LocalDate.now()).build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public List<Customer> listCustomers() {
        return  new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        log.debug("Get Beer by Id - in service. Id: " + id.toString());

        return customerMap.get(id);
    }

    @Override
    public Customer save(Customer customer) {
        var savedCustomer = Customer
                    .builder()
                .id(UUID.randomUUID())
                .name(customer.getName())
                .lastModifieldData(LocalDate.now())
                .createdDate(LocalDate.now())
                .build()
                ;
        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void update(UUID id, Customer customer) {
        var update = customerMap.get(id);
        if(update!=null){
            update.setName(customer.getName());
            update.setLastModifieldData(LocalDate.now());

            customerMap.put(update.getId(), update);
        }
    }

    @Override
    public void deleteById(UUID id) {
        customerMap.remove(id);
    }
}
