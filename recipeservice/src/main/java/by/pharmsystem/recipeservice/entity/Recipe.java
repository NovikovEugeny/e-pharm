package by.pharmsystem.recipeservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "recipes")
public class Recipe implements Serializable {

    @Id
    private long id;
    private long clientId;
    private long doctorId;
    private long medicamentId;
    private int quantity;
    private Date endDate;
    private String status;
}
