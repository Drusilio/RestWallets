package com.andreyshlyahtovich.jwtrestexample.payroll.assembler;

import com.andreyshlyahtovich.jwtrestexample.model.Wallet;
import com.andreyshlyahtovich.jwtrestexample.payroll.WalletController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WalletModelAssembler implements RepresentationModelAssembler<Wallet, EntityModel<Wallet>> {

    @Override
    public EntityModel<Wallet> toModel(Wallet wallet) {

        return EntityModel.of(wallet, 
                linkTo(methodOn(WalletController.class).one(wallet.getId())).withSelfRel(),
                linkTo(methodOn(WalletController.class).all()).withRel("wallets"));
    }
}