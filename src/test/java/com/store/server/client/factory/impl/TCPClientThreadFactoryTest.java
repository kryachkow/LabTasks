package com.store.server.client.factory.impl;

import com.store.server.ServerThread;
import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.Arguments;

class TCPClientThreadFactoryTest extends AbstractServerTests {


  @BeforeAll
  static void init() throws IOException {
    port = 4000;
    ServerThread serverThread = new ServerThread(port, new TCPClientThreadFactory(catalogService));
    serverThread.start();

  }

  @Override
  Stream<Arguments> getNoSuchCommandTestArguments() {
    return Stream.of(
        Arguments.of("get something", "NO_SUCH_COMMAND"),
        Arguments.of("get dasdas", "NO_SUCH_COMMAND"),
        Arguments.of("get !count", "NO_SUCH_COMMAND")
    );
  }

  @Override
  Stream<Arguments> getCountCountCommandTestArguments() {
    return Stream.of(
        Arguments.of("get count", "CONT_RESULT 4", 4),
        Arguments.of("get count", "CONT_RESULT 0", 0),
        Arguments.of("get count", "CONT_RESULT 30", 30)
    );
  }

  @Override
  Stream<Arguments> getInfoItemTestArguments() {
    return Stream.of(
        Arguments.of("get item=3", "title | 400", 3L, "title", 400),
        Arguments.of("get item=4", "title3 | 500", 4L, "title3", 500),
        Arguments.of("get item=32", "title32 | 800", 32L, "title32", 800)
    );
  }

  @Override
  Stream<Arguments> getNoSuchItemArguments() {
    return Stream.of(
        Arguments.of("get item=3", "NO_SUCH_ITEM"),
        Arguments.of("get item=600", "NO_SUCH_ITEM"),
        Arguments.of("get item=124123", "NO_SUCH_ITEM")
    );
  }
}

