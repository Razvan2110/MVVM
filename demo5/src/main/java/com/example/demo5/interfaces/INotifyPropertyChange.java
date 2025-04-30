package com.example.demo5.interfaces;

import java.beans.PropertyChangeListener;

public interface INotifyPropertyChange {
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}