package com.store.command;

import com.store.context.AppContext;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {

  private static final String RESPOND_MESSAGE = "Локалізацію було змінено на ";

  @Override
  public String doCommand() {
    Locale locale =
        AppContext.getAppContext().getLocale().getLanguage().equals("uk")
            ? new Locale("en")
            : new Locale("uk");
    AppContext.getAppContext().setLocale(locale);
    return RESPOND_MESSAGE + locale.getLanguage();
  }
}
