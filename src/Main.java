// src/Main.java
import application.observers.StudyProgressObserver;
import application.ports.in.DeckManagementInputPort;
import application.services.DeckService;
import application.strategies.RandomStudyStrategy;
import application.strategies.StudyStrategy;
import infrastructure.repositories.InMemoryDeckRepository;
import presentation.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        DeckManagementInputPort deckService = new DeckService(InMemoryDeckRepository.getInstance());
        StudyStrategy studyStrategy = new RandomStudyStrategy();
        StudyProgressObserver studyObserver = new StudyProgressObserver();
        ConsoleUI consoleUI = new ConsoleUI(deckService, studyStrategy, studyObserver);
        consoleUI.start();
    }
}