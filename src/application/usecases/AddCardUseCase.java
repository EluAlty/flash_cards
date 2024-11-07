package application.usecases;

import application.dto.CardDto;
import application.ports.in.DeckManagementInputPort;

public class AddCardUseCase {
    private final DeckManagementInputPort deckManagement;

    public AddCardUseCase(DeckManagementInputPort deckManagement) {
        this.deckManagement = deckManagement;
    }

    public void execute(String deckId, CardDto cardDto) {
        deckManagement.addCard(deckId, cardDto);
    }
}