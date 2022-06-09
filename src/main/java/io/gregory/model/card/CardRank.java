package io.gregory.model.card;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CardRank {

  TWO(2, "2"),
  THREE(3, "3"),
  FOUR(4, "4"),
  FIVE(5, "5"),
  SIX(6, "6"),
  SEVEN(7, "7"),
  EIGHT(8, "8"),
  NINE(9, "9"),
  TEN(10, "10"),
  JACK(10, "J"),
  QUEEN(10, "Q"),
  KING(10, "K"),
  ACE(11, "A");

  private final int value;
  private final String symbol;

  private static final Map<String, CardRank> mappedRankSymbols = Arrays.stream(CardRank.values())
    .collect(Collectors.toMap(s -> s.symbol, rank -> rank));

  CardRank(int rank, String symbol) {
    this.value = rank;
    this.symbol = symbol;
  }

  public static CardRank findByRankSymbol(String symbol) {
    return mappedRankSymbols.get(symbol);
  }

  public int getValue() {
    return value;
  }

  public String getSymbol() {
    return symbol;
  }
}
