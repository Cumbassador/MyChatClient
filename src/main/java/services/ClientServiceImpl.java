package services;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientServiceImpl implements ClientService{
    public final static int PORT = 8081;
    public final static String HOST = "localhost";
    @Override
    @SneakyThrows
    public void start() {
        Socket socket = new Socket(HOST,PORT);


        if (socket.isConnected()) {
            new Thread(new SocketRunnable(socket)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Выберите действие:\n1. Авторизация\n2. Регистрация");
                String choice = scanner.nextLine();

                if (choice.equals("1")) {
                    System.out.println("Введите свой логин:");
                    String login = scanner.nextLine();

                    System.out.println("Введите свой пароль:");
                    String password = scanner.nextLine();

                    serverWriter.println("!autho!" + login + ":" + password);
                    serverWriter.flush();
                    break;

                } else if (choice.equals("2")) {
                    System.out.println("Введите логин для регистрации:");
                    String login = scanner.nextLine();

                    System.out.println("Введите пароль для регистрации:");
                    String password = scanner.nextLine();

                    serverWriter.println("!register!" + login + ":" + password);
                    serverWriter.flush();
                    break;

                } else {
                    System.out.println("Некорректный выбор, попробуйте снова.");
                }
            }

            while (true) {
                System.out.println("Введите сообщение:");
                String consoleMessage = scanner.nextLine();

                serverWriter.println(consoleMessage);
                serverWriter.flush();
            }
        }




    }

}
