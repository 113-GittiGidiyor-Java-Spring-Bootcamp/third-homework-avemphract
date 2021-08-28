package dev.patika.thirdhomework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface BaseController<T> {

    ResponseEntity<List<T>> findAll();

    ResponseEntity<T> findById(int id);

    ResponseEntity<T> save(T body);

    ResponseEntity<T> update(T body);

    ResponseEntity<T> deleteById(int id);

}
