package com.task7.part2.proxy;

import com.task7.part2.model.PagedItem;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.naming.OperationNotSupportedException;

public class ImmutableObjectHandler implements InvocationHandler {

  private final PagedItem pagedItem;

  private ImmutableObjectHandler(PagedItem pagedItem) {
    this.pagedItem = pagedItem;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.getName().startsWith("set")) {
      throw new OperationNotSupportedException("This object couldn't be modified");
    }
    return method.invoke(pagedItem, args);
  }

  public static PagedItem createImmutableProxy(PagedItem pagedItem) {
    return (PagedItem) Proxy.newProxyInstance(PagedItem.class.getClassLoader(),
        new Class[]{PagedItem.class},
        new ImmutableObjectHandler(pagedItem));
  }
}
