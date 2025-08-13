package com.interprise.estoque_preco.controller;

import com.interprise.estoque_preco.constantes.RabbitMQConstantes;
import com.interprise.estoque_preco.dto.EstoqueDto;
import com.interprise.estoque_preco.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    public ResponseEntity alterarEstoque(@RequestBody EstoqueDto estoqueDto){
        System.out.println(estoqueDto.getCodigoProduto());
        rabbitmqService.enviarMensagem(RabbitMQConstantes.FILA_ESTOQUE, estoqueDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
