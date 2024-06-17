package services;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;

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

            MessegeInputService messegeInputService = new MessegeInputServiceImpl(System.in);
            System.out.println("Введите свой логин:");
            String login = messegeInputService.getMessege();

            System.out.println("Введите свой пароль:");
            String password = messegeInputService.getMessege();

            //!autho!lohin:password
            serverWriter.println("!autho!" + login + ":" + password);
            serverWriter.flush();

            while (true){
                System.out.println("Введите сообщение.");
            String consoleMessege = messegeInputService.getMessege();
                if ("Exit".equalsIgnoreCase(consoleMessege)) {
                    break;
                }

            serverWriter.println(consoleMessege);
            serverWriter.flush();
            }




        }




    }

}
