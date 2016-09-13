package com.nc.task1_2.controller.dao.impl;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.nc.task1_2.controller.dao.FileDAO;
import com.nc.task1_2.controller.exception.DataBaseException;
import com.nc.task1_2.model.File;
import com.nc.task1_2.model.Folder;
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
     * Параметры пропертей
     */
    private static final String PROPERTIES_PATH = "src/main/resources/task1_2.properties";
    private static final String PROPERTIES_URL = "db.url";
    private static final String PROPERTIES_USER = "db.user";
    private static final String PROPERTIES_PASSWORD = "db.password";
    private static final String PROPERTIES_TIMEOUT = "db.timeout";

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

    /**
     * Инициализация параметров из проперти-файла
     */
    private void initParams() throws DataBaseException {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(PROPERTIES_PATH);
            property.load(fis);

            url = property.getProperty(PROPERTIES_URL);
            user = property.getProperty(PROPERTIES_USER);
            password = property.getProperty(PROPERTIES_PASSWORD);
            timeout = Integer.parseInt(property.getProperty(PROPERTIES_TIMEOUT));

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
    private ResultSet executeQuery(String query) throws DataBaseException {
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
    private void execute(String query) throws DataBaseException {
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
    private String replaceS(String s) {
        return s.replace("\\", "\\\\");
    }

    /**
     * Создание нового файла
     * @param file - экземпляр класса File
     */
    @Override
    public void create(File file) throws DataBaseException {
        createFilesRec(file);
    }

    /**
     * Рекурсивное создание файлов
     * @param file - файл
     */
    private void createFilesRec(File file) throws DataBaseException {
        createFile(file);
        if (file instanceof Folder) {
            for (File childFile : ((Folder) file).getListChildFiles()) {
                createFilesRec(childFile);
            }
        }
    }

    /**
     * Создание конкретного файла уже в БД
     * @param file - файл
     */
    private void createFile(File file) throws DataBaseException {
        String query;
        if (file instanceof Folder) {
            query = "insert into t_folder (name" + (file.getParentFolder() != null ? ", link_folder": "") + ") values ('"
                    + replaceS(file.getFileName()) + "'" + (file.getParentFolder() != null ? ", " + file.getParentFolder().getId() : "") + ")";

        } else {
            query = "insert into t_file (name" + (file.getParentFolder() != null ? ", link_folder": "") + ") values ('"
                    + replaceS(file.getFileName()) + "'" + (file.getParentFolder() != null ? ", " + file.getParentFolder().getId() : "") + ")";
        }
        execute(query);

        query = "select last_insert_id()";
        ResultSet resultSet = executeQuery(query);

        try {
            if (resultSet.next()) {
                file.setId(resultSet.getInt(1));
            }
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), file);
        }
    }

    /**
     * Удаление файла
     * @param file - экземпляр класса File
     */
    @Override
    public void delete(File file) throws DataBaseException {
        String query;
        if (file instanceof Folder) {
            query = "delete from t_folder where id = " + file.getId();
        } else {
            query = "delete from t_file where id = " + file.getId();
        }
        execute(query);
    }

    /**
     * Перемещение файла
     * @param fileFrom - экземпляр класса File, который перемещается
     * @param fileTo   - экземпляр класса File, куда перемещается
     */
    @Override
    public void move(File fileFrom, File fileTo) throws DataBaseException {
        String query;
        if (fileFrom instanceof Folder) {
            query = "update t_folder set link_folder = " + fileTo.getParentFolder().getId() + " where id = " + fileFrom.getId();
        } else {
            query = "update t_file set link_folder = " + fileTo.getParentFolder().getId() + " where id = " + fileFrom.getId();
        }
        execute(query);
    }

    /**
     * Копирование файла
     * @param fileFrom - экземпляр класса File, который копируется
     * @param fileTo   - экземпляр класса File, куда копируется
     */
    @Override
    public void copy(File fileFrom, File fileTo) throws DataBaseException {
        create(fileTo);
    }

    /**
     * Сбрасывает хранилище
     */
    @Override
    public void reset() throws DataBaseException {
        // Очищаем таблицу t_folder
        String query = "delete from t_folder";
        execute(query);

        // Очищаем таблицу t_file
        query = "delete from t_file";
        execute(query);
    }

    /**
     * Проверяет, есть ли такой файл в хранилище
     * @param file - экземпляр класса File
     * @return true, если файл найден, false, если файл не найден
     */
    @Override
    public boolean exist(File file) throws DataBaseException {
        String query;
        if (file instanceof Folder) {
            query = "select * from t_folder where id = " + file.getId();
        } else {
            query = "select * from t_file where id = " + file.getId();
        }

        ResultSet resultSet = executeQuery(query);
        try {
            if (resultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), file);
        }
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
