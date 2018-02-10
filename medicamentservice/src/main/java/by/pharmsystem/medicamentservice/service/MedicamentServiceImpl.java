package by.pharmsystem.medicamentservice.service;

import by.pharmsystem.medicamentservice.entity.Medicament;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentServiceImpl implements MedicamentService {

    @Override
    public void add(Medicament medicament) {

    }

    @Override
    public Medicament findById() {
        return null;
    }

    @Override
    public List<Medicament> findByGroup(String group) {
        return null;
    }

    @Override
    public List<Medicament> findByName(String name) {
        return null;
    }
}
