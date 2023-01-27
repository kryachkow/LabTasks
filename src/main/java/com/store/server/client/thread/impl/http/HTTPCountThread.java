package com.store.server.client.thread.impl.http;

import com.store.server.client.thread.CountThread;
import com.store.server.client.utils.HTTPUtils;
import com.store.service.CatalogService;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HTTPCountThread extends CountThread {

  private static final String ERROR_MESSAGE = "В роботі HTTPCountThread сталась помилка ";
  private static final String RESPONSE_JSON_FORMAT = "{count: %s}";
  private final CatalogService catalogService;
  private final Socket socket;


  public HTTPCountThread(CatalogService catalogService, Socket socket) {
    this.catalogService = catalogService;
    this.socket = socket;
  }

  @Override
  public void run() {
    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new OutputStreamWriter(socket.getOutputStream()));
        socket) {
      String responseBody = String.format(RESPONSE_JSON_FORMAT,
          catalogService.getAllBooks().size());

      HTTPUtils.toSendResponse(bufferedWriter,
          HTTPConstants.STATUS_CODE_OK,
          HTTPConstants.STATUS_OK,
          HTTPConstants.CONTENT_TYPE_JSON,
          responseBody.length(),
          responseBody);

    } catch (IOException e) {
      System.out.println(ERROR_MESSAGE + e.getMessage());
    }
  }
}
