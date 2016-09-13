package com.nc.task1_2.controller.action.impl;

import com.nc.task1_2.controller.ViewObservable;
import com.nc.task1_2.controller.action.Action;
import com.nc.task1_2.controller.dao.FileDAO;
import com.nc.task1_2.controller.event.Event;
import com.nc.task1_2.controller.event.EventType;
import com.nc.task1_2.controller.exception.BaseException;
import com.nc.task1_2.controller.exception.ControllerException;
import com.nc.task1_2.controller.task.Task;
import com.nc.task1_2.controller.task.TaskType;
import com.nc.task1_2.model.File;
import com.nc.task1_2.model.VirtualFileSystem;

/**
 * Created by ilpr0816 on 13.09.2016.
 * Реализация интерфейса Action для команды scan
 */
public class ScanAction implements Action {
    /**
     * Выполнение действия
     * @param task - задача
     * @param dbDAO - дао доступа к БД
     * @param fsDAO - доа доступа к ФС
     * @param observable - нотификатор слушателя
     */
    @Override
    public void doAction(Task task, FileDAO dbDAO, FileDAO fsDAO, ViewObservable observable) throws BaseException, ControllerException {
        Event event = new Event(EventType.TASK_START, TaskType.SCAN);
        observable.notifyObservers(event);

        event = new Event(EventType.LOG, TaskType.SCAN, "start scan fs");
        observable.notifyObservers(event);
        File headFile = fsDAO.scan(task.getPath1());
        event = new Event(EventType.LOG, TaskType.SCAN, "finish scan fs");
        observable.notifyObservers(event);

        VirtualFileSystem.getInstance().setHeadFile(headFile);

        event = new Event(EventType.LOG, TaskType.SCAN, "start save in db");
        observable.notifyObservers(event);
        dbDAO.reset();
        dbDAO.create(headFile);
        event = new Event(EventType.LOG, TaskType.SCAN, "finish save in db");
        observable.notifyObservers(event);

        event = new Event(EventType.TASK_DONE, TaskType.SCAN);
        observable.notifyObservers(event);
    }
}
