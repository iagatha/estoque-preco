package com.interprise.estoque_preco.controller;

import com.interprise.estoque_preco.constantes.RabbitMQConstantes;
import com.interprise.estoque_preco.dto.PrecoDto;
import com.interprise.estoque_preco.service.RabbitmqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "preco")
public class PrecoController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    public ResponseEntity alterarPreco(@RequestBody PrecoDto precoDto){
        //System.out.println(precoDto.getPreco());
        rabbitmqService.enviarMensagem(RabbitMQConstantes.FILA_PRECO, precoDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
