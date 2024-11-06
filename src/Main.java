import application.observers.StudyObserver;
import application.observers.StudyProgressObserver;
import application.services.DeckService;
import application.strategies.RandomStudyStrategy;
import application.strategies.StudyStrategy;
import domain.repositories.InMemoryDeckRepository;
import presentation.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        DeckService deckService = new DeckService(InMemoryDeckRepository.getInstance());
        StudyStrategy studyStrategy = new RandomStudyStrategy();
        StudyObserver studyObserver = new StudyProgressObserver();
        ConsoleUI consoleUI = new ConsoleUI(deckService, studyStrategy, studyObserver);
        consoleUI.start();
    }
} 