package com.zen.gamelib.input;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class KeyboardInput extends KeyAdapter {

  private List<KeyCallback> callbacks = Collections.synchronizedList(new ArrayList<>(20));
  private List<KeyCallback> persistentCallbacks = Collections.synchronizedList(new ArrayList<>(40));
  private List<Integer> keyBuffer = Collections.synchronizedList(new LinkedList<>());

  public KeyboardInput() { }

  public void initialize(Component component) {
    component.addKeyListener(this);
  }

  public void removeCallback(KeyCallback callback) {
    this.callbacks.remove(callback);
  }

  public void addCallback(KeyCallback callback) {
    this.addCallback(callback, false);
  }

  public void addCallback(KeyCallback callback, boolean persistent) {
    if (persistent) {
      this.persistentCallbacks.add(callback);
    } else {
      this.callbacks.add(callback);
    }
  }

  public void clearCallbacks() {
    callbacks.clear();
  }

  public void processCallbacks() {
    ListIterator<Integer> iterator = this.keyBuffer.listIterator();

    while (iterator.hasNext()) {
      Integer key = iterator.next();
      this.callbacks.forEach(keyCallback -> keyCallback.onKeyPress(key));
      this.persistentCallbacks.forEach(keyCallback -> keyCallback.onKeyPress(key));
      iterator.remove();
    }
  }

  @Override
  public void keyPressed(KeyEvent event) {
    this.keyBuffer.listIterator().add(event.getKeyCode());
  }

}
