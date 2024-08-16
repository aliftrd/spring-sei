package com.github.aliftrd.sei.domain.entity;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lokasi")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToMany(mappedBy = "locations")
    @JsonBackReference
    protected Set<Project> projects;

    @Column(name = "nama_lokasi")
    protected String namaLokasi;

    @Column(name = "negara")
    protected String negara;

    @Column(name = "provinsi")
    protected String provinsi;

    @Column(name = "kota")
    protected String kota;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    protected Date createdAt;
}
