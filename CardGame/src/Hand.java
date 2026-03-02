public class Hand {
    private final Card[] cards;
    private int count;

    public Hand() {
        cards = new Card[5];
        count = 0;
    }

    public void addCard(Card card) {
        if (count < 5) {
            cards[count++] = card;
        } else {
            System.out.println("You already have 5 cards!");
        }
    }

    public void replaceCard(int position, Card newCard) {
        if (position < 1 || position > 5) {
            System.out.println("You chose wrong number. Choose from 1 to 5: ");
        } else {
            cards[position - 1] = newCard;
        }
    }

    public void showHand() {
        for (int i = 0; i < count; i++) {
            System.out.print(" " + (i + 1) + " |  ");
        }
        System.out.println();

        for (int i = 0; i < count; i++) {
            System.out.print(cards[i] + " |  ");
        }
        System.out.println();
    }

    public Card getCard(int index) {
        if (index >= 0 && index < count) {
            return cards[index];
        }
        return null;
    }

    public int getCount() {
        return count;
    }
}
