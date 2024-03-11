package com.kassiazama.bigchatbrasil.controller;

import com.kassiazama.bigchatbrasil.core.CrudController;
import com.kassiazama.bigchatbrasil.model.Plano;
import com.kassiazama.bigchatbrasil.model.TipoPlano;
import com.kassiazama.bigchatbrasil.model.record.Saldos;
import com.kassiazama.bigchatbrasil.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/plano")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlanoController extends CrudController<Plano, Long> {

    @Autowired
    private PlanoService service;

    @GetMapping("find-by-cliente/{id}")
    public ResponseEntity<Plano> findByClient(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findByClienteId(id));
    }

    @GetMapping("find-saldo-by-cliente/{id}")
    public ResponseEntity<Saldos> findSaldoByCliente(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findSaldoByClienteId(id));
    }

    @GetMapping("atualizar-saldo/{id}/{tipoPlano}/{saldo}")
    public ResponseEntity<Optional<Plano>> atualizarSaldo(@PathVariable(value = "id") Long id,
                                                          @PathVariable(value = "tipoPlano") TipoPlano tipoPlano,
                                                          @PathVariable(value = "saldo") BigDecimal saldo) {
        return ResponseEntity.ok(service.atualizarSaldos(id, saldo, tipoPlano));
    }

    @GetMapping("atualizar-plano/{id}/{tipoPlano}")
    public ResponseEntity<Optional<Plano>> atualizarPlano(@PathVariable(value = "id") Long id,
                                                          @PathVariable(value = "tipoPlano") TipoPlano tipoPlano) {
        return ResponseEntity.ok(service.atualizarPlano(id, tipoPlano));
    }
}
