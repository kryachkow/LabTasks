package com.task3.model;

import java.util.Objects;
//Task 3 part  2
public class ArticleHashSum {
    final String name;

    public ArticleHashSum(final String string){
        this.name = string;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleHashSum article = (ArticleHashSum) o;
        return Objects.equals(name, article.name);
    }

    @Override
    public int hashCode() {
        int i = 0;
        int result = 0;
        while (i < name.length() - 1 && i < 4) {
            result += name.charAt(i);
            i++;
        }
        return result;
    }


}
