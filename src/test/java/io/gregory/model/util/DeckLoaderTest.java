package io.gregory.model.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class DeckLoaderTest {

  private final String INCOMPLETE_DECK_ABSOLUTE_PATH = getResourcePath("deck_incomplete.txt");
  private final String IRRATIONAL_DECK_ABSOLUTE_PATH = getResourcePath("deck_irrational.txt");
  private final String INVALID_DECK_ABSOLUTE_PATH = getResourcePath("deck_invalid.txt");
  private final String VALID_DECK_ABSOLUTE_PATH = getResourcePath("deck_full.txt");


  @Test
  @DisplayName("Valid decks are loaded")
  void testLoadingValidDecks() {
    assertThat(DeckLoader.loadFromArgs(VALID_DECK_ABSOLUTE_PATH)).isPresent();
    assertThat(DeckLoader.loadFromArgs(INCOMPLETE_DECK_ABSOLUTE_PATH)).isPresent();
  }

  @Test
  @DisplayName("Invalid decks are filtered out")
  void testLoadingInvalidDecks() {
    assertThat(DeckLoader.loadFromArgs(INVALID_DECK_ABSOLUTE_PATH)).isEmpty();
    assertThat(DeckLoader.loadFromArgs(IRRATIONAL_DECK_ABSOLUTE_PATH)).isEmpty();
  }

  @SuppressWarnings("ConstantConditions")
  private String getResourcePath(String resource) {
    return this.getClass().getClassLoader().getResource(resource).getPath();
  }
}