package com.jamesorban.mygamestore.utils.converters;

import com.jamesorban.mygamestore.database.models.Author;
import com.jamesorban.mygamestore.modelFx.AuthorFx;

public class ConverterAuthor {

    public static Author convertToAuthor(AuthorFx authorFx){
        Author author = new Author();
        author.setId(authorFx.getId());
        author.setName(authorFx.getName());
        return author;
    }

    public static AuthorFx convertToAuthorFx(Author author){
        AuthorFx authorFx = new AuthorFx();
        authorFx.setId(author.getId());
        authorFx.setName(author.getName());
        return authorFx;
    }


}
