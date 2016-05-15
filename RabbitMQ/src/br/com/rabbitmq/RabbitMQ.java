package br.com.rabbitmq;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQ {
	
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setVirtualHost("pratica-psd");
		factory.setHost("52.36.237.202");
		factory.setPort(5672);
		factory.setUsername("edvan");
		factory.setPassword("edvan");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    
	    channel.queueDeclare(QUEUE_NAME, true, false, false, null);
	    
	    String message = "Teste";
	    Scanner scan = new Scanner(System.in);
	    
	    while(true){
	    	System.out.println("Informe uma mensagem: ");
	    	message = scan.nextLine();
	    	if(message == "q"){
	    		break;
	    	}
		    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		    System.out.println(" [x] Enviada '" + message + "'");
	    }
	    
	    
	    channel.close();
	    connection.close();
	}

}
