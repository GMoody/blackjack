package io.gregory.model.util;

import io.gregory.model.card.Card;
import io.gregory.model.card.CardRank;
import io.gregory.model.card.CardSuit;
import io.gregory.model.deck.Deck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeckLoader {

  private static final Logger logger = LoggerFactory.getLogger(DeckLoader.class);

  private static final String WHITESPACE_REGEX = "\\s+";


  public static Optional<Deck> loadFromArgs(String...args) {
    return args.length == 1 ? load(args[0]) : Optional.empty();
  }

  private static Optional<Deck> load(String path) {
    logger.info("Loading custom deck by filepath: {}", path);

    try (Stream<String> cardLine = Files.lines(Path.of(path))) {
      return cardLine.findFirst()
        .map(toDeck)
        .filter(Deck::isValid);
    }
    catch (Exception e) {
      logger.warn("Failed loading or parsing the custom deck");
      return Optional.empty();
    }
  }

  private static final Function<String, Card> toCard = cardString ->
    new Card(
      CardSuit.findBySuitSymbol(cardString.substring(0, 1)),
      CardRank.findByRankSymbol(cardString.substring(1))
    );

  private static final Function<String, Deck> toDeck = deckString ->
    Arrays.stream(deckString.replaceAll(WHITESPACE_REGEX, "").split(","))
      .map(toCard)
      .collect(Collectors.collectingAndThen(Collectors.toList(), Deck::new));

}
