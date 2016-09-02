package com.example.lukekramer.assignment6.repository;

import java.util.Set;

/**
 * Created by lukekramer on 19/04/16.
 */
public interface Repository <E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}
