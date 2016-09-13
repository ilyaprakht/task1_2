package com.nc.task1_2;

import com.nc.task1_2.controller.Controller;
import com.nc.task1_2.controller.dao.FileDAO;
import com.nc.task1_2.controller.dao.impl.FSFileDAOImpl;
import com.nc.task1_2.controller.dao.impl.JDBCFileDAOImpl;
import com.nc.task1_2.view.View;
import com.nc.task1_2.view.impl.ConsoleView;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Главный класс приложения
 */
public class Main {
    public static void main(String[] args) {
        // Инициализируем представление
        View consoleView = new ConsoleView();

        // Инициализируем DAO для хранилищ
        FileDAO dbDAO = new JDBCFileDAOImpl();
        FileDAO fsDAO = new FSFileDAOImpl();

        // Инициализируем и запускаем контроллер
        Controller controller = new Controller(consoleView, dbDAO, fsDAO);
        controller.run();
    }
}
