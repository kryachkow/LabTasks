package com.store.server.client.factory.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientSideApplicationDummy {

  public ClientSideApplicationDummy(){};

  public String getResponseFromServer(String request, int port) throws IOException {
    try(Socket socket = new Socket("127.0.0.1", port);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
        bufferedWriter.write(request);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        StringBuilder builder = new StringBuilder();
        String line = bufferedReader.readLine();
        do {
          builder.append(line);
          builder.append("\n");
          line = bufferedReader.readLine();
        } while (line != null);


        return builder.toString().trim();
    }
  }
}
