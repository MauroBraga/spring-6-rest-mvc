package br.com.mrb.boot.spring_6_rest_mvc.services;

import br.com.mrb.boot.spring_6_rest_mvc.model.Beer;

import java.util.UUID;

public interface BeerService {
    Beer getBeer(UUID id);
}
