![build](https://github.com/mekhnin/xmlParsing/actions/workflows/maven.yml/badge.svg)
## Сборка и запуск

- Компиляция кода и его исполнение производится c использованием **Java 11**.

- Сборка осуществляется при помощи **Apache Maven** командой
```shell
mvn clean package
```
Все необходимые зависимости будут упакованы в `fat jar` файл `task.jar`.

- Для запуска приложения выполните
```shell
cd ./target
java -jar task.jar pathToAS_ADDR_OBJ.XML pathToAS_ADM_HIERARCHY.XML 2010-01-01 "1422396, 1450759, 1449192, 1451562" проезд
```

- Вы восхитительны!
