package application.usecases;

import application.ports.out.DeckPersistenceOutputPort;

public class RemoveDeckUseCase {
    private final DeckPersistenceOutputPort deckPersistence;

    public RemoveDeckUseCase(DeckPersistenceOutputPort deckPersistence) {
        this.deckPersistence = deckPersistence;
    }

    public void execute(String deckId) {
        deckPersistence.deleteDeck(deckId);
    }
}