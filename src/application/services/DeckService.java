package application.services;

import application.dto.CardDto;
import application.dto.DeckDto;
import application.ports.in.DeckManagementInputPort;
import application.ports.out.DeckPersistenceOutputPort;
import domain.entities.Card;
import domain.entities.Deck;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class DeckService implements DeckManagementInputPort {
    private final DeckPersistenceOutputPort deckRepository;

    public DeckService(DeckPersistenceOutputPort deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Override
    public DeckDto createDeck(String name, String description) {
        Deck deck = new Deck.Builder()
                .name(name)
                .description(description)
                .build();
        deckRepository.save(deck);
        return convertToDeckDto(deck);
    }

    @Override
    public void addCardToDeck(String deckId, CardDto cardDto) {
        deckRepository.findById(deckId).ifPresent(deck -> {
            Card card = new Card.Builder()
                    .id(UUID.randomUUID().toString())
                    .question(cardDto.getQuestion())
                    .answer(cardDto.getAnswer())
                    .difficulty(cardDto.getDifficulty())
                    .build();
            deck.addCard(card);
            deckRepository.save(deck);
        });
    }

    @Override
    public void removeCardFromDeck(String deckId, String cardId) {
        deckRepository.findById(deckId).ifPresent(deck -> {
            deck.removeCard(cardId);
            deckRepository.save(deck);
        });
    }

    @Override
    public void deleteDeck(String deckId) {
        deckRepository.delete(deckId);
    }

    @Override
    public List<DeckDto> getAllDecks() {
        return deckRepository.findAll().stream()
                .map(this::convertToDeckDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeckDto getDeckById(String id) {
        Optional<Deck> deck = deckRepository.findById(id);
        return deck.map(this::convertToDeckDto).orElse(null);
    }

    private DeckDto convertToDeckDto(Deck deck) {
        List<CardDto> cardDtos = deck.getCards().stream()
                .map(this::convertToCardDto)
                .collect(Collectors.toList());
        return new DeckDto(deck.getId(), deck.getName(), deck.getDescription(), cardDtos);
    }

    private CardDto convertToCardDto(Card card) {
        return new CardDto(card.getId(), card.getQuestion(), card.getAnswer(), card.getDifficulty());
    }
}