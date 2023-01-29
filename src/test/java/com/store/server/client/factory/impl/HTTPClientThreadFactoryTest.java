package com.store.server.client.factory.impl;

import com.store.server.ServerThread;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.Arguments;

class HTTPClientThreadFactoryTest extends AbstractServerTests {


  @BeforeAll
  static void init() throws IOException {
    port = 4444;
    ServerThread serverThread = new ServerThread(port, new HTTPClientThreadFactory(catalogService));
    serverThread.start();

  }

  @Override
  Stream<Arguments> getNoSuchCommandTestArguments() {
    return Stream.of(
        Arguments.of("get something",
            """
                HTTP/1.1 404 Not Found
                Content-Type: text/plain
                Content-Length 18

                RESOURCE NOT FOUND"""),
        Arguments.of("get dasdas",
            """
                HTTP/1.1 404 Not Found
                Content-Type: text/plain
                Content-Length 18

                RESOURCE NOT FOUND"""),
        Arguments.of("get !count",
            """
                HTTP/1.1 404 Not Found
                Content-Type: text/plain
                Content-Length 18

                RESOURCE NOT FOUND""")
    );
  }

  @Override
  Stream<Arguments> getCountCountCommandTestArguments() {
    return Stream.of(
        Arguments.of("GET, URL: /shop/count",
            """
                HTTP/1.1 200 OK
                Content-Type: application/json
                Content-Length 10

                {count: 4}""",
            4),
        Arguments.of("GET, URL: /shop/count",
            """
                HTTP/1.1 200 OK
                Content-Type: application/json
                Content-Length 10

                {count: 0}""",
            0),
        Arguments.of("GET, URL: /shop/count",
            """
                HTTP/1.1 200 OK
                Content-Type: application/json
                Content-Length 11

                {count: 30}""",
            30)
    );
  }

  @Override
  Stream<Arguments> getInfoItemTestArguments() {
    return Stream.of(
        Arguments.of("GET, URL: /shop/item?get_info=3",
            """
                HTTP/1.1 200 OK
                Content-Type: application/json
                Content-Length 25

                {name: title, price: 400}""",
            3L, "title", 400),
        Arguments.of("GET, URL: /shop/item?get_info=4",
            """
                HTTP/1.1 200 OK
                Content-Type: application/json
                Content-Length 26

                {name: title3, price: 500}""",
            4L, "title3", 500),
        Arguments.of("GET, URL: /shop/item?get_info=32",
            """
                HTTP/1.1 200 OK
                Content-Type: application/json
                Content-Length 27

                {name: title32, price: 800}""",
            32L, "title32", 800)
    );
  }

  @Override
  Stream<Arguments> getNoSuchItemArguments() {
    return Stream.of(
        Arguments.of("GET, URL: /shop/item?get_info=3",
            """
                HTTP/1.1 204 No Content
                Content-Type: text/plain
                Content-Length 14

                BOOK NOT FOUND"""),
        Arguments.of("GET, URL: /shop/item?get_info=600",
            """
                HTTP/1.1 204 No Content
                Content-Type: text/plain
                Content-Length 14

                BOOK NOT FOUND"""),
        Arguments.of("GET, URL: /shop/item?get_info=7",
            """
                HTTP/1.1 204 No Content
                Content-Type: text/plain
                Content-Length 14

                BOOK NOT FOUND""")
    );
  }
}