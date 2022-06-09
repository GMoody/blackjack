package io.gregory.model;

import io.gregory.model.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {

  public static final int BLACKJACK_SCORE = 21;
  private static final int DOUBLE_ACE_SCORE = 22;

  private final List<Card> cards = new ArrayList<>();
  private int score = 0;

  public void addCard(Card card) {
    cards.add(card);
    score += card.getRankValue();
  }

  public int getScore() {
    return score;
  }

  public boolean isBlackjackScore() {
    return cards.size() == 2 && score == BLACKJACK_SCORE;
  }

  public boolean hasDoubleAces() {
    return cards.size() == 2 && score == DOUBLE_ACE_SCORE;
  }

  public boolean isBusted() {
    return score > BLACKJACK_SCORE;
  }

  @Override
  public String toString() {
    return cards.stream()
      .map(Card::toString)
      .collect(Collectors.joining(", "));
  }
}
