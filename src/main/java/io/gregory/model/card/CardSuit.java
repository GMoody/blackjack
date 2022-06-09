package io.gregory.model.card;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CardSuit {

  CLUBS("C"),
  DIAMONDS("D"),
  HEARTS("H"),
  SPADES("S");

  private final String suit;

  private static final Map<String, CardSuit> mappedValues = Arrays.stream(CardSuit.values())
    .collect(Collectors.toMap(s -> s.suit, e -> e));

  CardSuit(String suit) {
    this.suit = suit;
  }

  public static CardSuit findBySuitSymbol(String symbol) {
    return mappedValues.get(symbol);
  }

  public String getSuit() {
    return suit;
  }
}
