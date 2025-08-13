package com.interprise.estoque_preco.dto;

import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;


public class EstoqueDto implements Serializable {

    private String codigoProduto;
    private int quantidade;

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
