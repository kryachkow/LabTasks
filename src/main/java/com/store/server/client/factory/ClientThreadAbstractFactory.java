package com.store.server.client.factory;

import com.store.server.client.thread.CountThread;
import com.store.server.client.thread.ItemThread;
import com.store.server.client.thread.NoSuchCommandThread;
import com.store.service.CatalogService;
import java.net.Socket;
import java.util.Map;
import java.util.function.BiFunction;

public interface ClientThreadAbstractFactory {

  Thread getClientTread(Socket socket);
  CountThread createCountThread(Socket socket);
  ItemThread createItemThread(Socket socket, String request);
  NoSuchCommandThread createNoSuchCommandThread(Socket socket);
}
