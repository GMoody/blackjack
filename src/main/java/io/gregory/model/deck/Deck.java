package io.gregory.model.deck;

import io.gregory.model.card.Card;
import io.gregory.model.card.CardRank;
import io.gregory.model.card.CardSuit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Deck {

  private static final Logger logger = LoggerFactory.getLogger(Deck.class);

  private static final int MINIMUM_DECK_SIZE = 4;

  private final LinkedList<Card> cards = new LinkedList<>();


  public Deck() {
    logger.info("Generating a brand new deck and shuffling");
    fillDeck();
    shuffle();
  }

  public Deck(List<Card> cards) {
    this.cards.addAll(cards);
  }

  public int getSize() {
    return cards.size();
  }

  public Card dealCard() {
    return cards.pop();
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }

  private void fillDeck() {
    for (CardSuit suit : CardSuit.values()) {
      for (CardRank rank : CardRank.values()) {
        cards.push(new Card(suit, rank));
      }
    }
  }

  public boolean isValid() {
    return new HashSet<>(cards).size() >= MINIMUM_DECK_SIZE;
  }

}
