package io.gregory.model.deck;

import io.gregory.model.card.Card;

import java.util.function.Supplier;

public class DeckDrawingParameters {

  private final Supplier<Card> cardsDealer;
  private int playerHandScore;

  public DeckDrawingParameters(Supplier<Card> cardsDealer) {
    this.cardsDealer = cardsDealer;
  }

  public DeckDrawingParameters(Supplier<Card> cardsDealer, int playerHandScore) {
    this.cardsDealer = cardsDealer;
    this.playerHandScore = playerHandScore;
  }

  public Supplier<Card> getCardsDealer() {
    return cardsDealer;
  }

  public int getPlayerHandScore() {
    return playerHandScore;
  }
}
