package com.andreyshlyahtovich.jwtrestexample.payroll;

import com.andreyshlyahtovich.jwtrestexample.model.Wallet;
import com.andreyshlyahtovich.jwtrestexample.payroll.assembler.WalletModelAssembler;
import com.andreyshlyahtovich.jwtrestexample.payroll.exception.WalletNotFoundException;
import com.andreyshlyahtovich.jwtrestexample.repository.WalletRepository;
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

    private final WalletRepository walletRepository;

    private final WalletModelAssembler assembler;

    public WalletController(WalletRepository walletRepository, WalletModelAssembler assembler) {
        this.walletRepository = walletRepository;
        this.assembler = assembler;
    }

    @GetMapping("/wallets")
    public CollectionModel<EntityModel<Wallet>> all() {


        List<EntityModel<Wallet>> wallets = walletRepository
                .findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(wallets, linkTo(methodOn(WalletController.class).all()).withSelfRel());
    }


    @GetMapping("/wallets/{id}")
    public EntityModel<Wallet> one(@PathVariable Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new WalletNotFoundException(id));
        return assembler.toModel(wallet);
    }

    @PostMapping("/wallets")
    ResponseEntity<?> newWallet(@RequestBody Wallet newWallet) {
        EntityModel<Wallet> entityModel = assembler.toModel(walletRepository.save(newWallet));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }



    @PutMapping("/wallets/{id}")
    ResponseEntity<?> replaceWallet(@RequestBody Wallet newWallet, @PathVariable Long id) {

        Wallet updatedWallet = walletRepository.findById(id)
                .map(wallet -> {
                    wallet.setName(newWallet.getName());
                    wallet.setAmount(newWallet.getAmount());
                    wallet.setCurrency(newWallet.getCurrency());
                    return walletRepository.save(wallet);
                })
                .orElseGet(() -> {
                    newWallet.setId(id);
                    return walletRepository.save(newWallet);
                });

        EntityModel<Wallet> entityModel = assembler.toModel(updatedWallet);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/wallets/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        walletRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
