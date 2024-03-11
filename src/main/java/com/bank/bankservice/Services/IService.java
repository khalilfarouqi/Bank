package com.bank.bankservice.Services;

import java.util.List;

public interface IService<D> {
    List<D> getAll();
    D getById(Long id);
    D save(D dto);
    D update(D dto);
    String deleteById(Long id);
}
