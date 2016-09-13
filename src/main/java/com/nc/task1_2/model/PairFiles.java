package com.nc.task1_2.model;

/**
 * Created by ilpr0816 on 12.09.2016.
 * Абстракция для хранения 2 файлов
 */
public class PairFiles {
    /**
     * Файл 1, из которого выполняется операция
     */
    private File fileFrom;

    /**
     * Файл 2, в который выполняется операция
     */
    private File fileTo;

    /**
     * Конструктор
     * @param fileFrom - файл, откуда выполняется операция
     * @param fileTo - файл, куда выполняется операция
     */
    PairFiles(File fileFrom, File fileTo) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
    }

    /**
     * Геттер для файла 1
     * @return файл, откуда выполняется операция
     */
    public File getFileFrom() {
        return fileFrom;
    }

    /**
     * Геттер для файла 2
     * @return файл, откуда выполняется операция
     */
    public File getFileTo() {
        return fileTo;
    }
}
