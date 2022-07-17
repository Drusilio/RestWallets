package com.andreyshlyahtovich.jwtrestexample.controller;

import com.andreyshlyahtovich.jwtrestexample.model.User;
import com.andreyshlyahtovich.jwtrestexample.controller.assembler.UserModelAssembler;
import com.andreyshlyahtovich.jwtrestexample.service.UserService;
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
public class UserController {

    private final UserService userService;
    private final UserModelAssembler assembler;

    public UserController(UserService userService, UserModelAssembler assembler) {
        this.assembler = assembler;
        this.userService = userService;
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {
         List<EntityModel<User>> users = userService.getAll()
                 .stream()
                 .map(assembler::toModel)
                 .collect(Collectors.toList());
         return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Long id) {
        User user = userService.getById(id);
        return assembler.toModel(user);
    }

    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) {
        EntityModel<User> entityModel = assembler.toModel(userService.save(newUser));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        User updatedUser = userService.replace(id, newUser);
        EntityModel<User> entityModel = assembler.toModel(updatedUser);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User with id " + id + " successfully deleted");
    }
}
