package sample.controller;

import sample.WindowManager;

public class WindowController {

    private WindowManager wm = WindowManager.getInstance();

    public void adress(){ wm.createTableWindow("Adress", "Адресс", false); }

    public void clients(){ wm.createTableWindow("Clients", "Клиенты", false); }

    public void agents(){
        wm.createTableWindow("Agents", "Агенты", false);
    }

    public void countries() {wm.createTableWindow("Countries", "Страны", false);}

    public void exit(){
        wm.closeAll();
    }
}
