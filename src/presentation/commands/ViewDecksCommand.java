package presentation.commands;

import application.dto.DeckDto;
import application.ports.in.DeckManagementInputPort;
import java.util.List;

public class ViewDecksCommand implements Command {
    private final DeckManagementInputPort deckService;

    public ViewDecksCommand(DeckManagementInputPort deckService) {
        this.deckService = deckService;
    }

    @Override
    public void execute() {
        List<DeckDto> decks = deckService.getAllDecks();
        if (decks.isEmpty()) {
            System.out.println("No decks available.");
        } else {
            System.out.println("Available decks:");
            for (DeckDto deck : decks) {
                System.out.println("ID: " + deck.getId());
                System.out.println("Name: " + deck.getName());
                System.out.println("Description: " + deck.getDescription());
                System.out.println("--------------------");
            }
        }
    }
}