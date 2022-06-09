package io.gregory.model.player;

import io.gregory.model.deck.DeckDrawingParameters;

public class Player extends PlayerCommon {

  private static final int PLAYER_STANDING_HAND_SCORE = 17;

  public Player(String name) {
    super(name);
  }

  @Override
  protected boolean isAbleToDraw(DeckDrawingParameters parameters) {
    return getHandScore() < PLAYER_STANDING_HAND_SCORE;
  }

}
