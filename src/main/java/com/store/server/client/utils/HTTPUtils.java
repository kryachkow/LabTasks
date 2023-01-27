package com.store.server.client.utils;

import java.io.BufferedWriter;
import java.io.IOException;

public class HTTPUtils {

  private static final String HEAD_FORMAT = "HTTP/1.1 %s %s";
  private static final String CONTENT_TYPE_FORMAT = "Content-Type: %s";
  private static final String CONTENT_LENGTH_FORMAT = "Content-Length %s";

  private HTTPUtils(){}

  public static void toSendResponse(BufferedWriter bufferedWriter, int statusCode, String status, String contentType, int length, String body)
      throws IOException {
    bufferedWriter.write(String.format(HEAD_FORMAT, statusCode, status));
    bufferedWriter.newLine();
    bufferedWriter.write(String.format(CONTENT_TYPE_FORMAT, contentType));
    bufferedWriter.newLine();
    bufferedWriter.write(String.format(CONTENT_LENGTH_FORMAT, length));
    bufferedWriter.newLine();
    bufferedWriter.newLine();
    bufferedWriter.write(body);
    bufferedWriter.newLine();
    bufferedWriter.flush();
  }



}
