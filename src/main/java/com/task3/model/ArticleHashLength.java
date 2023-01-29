package com.task3.model;

import java.util.Objects;
//Task 3 part  2
public class ArticleHashLength {
    final String name;

    public ArticleHashLength(final String string){
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
        ArticleHashLength article = (ArticleHashLength) o;
        return Objects.equals(name, article.name);
    }

    @Override
    public int hashCode() {
        return name.length();
    }
}
