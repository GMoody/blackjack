package io.gregory.model.player;

import io.gregory.model.Hand;
import io.gregory.model.card.Card;
import io.gregory.model.deck.DeckDrawingParameters;

public abstract class PlayerCommon {

  protected final String name;
  protected final Hand hand;

  protected PlayerCommon(String name) {
    this.name = name;
    this.hand = new Hand();
  }

  protected abstract boolean isAbleToDraw(DeckDrawingParameters parameters);

  public void drawCards(DeckDrawingParameters parameters) {
    while (isAbleToDraw(parameters)) {
      takeCard(parameters.getCardsDealer().get());
    }
  }

  public void takeCard(Card card) {
    hand.addCard(card);
  }

  public boolean hasBlackJack() {
    return hand.isBlackjackScore();
  }

  public boolean hasDoubleAces() {
    return hand.hasDoubleAces();
  }

  public boolean hasBusted() {
    return hand.isBusted();
  }

  public int getHandScore() {
    return hand.getScore();
  }

  public String getName() {
    return name.toLowerCase();
  }

  @Override
  public String toString() {
    return name.toLowerCase() + ": " + hand;
  }

}
