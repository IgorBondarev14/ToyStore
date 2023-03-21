package Model;

public class Toy {
    private String id;
    private String name;
    private String probabilityLoss;

    private String quantity;

    public Toy(String id, String name, String probabilityLoss, String quantity) {
        this.id = id;
        this.name = name;
        this.probabilityLoss = probabilityLoss;
        this.quantity = quantity;
    }
    public Toy(String name, String probabilityLoss, String quantity) {
        this.name = name;
        this.probabilityLoss = probabilityLoss;
        this.quantity = quantity;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProbabilityLoss() {
        return probabilityLoss;
    }

    public void setProbabilityLoss(String probabilityLoss) {
        this.probabilityLoss = probabilityLoss;
    }

    public String getQuantity() {return quantity;}

    public void setQuantity(String quantity) {this.quantity = quantity;}

    public String toString() {
        return String.format("id игрушки - `%s`, Наименование игрушки - `%s`, Вероятность выпадения - `%s`, " +
                "Количество - `%s`", id, name, probabilityLoss, quantity);
    }
}

