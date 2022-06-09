package io.gregory.model.card;

import java.util.Objects;

public class Card {

  private final CardSuit cardSuit;
  private final CardRank cardRank;

  public Card(CardSuit suit, CardRank rank) {
    cardSuit = suit;
    cardRank = rank;
  }

  public int getRankValue() {
    return cardRank.getValue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(cardSuit, cardRank);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    var that = (Card) o;
    return Objects.equals(this.cardSuit, that.cardSuit) &&
      Objects.equals(this.cardRank, that.cardRank);
  }

  @Override
  public String toString() {
    return cardSuit.getSuit() + cardRank.getSymbol();
  }
}
