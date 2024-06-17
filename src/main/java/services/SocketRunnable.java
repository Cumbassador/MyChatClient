package services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


import java.net.Socket;
@RequiredArgsConstructor
public class SocketRunnable  implements Runnable{
    private final Socket socket;
    @SneakyThrows
    @Override
    public void run() {
        MessegeInputService serverReader = new MessegeInputServiceImpl(socket.getInputStream());
        while (true){
            System.out.println(serverReader.getMessege());

        }

    }
}
