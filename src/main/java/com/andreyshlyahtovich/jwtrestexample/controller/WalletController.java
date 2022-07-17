package com.andreyshlyahtovich.jwtrestexample.controller;

import com.andreyshlyahtovich.jwtrestexample.model.Wallet;
import com.andreyshlyahtovich.jwtrestexample.controller.assembler.WalletModelAssembler;
import com.andreyshlyahtovich.jwtrestexample.service.WalletService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class WalletController {

    private final WalletService walletService;
    private final WalletModelAssembler assembler;

    public WalletController(WalletService walletService, WalletModelAssembler assembler) {
        this.walletService = walletService;
        this.assembler = assembler;
    }

    @GetMapping("/wallets")
    public CollectionModel<EntityModel<Wallet>> all() {
        List<EntityModel<Wallet>> wallets = walletService.getAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(wallets, linkTo(methodOn(WalletController.class).all()).withSelfRel());
    }


    @GetMapping("/wallets/{id}")
    public EntityModel<Wallet> one(@PathVariable Long id) {
        Wallet wallet = walletService.getById(id);
        return assembler.toModel(wallet);
    }

    @PostMapping("/wallets")
    ResponseEntity<?> newWallet(@RequestBody Wallet newWallet) {
        EntityModel<Wallet> entityModel = assembler.toModel(walletService.save(newWallet));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/wallets/{id}")
    ResponseEntity<?> replaceWallet(@RequestBody Wallet newWallet, @PathVariable Long id) {
        Wallet updatedWallet = walletService.replace(id, newWallet);
        EntityModel<Wallet> entityModel = assembler.toModel(updatedWallet);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/wallets/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        walletService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/wallets/adduser/{walletId}/{userId}")
    public ResponseEntity<?> addUserToWallet(@PathVariable Long walletId,@PathVariable Long userId) {
        walletService.addUserToWallet(walletId, userId);
        return ResponseEntity.noContent().build();
    }
}
