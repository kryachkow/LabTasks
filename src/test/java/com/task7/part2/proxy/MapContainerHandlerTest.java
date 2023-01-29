package com.task7.part2.proxy;

import com.task7.part2.model.PagedItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MapContainerHandlerTest {

  @ParameterizedTest
  @CsvSource({
      "note name, note material, 12, 23",
      "note2, note material2, 312312312, 0",
      "note6, note material6, 312332, 324"
  })
  void setGetTest(String name, String material, int price, int pageNumber) {
    PagedItem proxyItem = MapContainerHandler.createMapContainerProxy();

    proxyItem.setName(name);
    proxyItem.setMaterial(material);
    proxyItem.setPrice(price);
    proxyItem.setPageNumber(pageNumber);

    Assertions.assertEquals(name, proxyItem.getName());
    Assertions.assertEquals(material, proxyItem.getMaterial());
    Assertions.assertEquals(price, proxyItem.getPrice());
    Assertions.assertEquals(pageNumber, proxyItem.getPageNumber());
  }

}