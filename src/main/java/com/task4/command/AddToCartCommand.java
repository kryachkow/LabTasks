package com.task4.command;


import com.task4.service.CatalogService;
import com.task4.service.CartPartService;
import com.task4.service.CartService;
import com.task4.model.Book;

import java.util.Scanner;

public class AddToCartCommand implements Command{

    private final CatalogService catalogService;
    private final CartPartService cartPartService;
    private final CartService cartService;

    private static final String CHOOSE_BOOK_MESSAGE ="Будь-ласка оберіть книгу за номером!";
    private static final String WRONG_ID_MESSAGE = "Цієї книги немає в каталозі, будь-ласка перевірте перевірте введений номер та спробуйте ще раз!";
    private static final String ENTER_QUANTITY_MESSAGE = "Введіть потрібну кількість кижок";
    private static final String WRONG_QUANTITY_MESSAGE = "Дуже дивна кількість, введість число більше за 0!";
    private static final String[] SUCCESSFUL_MESSAGE_PARTS =
            new String[]{"Книгу ", " у кількості ", " додано до корзини, гарних вам покупок"};
    private static final String BOOK_MESSAGE = "Книгу ";
    private static final String IN_QUANTITY_MESSAGE = " у кількості ";
    private static final String ADDED_TO_CART_MESSAGE = " додано до корзини, гарних вам покупок";

    public AddToCartCommand(CatalogService catalogService, CartPartService cartPartService, CartService cartService){
        this.catalogService = catalogService;
        this.cartPartService = cartPartService;
        this.cartService = cartService;
    }

    @Override
    public String doCommand() {
        Scanner bookChooser = new Scanner(System.in);
        Book book = null;
        while (book == null){
            System.out.println(CHOOSE_BOOK_MESSAGE);
            book = catalogService.getBook(bookChooser.nextLong());
            if(book == null){
                System.out.println(WRONG_ID_MESSAGE);
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

        cartPartService.addCartPart(book, quantity);
        cartService.addToCart(book, quantity);

        return BOOK_MESSAGE + book.getBookTitle() + IN_QUANTITY_MESSAGE + quantity + ADDED_TO_CART_MESSAGE;
    }
}
