package workers;

import syncObjects.PizzaStore;

import java.util.Random;

/**
 * Worker that removes a pizza to a PizzaStore.
 */
public class ClientWorker implements Runnable{
    private final PizzaStore pizzaStore;
    private int pizzasLeftToEat = 2;
    private final int id;

    /**
     * Constructs a worker that removes a pizza to the PizzaStore.
     *
     * @param pizzaStore  Thread-safe list used to store pizzas.
     */
    public ClientWorker(PizzaStore pizzaStore, int id) {
        this.pizzaStore = pizzaStore;
        this.id = id;
    }

    /**
     * Removes a pizza from the PizzaStore
     */
    @Override
    public void run() {
        Random random = new Random();

        while (pizzasLeftToEat != 0) {
            if (pizzaStore.isPizza()) {
                System.out.println("El cliente "+ id + " se ha comido una pizza");
                pizzaStore.remove();
                pizzasLeftToEat--;
                try {
                    System.out.println("El cliente "+ id + " se va al baño");
                    Thread.sleep((20 + random.nextInt(30 - 20 + 1)) * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("El cliente "+ id + " no se ha podido comer una pizza");
                try {
                    System.out.println("El cliente "+ id + " se va a dar un paseo");
                    Thread.sleep((10 + random.nextInt(15 - 10 + 1)) * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("El cliente "+ id +" está satisfecho y se va a su casa");
    }

}
