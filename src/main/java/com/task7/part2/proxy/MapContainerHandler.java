package com.task7.part2.proxy;

import com.task7.part2.model.PagedItem;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class MapContainerHandler implements InvocationHandler {

  private Map<String, Object> variablesMap;

  private MapContainerHandler() {
    variablesMap = new HashMap<>();
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String methodName = method.getName();
    if (!methodName.contains("get") && !methodName.contains("set")) {
      return method.invoke(variablesMap, args);
    }
    if (methodName.contains("get")) {
      return method.getReturnType().equals(String.class)
          ? variablesMap.getOrDefault(getVariableName(methodName), null)
          : variablesMap.getOrDefault(getVariableName(methodName), 0);
    }
    variablesMap.put(getVariableName(methodName), args[0]);
    return null;
  }


  private String getVariableName(String methodName) {
    char[] chars = methodName.substring(3).toCharArray();
    chars[0] = Character.toLowerCase(chars[0]);
    return new String(chars);
  }

  public static PagedItem createMapContainerProxy() {
    return (PagedItem) Proxy.newProxyInstance(PagedItem.class.getClassLoader(),
        new Class[]{PagedItem.class},
        new MapContainerHandler());
  }
}
