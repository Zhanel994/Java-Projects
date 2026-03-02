import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();

        while(true) {
            deck.shuffle();
            Hand hand = new Hand();

            for (int i = 0; i < 5; i++) {
                hand.addCard(deck.chooseCard());
            }

            System.out.println("Your hand: ");
            hand.showHand();

            System.out.println("\nChoose the number of the card to change (with space): ");
            String input = scanner.nextLine();

            String[] parts = input.trim().split(" ");
            for (String part : parts) {
                if (part.equals("1") || part.equals("2") || part.equals("3") || part.equals("4") || part.equals("5")) {
                    int index = Integer.parseInt(part);
                    hand.replaceCard(index, deck.chooseCard());
                } else if (!part.isEmpty()) {
                    System.out.println("Wrong position: " + part);
                }
            }

            System.out.println("\nYour final hand: ");
            hand.showHand();
            System.out.println("\nWould you like to play again (Y/N)?  ");
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("N")) {
                System.out.println("Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}
