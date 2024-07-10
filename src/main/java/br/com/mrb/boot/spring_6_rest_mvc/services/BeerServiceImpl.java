package br.com.mrb.boot.spring_6_rest_mvc.services;

import br.com.mrb.boot.spring_6_rest_mvc.model.Beer;
import br.com.mrb.boot.spring_6_rest_mvc.model.BeerStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BeerServiceImpl implements BeerService{
    @Override
    public Beer getBeer(UUID id) {
        return Beer.builder().id(id)
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
