package com.store.server.client.thread.impl.http;

import com.store.model.Book;
import com.store.server.client.thread.ItemThread;
import com.store.server.client.utils.HTTPUtils;
import com.store.service.CatalogService;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HTTPItemThread extends ItemThread {

  private static final String ERROR_MESSAGE = "В роботі HTTPItemThread сталась помилка ";
  private static final  String REGEX = "GET, URL: /shop/item\\?get_info=\\d+";
  private static final String JSON_FORMAT = "{name: %s, price: %s}";
  private static final String NOT_FOUND_MESSAGE = "BOOK NOT FOUND";
  private final CatalogService catalogService;
  private final Socket socket;
  private final String request;


  public HTTPItemThread(CatalogService catalogService, Socket socket, String request) {
    this.catalogService = catalogService;
    this.socket = socket;
    this.request = request;
  }

  @Override
  public void run() {
    try (BufferedWriter bufferedWriter = new BufferedWriter(
            new OutputStreamWriter(socket.getOutputStream()));
        socket) {


      if (request.matches(REGEX)) {
        Book book = catalogService.getBook(
            Long.parseLong(request.substring(request.indexOf("=") + 1)));

        if (book != null) {

          String jsonResp = String.format(JSON_FORMAT, book.getBookTitle(), book.getPrice());

          HTTPUtils.toSendResponse(bufferedWriter,
              HTTPConstants.STATUS_CODE_OK,
              HTTPConstants.STATUS_OK,
              HTTPConstants.CONTENT_TYPE_JSON,
              jsonResp.length(),
              jsonResp);

          return;
        }
      }
      HTTPUtils.toSendResponse(bufferedWriter,
          HTTPConstants.STATUS_CODE_NO_CONTENT,
          HTTPConstants.STATUS_NO_CONTENT,
          HTTPConstants.CONTENT_TYPE_TEXT_PLAIN,
          NOT_FOUND_MESSAGE.length(),
          NOT_FOUND_MESSAGE);

    } catch (IOException e) {
      System.out.println(ERROR_MESSAGE + e.getMessage());
    }
  }

}
