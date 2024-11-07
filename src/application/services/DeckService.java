package application.services;

import application.dto.CardDto;
import application.dto.DeckDto;
import application.factories.CardFactory;
import application.factories.TextCardFactory;
import application.factories.ImageCardFactory;
import application.ports.in.DeckManagementInputPort;
import application.ports.out.DeckPersistenceOutputPort;
import domain.entities.Card;
import domain.entities.Deck;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DeckService implements DeckManagementInputPort {
    private final DeckPersistenceOutputPort deckPersistence;
    private final CardFactory textCardFactory;
    private final CardFactory imageCardFactory;

    public DeckService(DeckPersistenceOutputPort deckPersistence) {
        this.deckPersistence = deckPersistence;
        this.textCardFactory = new TextCardFactory();
        this.imageCardFactory = new ImageCardFactory();
    }

    @Override
    public DeckDto createDeck(String name, String description) {
        Deck deck = new Deck(UUID.randomUUID().toString(), name, description);
        deckPersistence.saveDeck(deck);
        return new DeckDto(deck.getId(), deck.getName(), deck.getDescription(), null);
    }

    @Override
    public void addCard(String deckId, CardDto cardDto) {
        Deck deck = deckPersistence.loadDeck(deckId);
        if (deck != null) {
            Card card = cardDto.getType().equals("IMAGE")
                    ? imageCardFactory.createCard(UUID.randomUUID().toString(), cardDto.getQuestion(), cardDto.getAnswer())
                    : textCardFactory.createCard(UUID.randomUUID().toString(), cardDto.getQuestion(), cardDto.getAnswer());
            deck.addCard(card);
            deckPersistence.saveDeck(deck);
        }
    }

    @Override
    public void removeCard(String deckId, String cardId) {
        Deck deck = deckPersistence.loadDeck(deckId);
        if (deck != null) {
            deck.removeCard(cardId);
            deckPersistence.saveDeck(deck);
        }
    }

    @Override
    public DeckDto getDeck(String deckId) {
        Deck deck = deckPersistence.loadDeck(deckId);
        if (deck != null) {
            List<CardDto> cardDtos = deck.getCards().stream()
                    .map(card -> new CardDto(card.getId(), card.getQuestion(), card.getAnswer(), card.getType()))
                    .collect(Collectors.toList());
            return new DeckDto(deck.getId(), deck.getName(), deck.getDescription(), cardDtos);
        }
        return null;
    }

    @Override
    public List<DeckDto> getAllDecks() {
        return deckPersistence.getAllDecks().stream()
                .map(deck -> new DeckDto(deck.getId(), deck.getName(), deck.getDescription(), null))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDeck(String deckId) {
        deckPersistence.deleteDeck(deckId);
    }

    @Override
    public void studyDeck(String deckId) {
        // This method will be implemented in the StudyDeckCommand
    }
}