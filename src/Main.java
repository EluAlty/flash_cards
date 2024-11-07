import application.observers.StudyProgressObserver;
import application.ports.in.DeckManagementInputPort;
import application.services.DeckService;
import application.strategies.DifficultyBasedStrategy;
import application.strategies.RandomStudyStrategy;
import application.strategies.StudyStrategy;
import application.usecases.CreateDeckUseCase;
import infrastructure.adapters.FileSystemAdapter;
import presentation.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        DeckManagementInputPort deckService = new DeckService(new FileSystemAdapter());
        StudyStrategy randomStrategy = new RandomStudyStrategy();
        StudyStrategy difficultyStrategy = new DifficultyBasedStrategy();
        StudyProgressObserver studyObserver = new StudyProgressObserver();
        CreateDeckUseCase createDeckUseCase = new CreateDeckUseCase(deckService);
        
        ConsoleUI consoleUI = new ConsoleUI(
            deckService, 
            randomStrategy,
            difficultyStrategy, 
            studyObserver, 
            createDeckUseCase
        );
        
        consoleUI.start();
    }
}