import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

import javax.jms.*;

public class PublisherJSON {

    public static void main(String[] args) {


        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        Connection connection;

        {
            try {
                connection = factory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = session.createQueue("jsonQueue");

                JSONObject json = new JSONObject();
                json.put("from_date", "24-Mar-1983");
                json.put("due_date", "30-Mar-1984");
                json.put("email", "user@gmail.com");
                json.put("query", "select * from table_name");

                TextMessage textMessage = session.createTextMessage(json.toString());
                MessageProducer producer = session.createProducer(destination);
                producer.send(textMessage);

                session.close();
                connection.close();


            } catch (JMSException e) {
                e.printStackTrace();
            }
        }


    }
}