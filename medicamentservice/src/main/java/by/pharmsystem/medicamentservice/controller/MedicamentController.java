package by.pharmsystem.medicamentservice.controller;

import by.pharmsystem.medicamentservice.entity.Medicament;
import by.pharmsystem.medicamentservice.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MedicamentController {

    @Autowired
    private MedicamentService medicamentService;

    @GetMapping("/show-list-by-group/{group}/{city}/")
    public List<Medicament> showByGroup(@PathVariable String group, @PathVariable String city) {
        return medicamentService.findByGroup(group, city);
    }

    @GetMapping("/show-list-by-name/{name}/{city}/")
    public List<Medicament> showByName(@PathVariable String name, @PathVariable String city) {
        return medicamentService.findByName(name, city);
    }

    @PostMapping("/add-new-medicament/{city}/")
    public void add(@RequestBody Medicament medicament, @PathVariable String city) {
        medicamentService.add(medicament, city);
    }

    @PutMapping("/addQuantity/{id}/{quantity}/{city/}")
    public void addQuantity(@PathVariable long id, @PathVariable int quantity, @PathVariable String city) {
        medicamentService.addQuantity(id, quantity, city);
    }

    @DeleteMapping("/deleteMedicament/{id}/{city}/")
    public void delete(@PathVariable long id, @PathVariable String city) {
        medicamentService.delete(id, city);
    }

    @PostMapping("/get-prices/{city}/")
    public Map<Long, Double> getPrices(@RequestBody List<Long> identifiers, @PathVariable String city) {
        return medicamentService.getPrices(identifiers, city);
    }

    @PostMapping("/get-recipe-requirements")
    public Map<Long, Boolean> getRecipeRequirements(@RequestBody List<Long> identifiers) {
        return medicamentService.getRecipeRequirements(identifiers);
    }
}
