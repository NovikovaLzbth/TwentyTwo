package com.company;
import java.util.ArrayList;
import java.util.List;

interface Observable {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers(String message);
}

class Group implements Observable {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {

        observers.add(observer);
    }
    @Override
    public void unsubscribe(Observer observer) {

        observers.remove(observer);
    }
    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    public void sendMessage(String message) {

        notifyObservers(message);
    }
}

interface Observer {
    void update(String message);
}

class User implements Observer {
    private String name;
    public User(String name) {

        this.name = name;
    }
    @Override
    public void update(String message) {

        System.out.println(name + " получил сообщение: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();
        Group group4 = new Group();

        User user1 = new User("Пользователь 1");
        User user2 = new User("Пользователь 2");

        group1.subscribe(user1);
        group2.subscribe(user1);
        group3.subscribe(user1);
        group1.subscribe(user2);
        group2.subscribe(user2);
        group3.subscribe(user2);
        group4.subscribe(user2);

        group1.sendMessage("Сообщения 1 из группы 1");
        group2.sendMessage("Сообщения 2 из группы 2");
        group3.sendMessage("Сообщения 3 из группы 3");
        group4.sendMessage("Сообщения 4 из группы 4");
    }
}

