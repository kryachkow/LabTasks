package com.store.server.client.thread.impl.tcp;

import com.store.model.Book;
import com.store.server.client.thread.ItemThread;
import com.store.service.CatalogService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPItemThread extends ItemThread {

  private static final String ERROR_MESSAGE = "В роботі TCPItemThread сталась помилка ";
  private static final  String REGEX = "get item=\\d+";
  private static final  String NO_SUCH_ITEM = "NO_SUCH_ITEM";
  private final CatalogService catalogService;
  private final Socket socket;
  private final String request;


  public TCPItemThread(CatalogService catalogService, Socket socket, String request) {
    this.catalogService = catalogService;
    this.socket = socket;
    this.request = request;
  }

  @Override
  public void run() {
    try (
        BufferedWriter bufferedWriter = new BufferedWriter(
            new OutputStreamWriter(socket.getOutputStream()));
        socket) {
      String response = NO_SUCH_ITEM;
      if (request.matches(REGEX)) {
        Book book = catalogService.getBook(
            Long.parseLong(request.substring(request.indexOf("=") + 1)));
        if (book != null) {
          response = book.getBookTitle();
          response += " | " + book.getPrice();
        }
      }

      bufferedWriter.write(response);
      bufferedWriter.newLine();
      bufferedWriter.flush();

    } catch (IOException e) {
      System.out.println(ERROR_MESSAGE + e.getMessage());
    }
  }
}
