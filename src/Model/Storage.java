package Model;

import java.util.List;

public interface Storage {
    List<Toy> getAllToys();
    String CreateToy(Toy toy);
    void updateToy(Toy toy);
    void deleteToy(String delId);
}
