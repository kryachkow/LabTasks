package com.store.server.client.thread.impl.tcp;

import com.store.server.client.thread.CountThread;
import com.store.service.CatalogService;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPCountThread extends CountThread {

  private static final String ERROR_MESSAGE = "В роботі TCPCountThread сталась помилка ";
  private static final String COUNT_RESULT = "CONT_RESULT ";
  private final CatalogService catalogService;
  private final Socket socket;

  public TCPCountThread(CatalogService catalogService, Socket socket) {
    this.catalogService = catalogService;
    this.socket = socket;
  }


  @Override
  public void run() {
    try (BufferedWriter bufferedWriter = new BufferedWriter(
            new OutputStreamWriter(socket.getOutputStream()));
        socket) {

      bufferedWriter.write(COUNT_RESULT + catalogService.getAllBooks().size());
      bufferedWriter.newLine();
      bufferedWriter.flush();

    } catch (IOException e) {
      System.out.println(ERROR_MESSAGE + e.getMessage());
    }
  }
}
