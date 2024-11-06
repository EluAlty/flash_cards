package domain.repositories;

import domain.entities.Deck;
import java.util.List;
import java.util.Optional;

public interface DeckRepository {
    void save(Deck deck);
    Optional<Deck> findById(String id);
    List<Deck> findAll();
    void delete(String id);
} 