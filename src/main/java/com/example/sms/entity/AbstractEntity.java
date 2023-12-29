package com.example.sms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = -7629975639679462008L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created", columnDefinition = "timestamp default current_timestamp")
    private Date created;

    @Column(name="updated", columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updated;

    @Column(name="deleted", columnDefinition = "tinyint(1) default 0")
    private boolean deleted;
}
