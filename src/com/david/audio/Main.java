package com.david.audio;

import java.net.BindException;

public class Main {

    public static void main(String[] args) {

        AudioNetServer serv = new AudioNetServer();

        if (args.length > 0) {
            try {

                int port = Integer.parseInt(args[0]);
                serv = new AudioNetServer(port);
            } catch (Exception e) {
                System.out.println("Invalid PORT number as argument!\nUsing default port [4444]");

            }
        }

        serv.Start();
    }
}
