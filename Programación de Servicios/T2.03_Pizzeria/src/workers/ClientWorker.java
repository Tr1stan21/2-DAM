package workers;

import syncObjects.PizzaStore;

import java.util.Random;

/**
 * Worker that removes a pizza to a PizzaStore.
 */
public class ClientWorker implements Runnable{
    private final PizzaStore pizzaStore;
    private int pizzasEaten = 0;
    private final int id;

    /**
     * Constructs a worker that removes a pizza from the PizzaStore.
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
        System.out.println("Inicio del cliente " + (id));

        while (pizzasEaten < 5) {
            if (pizzaStore.tryToEatPizza()) {
                pizzasEaten++;
                System.out.println("El cliente "+ id + " se ha comido una pizza, lleva " + pizzasEaten +" pizzas comidas");
                if (pizzasEaten == 5) {
                    System.out.println("El cliente " + id + " está satisfecho y se va a su casa");
                    break;
                }

                try {
                    System.out.println("El cliente "+ id + " se va a dar un paseo");
                    Thread.sleep((20 + random.nextInt(30 - 20 + 1)) * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("El cliente "+ id + " no ha podido comerse una pizza");
                try {
                    System.out.println("El cliente "+ id + " se va a dar un paseo");
                    Thread.sleep((10 + random.nextInt(15 - 10 + 1)) * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("El cliente "+ id + " ha terminado de pasear, vuelve a entrar a la pizzería");
        }
    }

}
