package com.xiaoxiong.nbst01.service;

import java.util.List;

/**
 * 基本的增删查改
 */
public interface IBaseService<T> {
    List<T> getList();

    T getItem(int id);

    boolean updateItem(T t);

    boolean deleteItem(int id);

    boolean addItem(T t);


}
