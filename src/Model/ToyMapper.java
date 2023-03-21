package Model;

public class ToyMapper implements Mapperable {
    public String mapS(Toy toy) {
        return String.format("%s, %s, %s, %s", toy.getId(), toy.getName(), toy.getProbabilityLoss(), toy.getQuantity());
    }

    public Toy mapT(String string) {
        String[] strings = string.split(", ", 4);
        return new Toy(strings[0], strings[1], strings[2], strings[3]);
    }
}

