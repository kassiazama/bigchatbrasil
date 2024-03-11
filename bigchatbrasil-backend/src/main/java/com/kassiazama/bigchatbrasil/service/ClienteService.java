package com.kassiazama.bigchatbrasil.service;

import com.kassiazama.bigchatbrasil.core.CrudService;
import com.kassiazama.bigchatbrasil.model.Cliente;
import com.kassiazama.bigchatbrasil.model.Plano;
import com.kassiazama.bigchatbrasil.model.TipoPlano;
import com.kassiazama.bigchatbrasil.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends CrudService<Cliente, Long> {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PlanoService planoService;

    public Cliente findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Cliente save(Cliente entity) {
        Cliente save = super.save(entity);

        Plano p = new Plano();
        p.setCliente(save);
        p.setTipoPlano(TipoPlano.PRE_PAGO);

        planoService.save(p);

        return save;
    }
}
