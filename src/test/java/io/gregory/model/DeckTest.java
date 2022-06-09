package io.gregory.model;

import io.gregory.model.deck.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

  final int STANDARD_DECK_SIZE = 52;

  @Test
  @DisplayName("Generated deck contains 52 cards")
  void testGeneratedDeckIsFull() {
    assertThat(new Deck().getSize()).isEqualTo(STANDARD_DECK_SIZE);
  }

}