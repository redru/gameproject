package com.zen.gamelib.input;

import com.zen.gamelib.core.InputEventListener;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class KeyboardInputHandler extends KeyAdapter {

  private List<InputEventListener> callbacks = Collections.synchronizedList(new LinkedList<>());
  private List<Integer> keyBuffer = Collections.synchronizedList(new LinkedList<>());

  public KeyboardInputHandler() { }

  public void initialize(Component component) {
    component.addKeyListener(this);
  }

  public void removeCallback(InputEventListener callback) {
    this.callbacks.remove(callback);
  }

  public void addCallback(InputEventListener callback) {
    this.callbacks.add(callback);
  }

  public void clearCallbacks() {
    callbacks.clear();
  }

  public void processCallbacks() {
    ListIterator<Integer> iterator = this.keyBuffer.listIterator();

    while (iterator.hasNext()) {
      Integer key = iterator.next();
      this.callbacks.forEach(keyCallback -> keyCallback.onKeyPress(key));
      iterator.remove();
    }
  }

  public void processCallbacks(List<InputEventListener> externalCallbacks) {
    ListIterator<Integer> iterator = this.keyBuffer.listIterator();

    while (iterator.hasNext()) {
      Integer key = iterator.next();
      this.callbacks.forEach(keyCallback -> keyCallback.onKeyPress(key));
      externalCallbacks.forEach(keyCallback -> keyCallback.onKeyPress(key));
      iterator.remove();
    }
  }

  @Override
  public void keyPressed(KeyEvent event) {
    this.keyBuffer.listIterator().add(event.getKeyCode());
  }

}
