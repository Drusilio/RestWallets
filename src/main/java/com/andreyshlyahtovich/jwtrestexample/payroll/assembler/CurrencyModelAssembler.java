package com.andreyshlyahtovich.jwtrestexample.payroll.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.andreyshlyahtovich.jwtrestexample.model.Currency;
import com.andreyshlyahtovich.jwtrestexample.payroll.CurrencyController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CurrencyModelAssembler implements RepresentationModelAssembler<Currency, EntityModel<Currency>> {

    @Override
    public EntityModel<Currency> toModel(Currency currency) {

        return EntityModel.of(currency, //
                linkTo(methodOn(CurrencyController.class).one(currency.getId())).withSelfRel(),
                linkTo(methodOn(CurrencyController.class).all()).withRel("currencies"));
    }
}