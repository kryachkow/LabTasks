package com.store.service.impl;

import com.store.context.AppContext;
import com.store.model.Book;
import com.store.service.LocalizationService;
import com.store.service.Localized;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BookLocalizationServiceImpl implements LocalizationService {

  private static String BUNDLE_LINK = "localization";

  public BookLocalizationServiceImpl() {

  }

  @Override
  public Map<String, String> getLocalizedFields() {
    Locale locale = AppContext.getAppContext().getLocale();
    ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_LINK, locale);
    return Arrays.stream(Book.class.getDeclaredFields())
        .filter(f -> f.isAnnotationPresent(Localized.class))
        .collect(Collectors.toMap(Field::getName,
            field -> resourceBundle.getString(field.getAnnotation(Localized.class).value())));
  }


}
