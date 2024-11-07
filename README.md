# Система изучения флеш-карточек

## Обзор
Эта система представляет собой приложение для создания и изучения флеш-карточек, разработанное с использованием Java и различных паттернов проектирования. Система позволяет пользователям создавать колоды карточек, добавлять и удалять карточки, а также изучать их с использованием различных стратегий.

## Документация
- [Обзор архитектуры](overview.md)
- [Руководство пользователя](docs/user_guide.md)
- [Ограничения системы](limitations.md)

## Паттерны проектирования
- [Factory Method](docs/design_patterns/factory_method.md)
- [Abstract Factory](docs/design_patterns/abstract_factory.md)
- [Decorator](docs/design_patterns/decorator.md)
- [Observer](docs/design_patterns/observer.md)
- [Strategy](docs/design_patterns/strategy.md)
- [Command](docs/design_patterns/command.md)

## Запуск проекта
1. Убедитесь, что у вас установлена Java Development Kit (JDK) версии 8 или выше.
2. Склонируйте репозиторий: git clone [https://github.com/EluAlty/flash_cards.git]
3. Перейдите в директорию проекта: cd flash_cards_system
4. Скомпилируйте проект: javac -d out src/**/*.java
5. Запустите приложение: java -cp out Main

## Структура проекта
src/
├── application/
│   ├── dto/
│   ├── factories/
│   ├── observers/
│   ├── ports/
│   ├── services/
│   ├── strategies/
│   └── usecases/
├── domain/
│   └── entities/
├── infrastructure/
│   ├── adapters/
│   └── repositories/
├── presentation/
│   ├── commands/
│   └── ui/
└── Main.java

## Вклад в проект
Если вы хотите внести свой вклад в проект, пожалуйста, создайте pull request с описанием предлагаемых изменений.

## Лицензия
Этот проект лицензирован под MIT License - см. файл [LICENSE](LICENSE) для деталей.