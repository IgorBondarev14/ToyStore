package View;

import Controller.Controller;
import Model.Toy;

import java.util.List;
import java.util.Scanner;

public class View {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        Commands command = Commands.NONE;
        System.out.println("Здравсвуйте. Выберите команду.");
        while (true) {
            String com = handInput("(Возможные варианты команд: Help, Create, Change, Delete, Search, Read, " +
                    "Raffle, Exit)\nДля подробной информации выберите Help.\n" +
                    "Введите команду (регистр значения не имеет): ");
            command = Commands.valueOf(com.toLowerCase());
            if (command.equals(Commands.exit)) return;
            try {
                switch (command) {
                    case help:
                        System.out.println("Help   - описание команд\n" +
                                "Create - создание новой игрушки\nChange - изменение игрушки\nDelete - удаление " +
                                "игрушки\nSearch - поиск и просмотр игрушек\nRead   - просмотр всех игрушек\n" +
                                "Raffle - проведение розыгрыша игрушки\nExit   - закрытие программы");
                        break;
                    case create:
                        String name = handInput("Название игрушки: ");
                        String probabilityLoss = handInput("Вероятность выпадения игрушки: ");
                        String quantity = handInput("Количество: ");
                        controller.saveToy(new Toy(name, probabilityLoss, quantity));
                        break;
                    case change:
                        String id = handInput("Какую запись редактировать? Введите номер ID: ");
                        controller.idValidation(id);
                        controller.updateToy(id, createOne());
                        break;
                    case delete:
                        String delId = handInput("Какую запись удалить? Введите номер ID: ");
                        controller.idValidation(delId);
                        controller.deleteToy(delId);
                        break;
                    case search:
                        String SearchId = handInput("Введите id искомой записи: ");
                        Toy toy = controller.readToy(SearchId);
                        System.out.println(toy);
                        break;
                    case read:
                        List<Toy> toys = controller.readAll();
                        toys.forEach(i -> System.out.println(i + "\n"));
                        break;
                    case raffle:
                        controller.raffleToy();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Что-то пошло не так!\n" + e.getMessage());
                ;
            }
        }
    }

    private String handInput(String message) {
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        return scan.nextLine();
    }

    private Toy createOne() {
        String name = handInput("Наименование игрушки: ");
        String probabilityLoss = handInput("Вероятность выпадения: ");
        String quantity = handInput("Количество: ");
        Toy newToy = new Toy(name, probabilityLoss, quantity);
        return newToy;
    }
}
