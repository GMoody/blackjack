package io.gregory.model.player;

import io.gregory.model.Hand;
import io.gregory.model.deck.DeckDrawingParameters;

public class Dealer extends PlayerCommon {

  public Dealer(String name) {
    super(name);
  }

  @Override
  protected boolean isAbleToDraw(DeckDrawingParameters parameters) {
    var playerHandScore = parameters.getPlayerHandScore();
    return playerHandScore < Hand.BLACKJACK_SCORE &&
      getHandScore() <= playerHandScore;
  }

}
