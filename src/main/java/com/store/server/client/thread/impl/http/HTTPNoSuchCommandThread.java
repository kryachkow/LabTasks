package com.store.server.client.thread.impl.http;

import com.store.server.client.thread.NoSuchCommandThread;
import com.store.server.client.utils.HTTPUtils;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HTTPNoSuchCommandThread extends NoSuchCommandThread {

  private static final String ERROR_MESSAGE = "В роботі HTTPNoSuchCommandThread сталась помилка ";

  private static final String NOT_FOUND_MESSAGE = "RESOURCE NOT FOUND";
  private final Socket socket;

  public HTTPNoSuchCommandThread(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new OutputStreamWriter(socket.getOutputStream()));
        socket) {

      HTTPUtils.toSendResponse(bufferedWriter,
          HTTPConstants.STATUS_CODE_NOT_FOUND,
          HTTPConstants.STATUS_NOT_FOUND,
          HTTPConstants.CONTENT_TYPE_TEXT_PLAIN,
          NOT_FOUND_MESSAGE.length(),
          NOT_FOUND_MESSAGE);

    } catch (IOException e) {
      System.out.println(ERROR_MESSAGE + e.getMessage());
    }
  }
}

