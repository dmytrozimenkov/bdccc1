package sample.controller;

import sample.WindowManager;

public class WindowController {

    private WindowManager wm = WindowManager.getInstance();

    public void clients(){
        wm.createTableWindow("Clients", "Клиенты");
    }

    public void agents(){
        wm.createTableWindow("Agents", "Агенты");
    }

    public void countries() {wm.createTableWindow("Countries", "Страны");}

    public void exit(){
        wm.closeAll();
    }
}
