# Blackjack

## Game model
 - Create a single deck of playing cards
 - Two players (called Sam and the Dealer) who will play against each other
 - Each player is given two cards from the top of a shuffled deck of cards. Cards are given in the following order: [sam, dealer, sam, dealer]
 
## Game rules
 - Determine score of a hand [1] 
 - Check if either player has blackjack (21) with their initial hand and wins the game. Blackjack is an initial score of 21 with two cards: A + [10, J, Q, K]
 - Sam wins when both players start with Blackjack
 - The Dealer wins when both players start with 22 (A + A)
 - If neither player has blackjack then Sam can start drawing cards from the top of the deck
 - Sam should stop drawing cards from the deck if their total reaches 17 or higher •Sam has lost the game if their total is higher than 21
 - When Sam has stopped drawing cards the Dealer can start drawing cards from the top of the deck
 - The Dealer should stop drawing cards when their total is higher than Sam.
 - The Dealer has lost the game if their total is higher than 21
 - Determine which player wins the game
 
[1] Numbered cards are their point value. 
Jack, Queen and King count as 10 and Ace counts as 11.


## Input
The game should be able to read a file containing a deck of cards, taking the reference to the files as a command line argument, as a starting point. If no file is provided, a new shuffled deck should be initialised.

## Tools

 - Java >= 11
 - Gradle >= 5.0

## Testing

Pre-built jar is available in build/libs

    java -jar blackjack-1.0-all.jar /Users/user/folder/blackjack/src/test/resources/deck_full.txt
