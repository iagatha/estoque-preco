package com.interprise.estoque_preco.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //envia a mensagem para o RabbitMQ
    public void enviarMensagem(String nomeFila, Object mensagem){
        rabbitTemplate.convertAndSend(nomeFila, mensagem);
    }
}
