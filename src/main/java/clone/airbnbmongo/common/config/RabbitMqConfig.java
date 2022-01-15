package clone.airbnbmongo.common.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Configuration
public class RabbitMqConfig {

    static final String topicExchangeName = "airbnb-clone-exchange";

    static final String queueName = "airbnb-clone";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("airbnb.clone.#");
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.setRoutingKey(queueName);

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter adapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(adapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(receiver, "receiveMessage");
        adapter.setMessageConverter(new Jackson2JsonMessageConverter());

        return adapter;
    }

//    @Bean(name = "rabbitListenerContainerFactory")
//    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        configurer.configure(factory, connectionFactory);
//
//        factory.setAfterReceivePostProcessors(new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                System.out.println("message = " + message);
//
//                String type = message.getMessageProperties().getHeader("type").toString();
//                System.out.println("type = " + type);
//
//                if("clone.airbnbpg.common.TestDto".equalsIgnoreCase(type))  {
//                    message.getMessageProperties().setHeader("__TypeId__", TestDto.class.getName());
//                    System.out.println("changed type = " + message.getMessageProperties().getHeader("type"));
//                    System.out.println("changed type = " + message.getMessageProperties().getHeader("__TypeId__"));
//                }
//
//                return message;
//            }
//        });
//
//        return factory;
//    }

}

