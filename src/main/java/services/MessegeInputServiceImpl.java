package services;


import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MessegeInputServiceImpl implements MessegeInputService{
    private final BufferedReader bufferedReader;


    public MessegeInputServiceImpl(InputStream inputStream){
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @SneakyThrows
    @Override
    public String getMessege() {
        return bufferedReader.readLine();

    }
}
