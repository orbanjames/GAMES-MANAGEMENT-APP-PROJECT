package com.jamesorban.gamesmanagementapp.utils.converters;

import com.jamesorban.gamesmanagementapp.database.models.Game;
import com.jamesorban.gamesmanagementapp.modelFx.GameFx;
import com.jamesorban.gamesmanagementapp.utils.Utils;

public class ConverterGame {

    public static Game convertToGame(GameFx gameFx){
        Game game = new Game();
        game.setId(gameFx.getId());
        game.setTitle(gameFx.getTitle());
        game.setDescription(gameFx.getDescription());
        game.setRating(gameFx.getRating());
        game.setReleaseDate(Utils.convertToDate(gameFx.getReleaseDate()));
        game.setAddedDate(Utils.convertToDate(gameFx.getAddedDate()));
        return game;
    }

    public static GameFx convertToGameFx(Game game) {
        GameFx gameFx = new GameFx();
        gameFx.setId(game.getId());
        gameFx.setTitle(game.getTitle());
        gameFx.setDescription(game.getDescription());
        gameFx.setRating(game.getRating());
        gameFx.setReleaseDate(Utils.convertToLocalDate(game.getReleaseDate()));
        gameFx.setAuthorFx(ConverterAuthor.convertToAuthorFx(game.getAuthor()));
        gameFx.setCategoryFx(ConverterCategory.covertToCategoryFx(game.getCategory()));
        return gameFx;
    }
}
