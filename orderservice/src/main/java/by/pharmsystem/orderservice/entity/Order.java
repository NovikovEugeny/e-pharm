package by.pharmsystem.orderservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@Document(collection = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = -1578227228848564223L;

    @Id
    private long id;
    private long clientId;
    private long pharmacistId;
    private Map<Long, Integer> medicaments;
    private double cost;
    private String address;
    private Date requestDate;
    private Date responseDate;
    private String status;
}
