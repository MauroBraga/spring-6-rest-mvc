package br.com.mrb.boot.spring_6_rest_mvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Customer {
    private UUID id;
    private String name;
    private LocalDate createdDate;
    private LocalDate lastModifieldData;

}
