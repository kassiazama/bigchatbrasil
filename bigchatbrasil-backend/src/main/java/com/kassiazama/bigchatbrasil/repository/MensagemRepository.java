package com.kassiazama.bigchatbrasil.repository;

import com.kassiazama.bigchatbrasil.core.CrudRepository;
import com.kassiazama.bigchatbrasil.model.Mensagem;
import com.kassiazama.bigchatbrasil.model.Plano;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemRepository extends CrudRepository<Mensagem, Long> {

   Mensagem findByClienteId(Long id);

}
