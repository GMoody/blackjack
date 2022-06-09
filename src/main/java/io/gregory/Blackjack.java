package io.gregory;

import io.gregory.model.card.Card;
import io.gregory.model.deck.Deck;
import io.gregory.model.deck.DeckDrawingParameters;
import io.gregory.model.player.Dealer;
import io.gregory.model.player.Player;
import io.gregory.model.player.PlayerCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Supplier;


public class Blackjack {

  private final Logger logger = LoggerFactory.getLogger(Blackjack.class);

  private final Dealer dealer;
  private final Player player;
  private final Supplier<Card> cardsDealer;

  private PlayerCommon winner;


  public Blackjack(Deck deck, Dealer dealer, Player player) {
    this.dealer = dealer;
    this.player = player;
    cardsDealer = deck::dealCard;
  }

  public void start() {
    dealInitialCards();
    determineInitialWinner()
      .ifPresentOrElse(
        w -> printResult(),
        () -> {
          drawCards();
          endGame();
        });
  }

  public PlayerCommon getWinner() {
    return winner;
  }


  private void dealInitialCards() {
    player.takeCard(cardsDealer.get());
    dealer.takeCard(cardsDealer.get());
    player.takeCard(cardsDealer.get());
    dealer.takeCard(cardsDealer.get());
  }

  private Optional<PlayerCommon> determineInitialWinner() {
    var isEveryoneHasBlackjack = player.hasBlackJack() && dealer.hasBlackJack();
    var isEveryoneHasDoubleAces = player.hasDoubleAces() && dealer.hasDoubleAces();

    if (isEveryoneHasBlackjack || player.hasBlackJack()) {
      winner = player;
    }
    else if (isEveryoneHasDoubleAces || dealer.hasBlackJack()) {
      winner = dealer;
    }

    return Optional.ofNullable(winner);
  }

  private void drawCards() {
    player.drawCards(new DeckDrawingParameters(cardsDealer));
    dealer.drawCards(new DeckDrawingParameters(cardsDealer, player.getHandScore()));
  }

  private void endGame() {
    determineWinner();
    printResult();
  }

  private void determineWinner() {
    if (player.hasBusted()) {
      winner = dealer;
    }
    else if (dealer.hasBusted()) {
      winner = player;
    }
    else {
      winner = player.getHandScore() > dealer.getHandScore() ? player : dealer;
    }
  }

  private void printResult() {
    logger.info("\n{}\n{}\n{}", winner.getName(), player, dealer);
  }

}
