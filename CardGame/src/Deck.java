import java.util.Random;

public class Deck {
    private final Card[] cards;
    private int currentIndex;

    public Deck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"\u2660", "\u2665", "\u2666", "\u2663"};

        cards = new Card[52];
        int index = 0;

        for (String suit : suits) {
            for (String rank : ranks) {
                cards[index++] = new Card(rank, suit);
            }
        }

        currentIndex = 0;
    }

    public void shuffle() {
        Random random = new Random();

        for (int i = 0; i < cards.length; i++) {
            int j = random.nextInt(cards.length);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }

        currentIndex = 0;
    }

    public Card chooseCard() {
        if (currentIndex >= cards.length) {
            System.out.println("The deck is empty");
            return null;
        }

        return cards[currentIndex++];
    }

    public int remainingCards() {
        return cards.length - currentIndex;
    }
}
