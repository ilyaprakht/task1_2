package com.nc.task1_2.controller.action;

import com.nc.task1_2.controller.ViewObservable;
import com.nc.task1_2.controller.dao.FileDAO;
import com.nc.task1_2.controller.exception.AbstractException;
import com.nc.task1_2.controller.exception.ControllerException;
import com.nc.task1_2.controller.task.Task;

/**
 * Created by ilpr0816 on 13.09.2016.
 * Интерфейс действия, выполнения команды
 */
public interface Action {
    /**
     * Выполнение действия
     * @param task - задача
     * @param dbDAO - дао доступа к БД
     * @param fsDAO - доа доступа к ФС
     * @param observable - нотификатор слушателя
     */
    void doAction(Task task, FileDAO dbDAO, FileDAO fsDAO, ViewObservable observable) throws AbstractException, ControllerException;
}
