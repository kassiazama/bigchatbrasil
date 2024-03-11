package com.kassiazama.bigchatbrasil.controller;

import com.kassiazama.bigchatbrasil.core.CrudController;
import com.kassiazama.bigchatbrasil.model.Mensagem;
import com.kassiazama.bigchatbrasil.model.Plano;
import com.kassiazama.bigchatbrasil.model.TipoPlano;
import com.kassiazama.bigchatbrasil.model.record.Saldos;
import com.kassiazama.bigchatbrasil.service.MensagemService;
import com.kassiazama.bigchatbrasil.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/mensagem")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MensagemController extends CrudController<Mensagem, Long> {

    @Autowired
    private MensagemService service;

    @PostMapping("save-registra-consumo")
    public ResponseEntity<Optional<Saldos>>saveAndRegistraConsumo(@RequestBody Mensagem mensagem) {
        return ResponseEntity.ok(service.saveAndRegistraConsumo(mensagem));
    }
}
