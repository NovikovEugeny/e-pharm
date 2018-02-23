package by.pharmsystem.medicamentservice.controller;

import by.pharmsystem.medicamentservice.entity.Medicament;
import by.pharmsystem.medicamentservice.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicamentController {

    @Autowired
    private MedicamentService medicamentService;

    @GetMapping("/show-list-by-group/{group}/")
    public List<Medicament> showByGroup(@PathVariable String group) {
        return medicamentService.findByGroup(group);
    }

    @GetMapping("/show-list-by-name/{name}/")
    public List<Medicament> showByName(@PathVariable String name) {
        return medicamentService.findByName(name);
    }

    @PostMapping("/add-new-medicament")
    public void add(@RequestBody Medicament medicament) {
        medicamentService.add(medicament);
    }

    @PatchMapping("/addQuantity/{id}/{quantity}/")
    public void addQuantity(@PathVariable long id, @PathVariable int quantity) {
        medicamentService.addQuantity(id, quantity);
    }

    @DeleteMapping("/deleteMedicament/{id}/")
    public void delete(@PathVariable long id) {
        medicamentService.delete(id);
    }

    @PostMapping("/get-prices")
    public List<Double> getPrices(@RequestBody List<Long> identifiers) {
        return medicamentService.getPrices(identifiers);
    }
}
