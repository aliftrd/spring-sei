package com.github.aliftrd.sei.domain.entity;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "proyek")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToMany
    @JoinTable(
        name = "proyek_lokasi",
        joinColumns = @JoinColumn(name = "proyek_id"),
        inverseJoinColumns = @JoinColumn(name = "lokasi_id")
    )
    @JsonManagedReference
    protected Set<Location> locations;

    @Column(name = "nama_proyek")
    protected String namaProyek;

    @Column(name = "client")
    protected String client;

    @Column(name = "tgl_mulai")
    protected Date tglMulai;

    @Column(name = "tgl_selesai")
    protected Date tglSelesai;

    @Column(name = "pimpinan_proyek")
    protected String pimpinanProyek;

    @Column(columnDefinition = "TEXT", name = "keterangan")
    protected String keterangan;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    protected Date createdAt;
}
