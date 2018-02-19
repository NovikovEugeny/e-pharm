package by.pharmsystem.medicamentservice.service;

import by.pharmsystem.medicamentservice.entity.Medicament;

import java.util.List;

public interface MedicamentService {

    void add(Medicament medicament);

    void addQuantity(long id, int quantity);

    void delete(long id);

    List<Medicament> findByGroup(String group);

    List<Medicament> findByName(String name);
}
