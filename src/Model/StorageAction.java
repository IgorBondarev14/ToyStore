package Model;

import java.util.ArrayList;
import java.util.List;

public class StorageAction implements Storage{
    private ToyMapper mapper = new ToyMapper();
    private Action action;

    public StorageAction(Action action) {
        this.action = action;
    }

    @Override
    public List<Toy> getAllToys() {
        List<String> strings = action.readAllStrings();
        List<Toy> toys = new ArrayList<>();
        for (String string : strings) {
            toys.add(mapper.mapT(string));
        }
        return toys;
    }

    @Override
    public String CreateToy(Toy toy) {

        List<Toy> toys = getAllToys();
        int max = 0;
        for (Toy t : toys) {
            int id = Integer.parseInt(t.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        toy.setId(id);
        toys.add(toy);
        writeDown(toys);
        return id;
    }

    @Override
    public void updateToy (Toy toy) {
        List<Toy> users = getAllToys();
        Toy target = users.stream().filter(i -> i.getId().equals(toy.getId())).findFirst().get();
        target.setName(toy.getName());
        target.setProbabilityLoss(toy.getProbabilityLoss());
        target.setQuantity(toy.getQuantity());
        writeDown(users);
    }

    @Override
    public void deleteToy (String delId) {
        List<Toy> toys = getAllToys();
        for (Toy t : toys) {
            if (t.getId().equals(delId)) {
                toys.remove(t);
                break;
            }
        }
        writeDown(toys);
    }

    private void writeDown(List<Toy> toys) {
        List<String> strings = new ArrayList<>();
        for (Toy t: toys) {
            strings.add(mapper.mapS(t));
        }
        action.saveAllStrings(strings);
    }
}
