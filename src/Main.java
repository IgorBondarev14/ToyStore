import Controller.Controller;
import Model.Action;
import Model.ActionFile;
import Model.Storage;
import Model.StorageAction;
import View.View;

public class Main {
    public static void main(String[] args) {
        Action action = new ActionFile("Toys.txt");
        Storage storage = new StorageAction(action);
        Controller controller = new Controller(storage);
        View view = new View(controller);
        view.start();
    }
}