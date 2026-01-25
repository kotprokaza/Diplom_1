# Diplom_1 — Юнит-тесты для Stellar Burgers

## Описание проекта
Юнит-тесты для класса `Burger` приложения Stellar Burgers. Дипломный проект Яндекс.Практикума.

## Покрытие кода
- **Burger:** 100% ✅
- **Database:** 100% ✅
- **Ingredient:** 100% ✅
- **Bun:** 100% ✅
- **IngredientType:** 100% ✅
- **Praktikum:** 100% ✅
- **Итого:** 100% ✅

## Технологии
- Java 11
- JUnit 4
- Mockito 4.8.0
- JaCoCo 0.8.11
- Maven

## Запуск тестов
```bash
mvn clean test
mvn jacoco:report
open target/site/jacoco/index.html
