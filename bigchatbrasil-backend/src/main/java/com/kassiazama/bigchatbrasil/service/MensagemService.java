package com.kassiazama.bigchatbrasil.service;

import com.kassiazama.bigchatbrasil.core.CrudService;
import com.kassiazama.bigchatbrasil.model.Mensagem;
import com.kassiazama.bigchatbrasil.model.Plano;
import com.kassiazama.bigchatbrasil.model.TipoPlano;
import com.kassiazama.bigchatbrasil.model.record.Saldos;
import com.kassiazama.bigchatbrasil.repository.MensagemRepository;
import com.kassiazama.bigchatbrasil.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class MensagemService extends CrudService<Mensagem, Long> {

    @Autowired
    private MensagemRepository repository;

    @Autowired
    private PlanoService planoService;

    public Optional<Saldos> saveAndRegistraConsumo(Mensagem entity) {
        super.save(entity);
        return planoService.registrarConsumo(entity.getCliente().getId());
    }
}
