package com.nc.task1_2.controller.dao.impl;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.nc.task1_2.controller.dao.FileDAO;
import com.nc.task1_2.controller.exception.DataBaseException;
import com.nc.task1_2.model.File;
import com.nc.task1_2.model.Path;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Реализация FileDAO для MySQL через JDBC
 */
public class JDBCFileDAOImpl implements FileDAO {
    /**
     * url подключения драйвера jdbc
     */
    private String url;

    /**
     * Логин доступа к БД
     */
    private String user;

    /**
     * Пароль доступа к БД
     */
    private String password;

    /**
     * Таймаут проверки валидности подключения
     */
    private int timeout;

    /**
     * Экземпляр подключения
     */
    private Connection connection;

    private void initParams() throws DataBaseException {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/task1_2.properties");
            property.load(fis);

            url = property.getProperty("db.url");
            user = property.getProperty("db.user");
            password = property.getProperty("db.password");
            timeout = Integer.getInteger(property.getProperty("db.timeout"));

        } catch (IOException e) {
            throw new DataBaseException(e.getMessage(), null);
        }
    }

    /**
     * Возвращает statement, делает реконнект, если коннекш потерян
     * @return statement
     */
    private Statement getStatement() throws SQLException, DataBaseException {
        // Проверяем, что коннекш активен
        if (connection == null || !connection.isValid(timeout)) {// Если не активен, то подключаемся заново
            initParams(); // Инициализируем параметры подключения из файла properties
            connection = (Connection) DriverManager.getConnection(url, user, password); // Создаем новый коннекшн
        }
        // Возвращаем действующий statement
        return (Statement) connection.createStatement();
    }

    /**
     * Выполняет запрос, пордразумевающий возвращаемое значение
     * @param query - запрос
     * @return - значение в виде ResultSet
     */
    protected ResultSet executeQuery(String query) throws DataBaseException {
        ResultSet resultSet;
        try {
            resultSet = getStatement().executeQuery(query);
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), null);
        }
        return resultSet;
    }

    /**
     * Выполняет запрос, не подразумевающий возвращаемое значение
     * @param query - запрос
     */
    protected void execute(String query) throws DataBaseException {
        try {
            getStatement().execute(query);
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), null);
        }
    }

    /**
     * Заменяет слеш на двойной слеш, так как в mysql это символ экранирования и при вставке одиночный слеш игнорируется
     * @param s - входный строка
     * @return - строка с заменой
     */
    protected String replaceS(String s) {
        return s.replace("\\", "\\\\");
    }

    /**
     * Создание нового файла
     *
     * @param file - экземпляр класса File
     */
    @Override
    public void create(File file) {

    }

    /**
     * Удаление файла
     *
     * @param file - экземпляр класса File
     */
    @Override
    public void delete(File file) {

    }

    /**
     * Перемещение файла
     *
     * @param fileFrom - экземпляр класса File, который перемещается
     * @param fileTo   - экземпляр класса File, куда перемещается
     */
    @Override
    public void move(File fileFrom, File fileTo) {

    }

    /**
     * Копирование файла
     *
     * @param fileFrom - экземпляр класса File, который копируется
     * @param fileTo   - экземпляр класса File, куда копируется
     */
    @Override
    public void copy(File fileFrom, File fileTo) {

    }

    /**
     * Сбрасывает хранилище
     */
    @Override
    public void reset() {

    }

    /**
     * Проверяет, есть ли такой файл в хранилище
     *
     * @param file - экземпляр класса File
     * @return true, если файл найден, false, если файл не найден
     */
    @Override
    public boolean exist(File file) {
        return false;
    }

    /**
     * Сканирует хранилище, начиная от указанного файла
     * @param path - путь к головному файлу, от которого начнется сканирование хранилища
     */
    public File scan(Path path) throws DataBaseException {
        return null; // nothing to do
    }
}
