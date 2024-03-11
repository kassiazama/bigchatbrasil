package com.kassiazama.bigchatbrasil.service;

import com.kassiazama.bigchatbrasil.core.CrudService;
import com.kassiazama.bigchatbrasil.exception.MethodNotAllowedException;
import com.kassiazama.bigchatbrasil.model.Plano;
import com.kassiazama.bigchatbrasil.model.TipoPlano;
import com.kassiazama.bigchatbrasil.model.record.Saldos;
import com.kassiazama.bigchatbrasil.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PlanoService extends CrudService<Plano, Long> {

    @Autowired
    private PlanoRepository repository;

    private final BigDecimal CUSTO_MENSAGEM = BigDecimal.valueOf(0.25);

    public Plano findByClienteId(Long id) {
        return repository.findByClienteId(id);
    }

    public Saldos findSaldoByClienteId(Long id) {
        return repository.findSaldoByClienteId(id);
    }

    public Optional<Plano> atualizarSaldos(Long id, BigDecimal saldo, TipoPlano tipoPlano) {

        Optional<Plano> byId = findById(id);

        return byId.map(plano -> {
            if (TipoPlano.PRE_PAGO.equals(tipoPlano)) {
                plano.setSaldoPrePagoTotal(plano.getSaldoPrePagoTotal().add(saldo));
            } else {
                plano.setLimiteTotal(saldo);
            }
            return repository.save(plano);
        });
    }

    public Optional<Plano> atualizarPlano(Long id, TipoPlano tipoPlano) {

        Optional<Plano> byId = findById(id);

        return byId.map(plano -> {
            plano.setTipoPlano(tipoPlano);
            return repository.save(plano);
        });
    }

    public Optional<Saldos> registrarConsumo(Long id) {
        Optional<Plano> byId = findById(id);
        return byId.map(this::registrarConsumoParaPlano);
    }

    private Saldos registrarConsumoParaPlano(Plano plano) {
        if (TipoPlano.PRE_PAGO.equals(plano.getTipoPlano())) {
            return registrarConsumoPrePago(plano);
        } else {
            return registrarConsumoPosPago(plano);
        }
    }

    private Saldos registrarConsumoPrePago(Plano plano) {
        BigDecimal novoSaldo = plano.getSaldoPrePagoTotal().subtract(CUSTO_MENSAGEM);
        validarSaldoPrePago(novoSaldo, "Plano sem saldo disponível");
        plano.setSaldoPrePagoTotal(novoSaldo);
        return salvarEAtualizarSaldos(plano);
    }

    private Saldos registrarConsumoPosPago(Plano plano) {
        BigDecimal novoSaldo = plano.getLimiteConsumido().add(CUSTO_MENSAGEM);
        validarSaldoPos(plano.getLimiteTotal(), novoSaldo, "Plano sem saldo disponível");
        plano.setLimiteConsumido(novoSaldo);
        return salvarEAtualizarSaldos(plano);
    }

    private void validarSaldoPrePago(BigDecimal novoSaldo, String mensagemErro) {
        if (novoSaldo.compareTo(CUSTO_MENSAGEM) < 0 && novoSaldo.compareTo(BigDecimal.ZERO) != 0) {
            throw new MethodNotAllowedException(mensagemErro);
        }
    }

    private void validarSaldoPos(BigDecimal limiteTotal, BigDecimal novoSaldo, String mensagemErro) {
        if (novoSaldo.compareTo(limiteTotal) > 0) {
            throw new MethodNotAllowedException(mensagemErro);
        }
    }

    private Saldos salvarEAtualizarSaldos(Plano plano) {
        Plano save = repository.save(plano);
        return new Saldos(save.getLimiteConsumido(), save.getSaldoPrePagoTotal(), save.getLimiteTotal(), save.getTipoPlano());
    }

}
