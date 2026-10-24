package com.example.erp_todotcom.service;

import com.example.erp_todotcom.domain.entity.Pedido;
import com.example.erp_todotcom.domain.enums.StatusPedido;
import com.example.erp_todotcom.rest.dto.PedidoDTO;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto, JwtAuthenticationToken token);
    //'PedidoServiceimpl' IRÁ SATISFAZER O CONTRATO DESTA INTERFACE//
    //E CRIAR O A IMPLEMENTAÇÃO DO METODO 'salvar'//


    Optional<Pedido> obterPedidoCompleto (Integer id);

    void atualizacaoStatus(Integer id, StatusPedido statusPedido);
}
