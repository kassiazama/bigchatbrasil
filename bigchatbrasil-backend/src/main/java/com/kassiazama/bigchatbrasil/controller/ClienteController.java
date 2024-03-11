package com.kassiazama.bigchatbrasil.controller;

import com.kassiazama.bigchatbrasil.core.CrudController;
import com.kassiazama.bigchatbrasil.model.Cliente;
import com.kassiazama.bigchatbrasil.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClienteController extends CrudController<Cliente, Long> {

    @Autowired
    private ClienteService service;

    @GetMapping("find-by-email/{email}")
    public ResponseEntity<Cliente> findByEmail(@PathVariable(value = "email") String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

}
