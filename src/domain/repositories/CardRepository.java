package domain.repositories;

import domain.entities.Card;
import java.util.List;
import java.util.Optional;

public interface CardRepository {
    void save(Card card);
    Optional<Card> findById(String id);
    List<Card> findAll();
    void delete(String id);
} 