package com.store.server.client.thread.impl.tcp;

import com.store.server.client.thread.NoSuchCommandThread;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPNoSuchCommandThread extends NoSuchCommandThread {

  private static final String NO_SUCH_COMMAND = "NO_SUCH_COMMAND";
  private static final String ERROR_MESSAGE = "В роботі TCPNoSuchCommandThread сталась помилка ";
  private final Socket socket;

  public TCPNoSuchCommandThread(Socket socket) {
    this.socket = socket;
  }


  @Override
  public void run() {
    try (BufferedWriter bufferedWriter = new BufferedWriter(
            new OutputStreamWriter(socket.getOutputStream()));
        socket) {
      bufferedWriter.write(NO_SUCH_COMMAND);
      bufferedWriter.newLine();
      bufferedWriter.flush();

    } catch (IOException e) {
      System.out.println(ERROR_MESSAGE + e.getMessage());
    }
  }
}
