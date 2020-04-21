import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

import javax.jms.*;

public class ConsumerJson {

    public static void main(String[] args) {

        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            Connection connection = factory.createConnection();

            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("jsonQueue");

            MessageConsumer consumer = session.createConsumer(destination);

            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        TextMessage textMessage = (TextMessage) message;
                        JSONObject json = new JSONObject(textMessage.getText());

                        System.out.println(json.get("from_date"));
                        System.out.println(json.get("due_date"));
                        System.out.println(json.get("email"));

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

                private TextMessage textMessage(TextMessage message) {
                    return message;
                }
            });

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
