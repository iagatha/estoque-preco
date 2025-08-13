package com.interprise.estoque_preco.dto;

import java.io.Serializable;

public class PrecoDto implements Serializable {

    private String codigoProduto;
    private double preco;

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public double getPreco() {
        return preco;
    }
}
