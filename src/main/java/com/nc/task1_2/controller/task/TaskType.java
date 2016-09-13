package com.nc.task1_2.controller.task;

import com.nc.task1_2.controller.ViewObservable;
import com.nc.task1_2.controller.action.Action;
import com.nc.task1_2.controller.action.impl.*;
import com.nc.task1_2.controller.dao.FileDAO;
import com.nc.task1_2.controller.exception.AbstractException;
import com.nc.task1_2.controller.exception.ControllerException;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Типы команд от пользователя
 */
public enum TaskType {
    SCAN("scan", ScanAction.class), // команда scan
    MOVE("mv", MoveAction.class), // команда mv
    COPY("cp", CopyAction.class), // команда cp
    REMOVE("rm", RemoveAction.class), // команда rm
    PRINT("print", PrintAction.class), // команда print
    EXIT("exit", ExitAction.class), // команда exit
    EMPTY; // пустая команда для служебного использования

    Class<? extends Action> action;
    String actionCode;

    TaskType() {
        this.actionCode = "";
        this.action = null;
    }

    TaskType(String actionCode, Class<? extends Action> action) {
        this.actionCode = actionCode;
        this.action = action;
    }

    public void make(Task task, FileDAO dbDAO, FileDAO fsDAO, ViewObservable observable) throws ControllerException, AbstractException {
        try {
            action.newInstance().doAction(task, dbDAO, fsDAO, observable);
        } catch (ReflectiveOperationException e) {
            throw new ControllerException("Не удалось запустить действие " + actionCode, null); //TODO верный path
        }
    }
}
