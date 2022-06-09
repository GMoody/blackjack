package io.gregory;

import io.gregory.model.deck.Deck;
import io.gregory.model.card.Card;
import io.gregory.model.card.CardRank;
import io.gregory.model.card.CardSuit;
import io.gregory.model.player.Dealer;
import io.gregory.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackTest {

  final Card HEARTS_TWO = new Card(CardSuit.HEARTS, CardRank.TWO);
  final Card HEARTS_FIVE = new Card(CardSuit.HEARTS, CardRank.FIVE);
  final Card HEARTS_QUEEN = new Card(CardSuit.HEARTS, CardRank.QUEEN);
  final Card HEARTS_KING = new Card(CardSuit.HEARTS, CardRank.KING);
  final Card HEARTS_ACE = new Card(CardSuit.HEARTS, CardRank.ACE);

  final Card CLUBS_FIVE = new Card(CardSuit.CLUBS, CardRank.FIVE);
  final Card CLUBS_QUEEN = new Card(CardSuit.CLUBS, CardRank.QUEEN);
  final Card CLUBS_KING = new Card(CardSuit.CLUBS, CardRank.KING);
  final Card CLUBS_ACE = new Card(CardSuit.CLUBS, CardRank.ACE);

  final Card SPADES_FIVE = new Card(CardSuit.SPADES, CardRank.FIVE);
  final Card SPADES_ACE = new Card(CardSuit.SPADES, CardRank.ACE);
  final Card DIAMONDS_ACE = new Card(CardSuit.DIAMONDS, CardRank.ACE);

  final int PLAYER_STANDING_HAND_SCORE = 17;

  Blackjack game;
  Player player;
  Dealer dealer;
  Deck deck;


  @BeforeEach
  void init() {
    player = new Player("sam");
    dealer = new Dealer("dealer");
  }

  @Test
  @DisplayName("Blackjack in both hands")
  void testBothPlayersHaveBlackjack() {
    startGameWithDeck(
      HEARTS_KING, CLUBS_KING,
      HEARTS_ACE, CLUBS_ACE
    );

    assertThat(player.hasBlackJack()).isTrue();
    assertThat(dealer.hasBlackJack()).isTrue();
    assertThat(game.getWinner()).isEqualTo(player);
  }

  @Test
  @DisplayName("Double aces in both hands")
  void testBothPlayersHaveDoubleAces() {
    startGameWithDeck(
      CLUBS_ACE, DIAMONDS_ACE,
      HEARTS_ACE, SPADES_ACE
    );

    assertThat(player.hasDoubleAces()).isTrue();
    assertThat(dealer.hasDoubleAces()).isTrue();
    assertThat(game.getWinner()).isEqualTo(dealer);
  }

  @Test
  @DisplayName("Player has blackjack")
  void testPlayerHasBlackjack() {
    startGameWithDeck(
      HEARTS_ACE, HEARTS_KING,
      CLUBS_KING, CLUBS_QUEEN
    );

    assertThat(player.hasBlackJack()).isTrue();
    assertThat(game.getWinner()).isEqualTo(player);
  }

  @Test
  @DisplayName("Dealer has blackjack")
  void testDealerHasBlackjack() {
    startGameWithDeck(
      HEARTS_KING, HEARTS_ACE,
      CLUBS_KING, CLUBS_QUEEN
    );

    assertThat(dealer.hasBlackJack()).isTrue();
    assertThat(game.getWinner()).isEqualTo(dealer);
  }

  @Test
  @DisplayName("Player stands on 17")
  void testPlayerStandsOn17() {
    startGameWithDeck(
      HEARTS_QUEEN, CLUBS_QUEEN,
      HEARTS_FIVE, CLUBS_KING,
      HEARTS_TWO, CLUBS_FIVE
    );

    assertThat(player.getHandScore()).isEqualTo(PLAYER_STANDING_HAND_SCORE);
    assertThat(game.getWinner()).isEqualTo(dealer);
  }

  @Test
  @DisplayName("Dealer hits over player's score")
  void testDealerHitsOverPlayerScore() {
    startGameWithDeck(
      HEARTS_QUEEN, CLUBS_QUEEN,
      HEARTS_FIVE, CLUBS_FIVE,
      HEARTS_TWO, CLUBS_KING
    );

    assertThat(dealer.getHandScore()).isGreaterThan(player.getHandScore());
    assertThat(game.getWinner()).isEqualTo(player);
  }

  @Test
  @DisplayName("Player's bust")
  void testPlayerBusts() {
    startGameWithDeck(
      HEARTS_QUEEN, CLUBS_QUEEN,
      HEARTS_FIVE, CLUBS_FIVE,
      HEARTS_ACE
    );

    assertThat(player.hasBusted()).isTrue();
    assertThat(game.getWinner()).isEqualTo(dealer);
  }

  @Test
  @DisplayName("Dealer's bust")
  void testDealerBusts() {
    startGameWithDeck(
      HEARTS_QUEEN, CLUBS_QUEEN,
      HEARTS_FIVE, CLUBS_FIVE,
      HEARTS_TWO, CLUBS_ACE
    );

    assertThat(dealer.hasBusted()).isTrue();
    assertThat(game.getWinner()).isEqualTo(player);
  }

  @Test
  @DisplayName("Dealer wins by score")
  void testDealerWinByScore() {
    startGameWithDeck(
      HEARTS_FIVE, CLUBS_FIVE,
      HEARTS_KING, CLUBS_QUEEN,
      HEARTS_TWO, SPADES_FIVE
    );

    assertThat(dealer.getHandScore()).isGreaterThan(player.getHandScore());
    assertThat(game.getWinner()).isEqualTo(dealer);
  }


  void startGameWithDeck(Card...cards) {
    deck = new Deck(List.of(cards));
    game = new Blackjack(deck, dealer, player);
    game.start();
  }

}
