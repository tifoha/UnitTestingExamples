package UnitTestingExamples.ch05;

import java.util.*;

/**
 * Created by Vitaly on 01.10.2016.
 */
public class RaceResultsService {
    private static final String WITHOUT_CATEGORY = null;
    private Map<String, Collection<Client>> clientsByCategories = new HashMap<>();
    private Logger logger;

    public RaceResultsService(Logger logger) {
        this.logger = logger;
    }

    public void addSubscriber(Client client) {
        addSubscriber(WITHOUT_CATEGORY, client);
    }

    public void send(Message message) {
        send(WITHOUT_CATEGORY, message);
    }

    public boolean removeSubscriber(Client client) {
        return removeSubscriber(WITHOUT_CATEGORY, client);
    }

    public Logger getLogger() {
        return logger;
    }

    public void addSubscriber(String category, Client client) {
        Collection<Client> clients = this.clientsByCategories.computeIfAbsent(category, s -> new HashSet<>());
        clients.add(client);
    }

    public void send(String category, Message message) {
        logger.logg(message.getDate(), message.getText());
        Collection<Client> clients = clientsByCategories.get(category);

        if (clients != null) {
            clients.forEach(client -> client.receive(message));
        }
    }

    public boolean removeSubscriber(String category, Client client) {
        Collection<Client> clients = clientsByCategories.get(category);

        if (clients != null) {
            return clients.remove(client);
        }

        return false;
    }
}
