package com.nc.task1_2.view.impl;

import com.nc.task1_2.controller.event.Event;
import com.nc.task1_2.controller.event.EventType;
import com.nc.task1_2.controller.task.Task;
import com.nc.task1_2.controller.task.TaskType;
import com.nc.task1_2.view.View;

import java.util.Scanner;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Реализация интерфейса View для консоли
 */
public class ConsoleView implements View {
    /**
     * Значения команд в консоли
     */
    private final String COMMAND_SCAN = "scan";
    private final String COMMAND_MOVE = "mv";
    private final String COMMAND_COPY = "cp";
    private final String COMMAND_REMOVE = "rm";
    private final String COMMAND_PRINT = "print";
    private final String COMMAND_EXIT = "exit";

    /**
     * Записать данные во view
     * @param event - данные на вывод
     */
    @Override
    public void write(Event event) {
        // Инициализируем результирующую строку
        String outString;

        // Парсим параметры события
        String eventString = parseEventType(event.getEventType());
        String taskString = parseTaskType(event.getTaskType());
        String messageString = parseMessage(event.getMessage());

        // Формируем итоговый вывод. Для определенных случаев не выводим команду или событие (для красоты)
        if (event.getTaskType() == TaskType.EMPTY
                || event.getEventType() == EventType.OUTPUT) {
            outString = eventString + " " + messageString;
        } else if (event.getTaskType() == TaskType.EXIT) {
            outString = taskString;
        } else {
            outString = eventString + " " + taskString + " " + messageString;
        }

        // Выводим результирующую строку в консоль
        System.out.println(outString);
    }

    /**
     * Парсинг типа события
     * @param eventType - тип события
     * @return - результирующая строка для типа события
     */
    private String parseEventType(EventType eventType) {
        // Инициализируем результирующую строку
        String parseString = "";

        // Парсим тип события
        switch (eventType) {
            case TASK_START:
                parseString = "start";
                break;
            case TASK_DONE:
                parseString = "complete";
                break;
            case ERROR:
                parseString = "ERROR!!!";
                break;
            case LOG:
                parseString = "info.";
                break;
            case OUTPUT:
                parseString = "->";
                break;
        }

        // Вовзращаем результирующую строку
        return parseString;
    }

    /**
     * Парсинг типа команды
     * @param taskType - тип команды
     * @return - результирующая строка для типа команды
     */
    private String parseTaskType(TaskType taskType) {
        // Инициализируем результирующую строку
        String parseString = "";

        switch (taskType) {
            case SCAN:
                parseString = "command scan.";
                break;
            case MOVE:
                parseString = "command move.";
                break;
            case COPY:
                parseString = "command copy.";
                break;
            case REMOVE:
                parseString = "command remove.";
                break;
            case PRINT:
                parseString = "command print.";
                break;
            case EXIT:
                parseString = "command exit. close the program";
                break;
        }

        // Вовзращаем результирующую строку
        return parseString;
    }

    /**
     * Парсинг сообщения
     * @param message - сообщение
     * @return - результирующая строка сообщения
     */
    private String parseMessage(String message) {
        return (message == null ? "" : message);
    }

    /**
     * Обработка события
     * @param event - событие
     */
    @Override
    public void handleEvent(Event event) {
        // Обрабатываем событие на вывод
        write(event);
    }

    /**
     * Получить данные из view
     * @return - команда для обработки
     */
    @Override
    public Task read() {
        Task task;
        // Выполняем считывание данных с консоли до тех пор, пока не получим корректную команду
        do {
            // Получаем входящую строку из консоли
            Scanner scanner = new Scanner(System.in);
            String commandLine = scanner.nextLine();

            // Парсим входную строку, возвращаем команду
            task = parse(commandLine);
        } while (task.getTaskType() == TaskType.EMPTY);

        // Возвращаем полученную команду
        return task;
    }

    /**
     * Парсинг входной строки команды
     * @param commandLine - входная строка
     * @return команда
     */
    private Task parse(String commandLine) {
        try {
            // Делим входную строку на литералы по пробелам
            String[] commandSplit = commandLine.split(" ");

            // Определяем тип команды
            String commandType = commandSplit[0];

            // Инициализируем параметры в зависимости от типа команды
            switch (commandType) {
                case COMMAND_SCAN:
                    return new Task(TaskType.SCAN);
                case COMMAND_MOVE:
                    return new Task(TaskType.MOVE, commandSplit[1], commandSplit[2]);
                case COMMAND_COPY:
                    return new Task(TaskType.COPY, commandSplit[1], commandSplit[2]);
                case COMMAND_REMOVE:
                    return new Task(TaskType.REMOVE, commandSplit[1]);
                case COMMAND_PRINT:
                    return new Task(TaskType.PRINT);
                case COMMAND_EXIT:
                    return new Task(TaskType.EXIT);
                default: // Некорректный формат команды, указана неизвестная команда
                    // Выводим сообщение об ошибке
                    log_error("unknown command. please, try again");
            }
        }
        catch (ArrayIndexOutOfBoundsException e) { //Некорректный формат команды, указано неверное количество литералов
            // Выводим сообщение об ошибке
            log_error("incorrect command format. please, try again");
        }

        // Возвращаем пустую команду
        return new Task(TaskType.EMPTY);
    }

    /**
     * Вывод сообщения об ошибке сразу же в консоль. Используется только самой вьюхой для вывода ошибок в процессе парсинга
     * @param message - сообщение об ошибке
     */
    private void log_error(String message) {
        // Формируем событие
        Event event = new Event(EventType.ERROR, TaskType.EMPTY, message);
        // Выводим сообщение об ошибке здесь же в консоли
        write(event);
    }
}
