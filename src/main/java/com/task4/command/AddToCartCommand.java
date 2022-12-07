package com.task4.command;


import com.task4.storage.CartStorage;
import com.task4.storage.CatalogStorage;
import com.task4.storage.LastAddedStorage;
import com.task4.model.Book;

import java.util.Scanner;

public class AddToCartCommand implements Command{

    private static final String CHOOSE_BOOK_MESSAGE ="Будь-ласка оберіть книгу за назвою!";
    private static final String WRONG_TITLE_MESSAGE = "Цієї книги немає в каталозі, будь-ласка перевірте перевірте введену назву та спробуйте ще раз!";
    private static final String ENTER_QUANTITY_MESSAGE = "Введіть потрібну кількість кижок";
    private static final String WRONG_QUANTITY_MESSAGE = "Дуже дивна кількість, введість число більше за 0!";
    private static final String[] SUCCESSFUL_MESSAGE_PARTS =  new String[]{"Книгу ", " у кількості ", " додано до корзини, гарних вам покупок"};

    @Override
    public String doCommand() {
        Scanner bookChooser = new Scanner(System.in);
        Book book = null;
        while (book == null){
            System.out.println(CHOOSE_BOOK_MESSAGE);
            book = CatalogStorage.getBook(bookChooser.nextLine().trim());
            if(book == null){
                System.out.println(WRONG_TITLE_MESSAGE);
            }
        }
        System.out.println(ENTER_QUANTITY_MESSAGE);
        int quantity = 0;
        while (quantity <= 0){
            quantity = bookChooser.nextInt();
            if (quantity <= 0 ){
                System.out.println(WRONG_QUANTITY_MESSAGE);
            }
        }
        LastAddedStorage.addCartPart(book, quantity);
        CartStorage.addToCart(book, quantity);
        return SUCCESSFUL_MESSAGE_PARTS[0] + book.getBookTitle() + SUCCESSFUL_MESSAGE_PARTS[1] + quantity + SUCCESSFUL_MESSAGE_PARTS[2];
    }
}
