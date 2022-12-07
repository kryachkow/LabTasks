package com.task4.command;

import com.task4.storage.LastAddedStorage;
import com.task4.model.CartPart;

import java.util.List;
import java.util.Map;

public class CheckLastCommand implements Command{

    private static final String[] LAST_ADDS_MESSAGE_PARTS = new String[]{"Порядковий номер додавання ", " | Книга ", " | у кількості "};
    @Override
    public String doCommand() {
        List< Map.Entry<Integer, CartPart>> list= LastAddedStorage.getFiveLastEntries();
        StringBuilder builder = new StringBuilder();
        list.forEach(e -> builder.append(LAST_ADDS_MESSAGE_PARTS[0])
                .append(e.getKey())
                .append(LAST_ADDS_MESSAGE_PARTS[1])
                .append(e.getValue().getBook().getBookTitle())
                .append(LAST_ADDS_MESSAGE_PARTS[2])
                .append(e.getValue().getQuantity())
                .append("\n"));
        return builder.toString();
    }
}
