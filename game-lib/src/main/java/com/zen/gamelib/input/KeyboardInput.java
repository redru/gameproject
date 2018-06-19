package com.zen.gamelib.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JFrame;

public class KeyboardInput extends KeyAdapter {

  private List<KeyCallback> callbacks = Collections.synchronizedList(new ArrayList<>(20));
  private List<Integer> keyBuffer = Collections.synchronizedList(new LinkedList<>());

  public KeyboardInput() { }

  public void initialize(JFrame frame) {
    frame.addKeyListener(this);
  }

  public void removeCallback(KeyCallback callback) {
    this.callbacks.remove(callback);
  }

  public void addCallback(KeyCallback callback) {
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

  @Override
  public void keyPressed(KeyEvent event) {
    this.keyBuffer.listIterator().add(event.getKeyCode());
  }

}
