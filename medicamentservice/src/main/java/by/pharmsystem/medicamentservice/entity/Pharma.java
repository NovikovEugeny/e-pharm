package by.pharmsystem.medicamentservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "pharms")
public class Pharma
{
    @Id
    private String address;
    private List<Medicament> medicaments;
}
