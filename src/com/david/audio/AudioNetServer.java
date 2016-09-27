package com.david.audio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class AudioNetServer {

    private ServerSocket sock;
    private int port;

    private PrintWriter out;
    private BufferedReader in;

    public AudioNetServer() {
        this (4444);
    }
    public AudioNetServer(int port) {
        this.port = port;
    }

    public void setPort (int port) {
        this.port = port;
    }

    public void Start() {

        System.out.println("Starting Server...");
        System.out.println("Listening at port: ["+port+"]");

        try  {
            sock = new ServerSocket(port);

            while (true) {
                Socket client = sock.accept();
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));

                System.out.println("Client connected!");

                ProcessRequests();
            }

        } catch (BindException e) {
            System.out.println("Port already taken, try a different one!");
            return;
        } catch (IOException e)  {
            e.printStackTrace();
        }
    }

    private void ProcessRequests() {

        String inputstring;

        try {
            while ((inputstring = in.readLine()) != null) {
                System.out.println(inputstring);


                switch (inputstring) {

                    case "pause": Runtime.getRuntime().exec("xdotool key XF86AudioPlay");
                        break;

                    case "next": Runtime.getRuntime().exec("xdotool key XF86AudioNext");
                        break;

                    case "prev": Runtime.getRuntime().exec("xdotool key XF86AudioPrev");
                        break;

                    default:
                    Runtime.getRuntime().exec("amixer -D pulse sset Master " + inputstring);
                }

                out.println("OK");
            }
        } catch (IOException e) {
            System.out.println("Client left!");
        }
    }
}
