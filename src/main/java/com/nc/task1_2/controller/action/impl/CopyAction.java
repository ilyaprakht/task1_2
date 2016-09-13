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
import com.nc.task1_2.model.PairFiles;
import com.nc.task1_2.model.VirtualFileSystem;

/**
 * Created by ilpr0816 on 13.09.2016.
 * Реализация интерфейса Action для команды scan
 */
public class CopyAction implements Action {
    /**
     * Выполнение действия
     * @param task - задача
     * @param dbDAO - дао доступа к БД
     * @param fsDAO - доа доступа к ФС
     * @param observable - нотификатор слушателя
     */
    @Override
    public void doAction(Task task, FileDAO dbDAO, FileDAO fsDAO, ViewObservable observable) throws BaseException, ControllerException {
        Event event = new Event(EventType.TASK_START, TaskType.COPY);
        observable.notifyObservers(event);

        PairFiles files = VirtualFileSystem.getInstance().copy(task.getPath1(), task.getPath2());

        event = new Event(EventType.LOG, TaskType.COPY, "start copy in fs");
        observable.notifyObservers(event);
        fsDAO.copy(files.getFileFrom(), files.getFileTo());
        event = new Event(EventType.LOG, TaskType.COPY, "finish copy in fs");
        observable.notifyObservers(event);

        event = new Event(EventType.LOG, TaskType.COPY, "start copy in db");
        observable.notifyObservers(event);
        dbDAO.copy(files.getFileFrom(), files.getFileTo());
        event = new Event(EventType.LOG, TaskType.COPY, "finish copy in db");
        observable.notifyObservers(event);

        event = new Event(EventType.TASK_DONE, TaskType.COPY);
        observable.notifyObservers(event);
    }
}