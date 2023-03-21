package Controller;

import Model.Toy;
import Model.Storage;

import java.util.List;

public class Controller {
    private final Storage storage;

    public Controller(Storage storage) {
        this.storage = storage;
    }

    public void saveToy(Toy toy) throws Exception {
        checkToy(toy);
        storage.CreateToy(toy);
    }

    public Toy readToy (String id) throws Exception {
        List<Toy> toys = storage.getAllToys();
        for (Toy t: toys) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        throw new Exception("Искомый id не найден\n");
    }

    public List<Toy> readAll() {
        List<Toy> result = storage.getAllToys();
        return result;
    }

    public void updateToy (String id, Toy newToy) throws Exception {
        idValidation(id);
        newToy.setId(id);
        checkToyId(newToy);
        storage.updateToy(newToy);
    }

    public void updateQuantity (String id) throws Exception {
        List<Toy> toys = storage.getAllToys();
        for (Toy t: toys) {
            if (t.getId().equals(id)){
                int quantity = Integer.parseInt(t.getQuantity()) - 1 ;
                t.setQuantity(Integer.toString(quantity));
                storage.updateToy(t);
            }
        }
    }
    private static void checkToy(Toy toy) throws Exception {
        if (toy.getName().equals(" "))
            throw new Exception("Название игрушки отсутсвует\n");
        if (toy.getProbabilityLoss().equals(" "))
            throw new Exception("Вероятность выпадения не задана\n");
        if (toy.getQuantity().equals(" "))
            throw new Exception("Количество игрушек не задано\n");
    }

    private void checkToyId(Toy toy) throws Exception{
        if (toy.getId().isEmpty())
            throw new Exception("Искомый id не найден\n");
        checkToy(toy);
    }

    public void idValidation (String inputId) throws Exception {
        List<Toy> toys = storage.getAllToys();
        for (Toy t: toys) {
            if (t.getId().equals(inputId))
                return;
        }
        throw new Exception("Искомый id не найден\n");
    }

    public void deleteToy(String delId) {
        storage.deleteToy(delId);
    }

    public void raffleToy() throws Exception {
        List<Toy> toys = storage.getAllToys();
        int maxProbability = 0;
        String raffleId = "";
        for (Toy t: toys) {
            int currentProbability = Integer.parseInt(t.getProbabilityLoss()) * Integer.parseInt(t.getQuantity());
            if (currentProbability > maxProbability) {
                maxProbability = currentProbability;
                raffleId = t.getId();
            }
        }
        System.out.println("В данном розыгрыше выпала игрушка:");
        System.out.println(readToy(raffleId));
        System.out.println();
        updateQuantity(raffleId);
    }
}

