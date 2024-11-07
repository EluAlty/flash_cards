package application.usecases;

import application.dto.DeckDto;
import application.ports.in.DeckManagementInputPort;

public class CreateDeckUseCase {
    private final DeckManagementInputPort deckManagementInputPort;

    public CreateDeckUseCase(DeckManagementInputPort deckManagementInputPort) {
        this.deckManagementInputPort = deckManagementInputPort;
    }

    public DeckDto execute(String name, String description) {
        return deckManagementInputPort.createDeck(name, description);
    }
}