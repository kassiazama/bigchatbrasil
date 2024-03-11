package com.kassiazama.bigchatbrasil.repository;

import com.kassiazama.bigchatbrasil.core.CrudRepository;
import com.kassiazama.bigchatbrasil.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Cliente findByEmail(String email);

}
