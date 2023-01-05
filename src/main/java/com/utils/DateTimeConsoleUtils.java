package com.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class DateTimeConsoleUtils {

  private static final String DATE_FORMAT_MESSAGE = "dd/MM/yyyy";
  private static final String WRONG_FORMAT_MESSAGE = "Невірний формат дати, будьласка спробуйте ще раз!";
  private static final String ENTER_DATE_TIME_MESSAGE = "Будь-ласка введіть дату та час у форматі 'dd/MM/yyyy HH:mm'!";
  private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

  public static LocalDate getDate(String message) {
    LocalDate date = null;
    Scanner dateGetter = new Scanner(System.in);
    DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern(DATE_FORMAT_MESSAGE,
        Locale.US);
    while (date == null) {
      System.out.println(message);
      try {
        date = LocalDate.from(formatterWithTime.parse(dateGetter.nextLine().trim()));
      } catch (DateTimeParseException ignored) {
        System.out.println(WRONG_FORMAT_MESSAGE);
      }
    }
    return date;
  }


  public static LocalDateTime getDateTime() {
    Scanner dateGetter = new Scanner(System.in);
    DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.US);
    LocalDateTime dateTime = null;
    while (dateTime == null) {
      System.out.println(ENTER_DATE_TIME_MESSAGE);
      try {
        dateTime = LocalDateTime.from(formatterWithTime.parse(dateGetter.nextLine().trim()));
      } catch (DateTimeParseException ignored) {
        System.out.println(WRONG_FORMAT_MESSAGE);
      }
    }
    return dateTime;
  }
}
