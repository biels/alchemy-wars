package com.biel.alchemywars.awbungeecordplugin;

import com.biel.alchemywars.communication.protobuf.SpringToBungee;
import com.biel.alchemywars.communication.utils.DockerServiceResolver;
import com.google.protobuf.InvalidProtocolBufferException;
import com.rabbitmq.client.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ListenerInfo;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import static com.biel.alchemywars.communication.utils.DockerServiceResolver.*;

@RequiredArgsConstructor
public class RabbitMQManager {
    @NonNull
    ServerManager serverManager;
    private Connection connection;
    private Channel channel;



    public void start() throws IOException, TimeoutException, InterruptedException {
        while (connection == null) {
            try {
                connect();
                System.out.println("Connected to RabbitMQ!");
            } catch (ConnectException e) {
                System.out.println("Could not connect to rabbitmq! Retrying after 5 seconds...");
                Thread.sleep(5000);
            }
        }
        defineQueues();
        startLobbyRegistrationsConsumer();
    }

    public void stop() {

    }

    private void connect() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(DockerService.RABBIT_MQ.getResolvedAddress());
        connectionFactory.setPort(5672);
        //Configure
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
    }

    private void defineQueues() throws IOException {
    }

    private void queueListener(String queue, java.util.function.Consumer<byte[]> callback) throws IOException {
        channel.queueDeclare(queue, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received in '" + queue + ": " + message + "'");
                callback.accept(body);
            }
        };
        channel.basicConsume(queue, true, consumer);
    }

    private void startLobbyRegistrationsConsumer() throws IOException, InvalidProtocolBufferException {
        queueListener("server-registrations", b -> {
            try {
                SpringToBungee.ServerRegistration request = SpringToBungee.ServerRegistration.parseFrom(b);
                int port = request.getPort();
                serverManager.addServer(port, request.getServerType());

            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }

        });
    }

    public void sendTestMessage() {
        //channel.basicPublish();
    }
}
