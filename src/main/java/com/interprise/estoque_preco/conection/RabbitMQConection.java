package com.interprise.estoque_preco.conection;

import com.interprise.estoque_preco.constantes.RabbitMQConstantes;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConection {

    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    // Criando a fila
    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    // Criando a exchange
    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    //Relacionar a fila com a exchange
    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(),fila.getName(), null);
    }

    @PostConstruct
    private void adicionar(){
       var filaEstoque = this.fila(RabbitMQConstantes.FILA_ESTOQUE );
       var filaPreco = this.fila(RabbitMQConstantes.FILA_PRECO);
       var troca = this.trocaDireta();

       var ligacaoEstoque = this.relacionamento(filaEstoque, troca);
       var ligacaoPreco = this.relacionamento(filaEstoque, troca);

       this.amqpAdmin.declareQueue(filaEstoque);
       this.amqpAdmin.declareQueue(filaPreco);

       this.amqpAdmin.declareExchange(troca);

       this.amqpAdmin.declareBinding(ligacaoEstoque);
       this.amqpAdmin.declareBinding(ligacaoPreco);
    }
}
