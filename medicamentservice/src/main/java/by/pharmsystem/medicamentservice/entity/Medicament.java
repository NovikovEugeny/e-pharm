package by.pharmsystem.medicamentservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class Medicament implements Serializable {

    private static final long serialVersionUID = -2416625424862852665L;

    private long id;
    private String name;
    private String group;
    private String form;
    private String amount;
    private String activeSubstances;
    private String country;
    private boolean recipe;
    private double price;
    private int quantity;
}
