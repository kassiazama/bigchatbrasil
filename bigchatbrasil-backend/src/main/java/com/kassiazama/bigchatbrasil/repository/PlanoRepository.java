package com.kassiazama.bigchatbrasil.repository;

import com.kassiazama.bigchatbrasil.core.CrudRepository;
import com.kassiazama.bigchatbrasil.model.Plano;
import com.kassiazama.bigchatbrasil.model.record.Saldos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends CrudRepository<Plano, Long> {

    Plano findByClienteId(Long id);

    @Query(value = "select new com.kassiazama.bigchatbrasil.model.record.Saldos(" +
            " p.limiteConsumido, p.saldoPrePagoTotal, p.limiteTotal, p.tipoPlano ) from Plano p where p.cliente.id = :id")
    Saldos findSaldoByClienteId(@Param("id") Long id);

}
