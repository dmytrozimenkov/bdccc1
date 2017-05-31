package sample.controller;

import sample.WindowManager;

public class WindowController {

    private WindowManager wm = WindowManager.getInstance();

    public void clients(){
        wm.createTableWindow("Clients", "Клиенты", 4);
    }

    public void agents(){
        wm.createTableWindow("Agents", "Агенты", 2);
    }

    public void exit(){
        wm.closeAll();
    }
}
