package com.store.server.client.factory.impl;

import com.store.server.client.factory.ClientThreadAbstractFactory;
import com.store.server.client.thread.CountThread;
import com.store.server.client.thread.ItemThread;
import com.store.server.client.thread.NoSuchCommandThread;
import com.store.server.client.thread.impl.tcp.TCPCountThread;
import com.store.server.client.thread.impl.tcp.TCPItemThread;
import com.store.server.client.thread.impl.tcp.TCPNoSuchCommandThread;
import com.store.service.CatalogService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class TCPClientThreadFactory implements ClientThreadAbstractFactory {

  private static final String READING_REQUEST_ERROR = "Проблема з прочитанням TCP запиту ";
  private static final String REQUEST_REGEX = "get [a-z]+(.*)";
  private final CatalogService catalogService;
  private final Map<String, BiFunction<Socket, String, Thread>> threadCreationMap;

  public TCPClientThreadFactory(CatalogService catalogService) {
    this.catalogService = catalogService;
    threadCreationMap = new HashMap<>();
    threadCreationMap.put("get count", (socket, str) -> createCountThread(socket));
    threadCreationMap.put("get item", this::createItemThread);
  }

  @Override
  public Thread getClientTread(Socket socket) {
    Thread clientThread = createNoSuchCommandThread(socket);
    try {
      BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      String line = bufferedReader.readLine();

      if (line.matches(REQUEST_REGEX)) {
        String toCheck = line.contains("=") ? line.substring(0, line.indexOf("=")) : line;
        clientThread = threadCreationMap.getOrDefault(toCheck,
            (sk, str) -> createNoSuchCommandThread(sk)).apply(socket, line);
      }
    } catch (IOException e) {
      System.out.println(READING_REQUEST_ERROR + e.getMessage());
    }

    return clientThread;
  }

  @Override
  public CountThread createCountThread(Socket socket) {
    return new TCPCountThread(catalogService, socket);
  }

  @Override
  public ItemThread createItemThread(Socket socket, String request) {
    return new TCPItemThread(catalogService, socket, request);
  }

  @Override
  public NoSuchCommandThread createNoSuchCommandThread(Socket socket) {
    return new TCPNoSuchCommandThread(socket);
  }
}
