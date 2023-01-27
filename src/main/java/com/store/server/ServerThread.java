package com.store.server;

import com.store.server.client.factory.ClientThreadAbstractFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

  private static final String CLIENT_SOCKET_ERROR
      = "Пробема з створенням клієнтського сокету причина : ";

  private final ServerSocket serverSocket;
  private final ClientThreadAbstractFactory clientThreadAbstractFactory;

  public ServerThread(int port, ClientThreadAbstractFactory clientThreadAbstractFactory)
      throws IOException {
    this.serverSocket = new ServerSocket(port);
    this.clientThreadAbstractFactory = clientThreadAbstractFactory;

  }

  @Override
  public void run() {
    while (true) {
      try {
        Socket socket = acceptClientSocket(serverSocket);
        clientThreadAbstractFactory.getClientTread(socket).start();
      } catch (IOException e) {
        System.out.println(CLIENT_SOCKET_ERROR + e.getMessage());
      }
    }
  }


  private Socket acceptClientSocket(ServerSocket serverSocket) throws IOException {
    Socket socket = serverSocket.accept();
    return socket;
  }
}
