package by.pharmsystem.medicamentservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "medicaments")
public class Medicament implements Serializable {

    private static final long serialVersionUID = -2416625424862852665L;

    @Id
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
