package io.gregory;

import io.gregory.model.deck.Deck;
import io.gregory.model.player.Dealer;
import io.gregory.model.player.Player;
import io.gregory.model.util.DeckLoader;

public class Main {

  public static void main(String[] args) {
    var deck = DeckLoader.loadFromArgs(args).orElseGet(Deck::new);
    new Blackjack(deck, new Dealer("Dealer"), new Player("Sam")).start();
  }

}
