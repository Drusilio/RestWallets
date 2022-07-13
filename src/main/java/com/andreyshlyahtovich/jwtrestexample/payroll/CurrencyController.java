package com.andreyshlyahtovich.jwtrestexample.payroll;

import com.andreyshlyahtovich.jwtrestexample.model.Currency;
import com.andreyshlyahtovich.jwtrestexample.payroll.assembler.CurrencyModelAssembler;
import com.andreyshlyahtovich.jwtrestexample.payroll.exception.CurrencyNotFoundException;
import com.andreyshlyahtovich.jwtrestexample.repository.CurrencyRepository;
import com.andreyshlyahtovich.jwtrestexample.service.CurrencyService;
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
public class CurrencyController {

    private final CurrencyService currencyService;

    private final CurrencyModelAssembler assembler;

    public CurrencyController(CurrencyService currencyService, CurrencyModelAssembler assembler) {
        this.assembler = assembler;
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies")
    public CollectionModel<EntityModel<Currency>> all() {


         List<EntityModel<Currency>> currencies = currencyService.getAll().stream()
                 .map(assembler::toModel)
                 .collect(Collectors.toList());

         return CollectionModel.of(currencies, linkTo(methodOn(CurrencyController.class).all()).withSelfRel());
    }


    @GetMapping("/currencies/{id}")
    public EntityModel<Currency> one(@PathVariable Long id) {
        Currency currency = currencyService.getById(id);
        return assembler.toModel(currency);
    }

    @PostMapping("/currencies")
    ResponseEntity<?> newCurrency(@RequestBody Currency newCurrency) {
        EntityModel<Currency> entityModel = assembler.toModel(currencyService.save(newCurrency));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }



    @PutMapping("/currencies/{id}")
    ResponseEntity<?> replaceCurrency(@RequestBody Currency newCurrency, @PathVariable Long id) {
        Currency updatedCurrency = currencyService.replace(id, newCurrency);
        EntityModel<Currency> entityModel = assembler.toModel(updatedCurrency);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/currencies/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        currencyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
