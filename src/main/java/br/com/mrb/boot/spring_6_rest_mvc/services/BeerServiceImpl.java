package br.com.mrb.boot.spring_6_rest_mvc.services;

import br.com.mrb.boot.spring_6_rest_mvc.model.Beer;
import br.com.mrb.boot.spring_6_rest_mvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeer(UUID id) {
        log.debug("Get Beer Id in service was called");

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
