package com.zen.gamelib.util;

import com.zen.gamelib.exception.GameObjectNotFoundException;
import com.zen.gamelib.exception.ObjectsLimitException;
import com.zen.gamelib.objects.GameObject;
import java.util.Arrays;
import java.util.List;

public class GameObjectList {

  private int capacity;
  private volatile List<GameObject> list;

  private volatile int[] ids;
  private volatile String[] names;

  public GameObjectList(int capacity) {
    this.capacity = capacity;
  }

  private void initializeContainers() {
    this.list = Arrays.asList(new GameObject[this.capacity]);
    this.ids = new int[this.capacity];
    this.names = new String[this.capacity];

    for (int i = 0; i < this.capacity; i++) {
      this.list.set(i, new GameObject());
    }
  }

  public GameObject findById(int id) throws GameObjectNotFoundException {
    return this.list.get(this.getIndex(id));
  }

  public GameObject findByName(String name) throws GameObjectNotFoundException {
    return this.list.get(this.getIndex(name));
  }

  public void addGameObject(GameObject object) throws ObjectsLimitException {
    int freeIndex = this.getEmptySlot();

    if (freeIndex < 0) {
      throw new ObjectsLimitException("No empty slots free. Current limit: " + this.capacity);
    }

    object.setActive(true);

    this.ids[freeIndex] = object.getId();
    this.names[freeIndex] = object.getName();
    this.list.set(freeIndex, object);
  }

  public void removeGameObject(GameObject object) throws GameObjectNotFoundException {
    if (object == null) {
      throw new GameObjectNotFoundException("Cannot remove null object.");
    }

    int index = this.getIndex(object.getId());
    this.list.get(index).setActive(false);
  }

  private int getIndex(int id) throws GameObjectNotFoundException {
    for (int i = 0; i < this.capacity; i++) {
      if (this.ids[i] == id) return i;
    }

    throw new GameObjectNotFoundException("Object with id = " + id + " was not found.");
  }

  private int getIndex(String name) throws GameObjectNotFoundException {
    if (name == null)
      throw new GameObjectNotFoundException("Cannot get object for null name.");

    for (int i = 0; i < this.capacity; i++) {
      if (name.equals(this.names[i]))
        return i;
    }

    throw new GameObjectNotFoundException("Object with name = " + name + " was not found.");
  }

  private int getEmptySlot() {
    for (int i = 0; i < this.capacity; i++) {
      if (!this.list.get(i).isActive()) return i;
    }

    return -1;
  }

  public void clear() {
    this.initializeContainers();
  }

  public void printObjects() {
    for (GameObject object : this.list) {
      System.out.println(object);
    }
  }

  public int getCapacity() {
    return capacity;
  }

  public List<GameObject> getList() {
    return list;
  }

}
