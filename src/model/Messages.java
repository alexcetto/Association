package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by alexandrecetto on 18/03/2016.
 */

@Entity
public class Messages {
    private Long id;

    @Column(nullable = false)
    private String mess;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;


    @Column(name = "users", nullable = false)
    @OneToMany
    private User user;

    public Messages() {
    }


    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
