import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publishers {


    public static void main(String[] args) {

        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("firstQueue");

            String[] messages = {"First Message", "Second Message", "Third Message", "Fourth Message"};

            MessageProducer producer = session.createProducer(destination);

            for (String message : messages) {

                TextMessage textMessage = session.createTextMessage(message);
                producer.send(textMessage);
            }


            System.out.println("Message was published to the queue");

            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
