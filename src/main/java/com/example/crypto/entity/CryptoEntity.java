package com.example.crypto.entity;

import com.example.crypto.entity.audit.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "cryptos")
@EqualsAndHashCode(callSuper=false)
public class CryptoEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;
}
