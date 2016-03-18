package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by alexandrecetto on 18/03/2016.
 */

@Entity
public class Messages {
    private Long id;

    @Column(nullable = false)
    private String mess;
    @Column(nullable = false)
    private Date timestamp;
    @Column(nullable = false)
    private User user;



    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
