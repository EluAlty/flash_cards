import application.observers.StudyProgressObserver;
import application.ports.in.DeckManagementInputPort;
import application.services.DeckService;
import application.strategies.RandomStudyStrategy;
import application.strategies.StudyStrategy;
import application.usecases.CreateDeckUseCase;
import infrastructure.repositories.InMemoryDeckRepository;
import presentation.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        DeckManagementInputPort deckService = new DeckService(InMemoryDeckRepository.getInstance());
        StudyStrategy studyStrategy = new RandomStudyStrategy();
        StudyProgressObserver studyObserver = new StudyProgressObserver();
        CreateDeckUseCase createDeckUseCase = new CreateDeckUseCase(deckService);
        ConsoleUI consoleUI = new ConsoleUI(deckService, studyStrategy, studyObserver, createDeckUseCase);
        consoleUI.start();
    }
}