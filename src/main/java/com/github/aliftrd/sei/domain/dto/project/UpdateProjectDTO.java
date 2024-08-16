package com.github.aliftrd.sei.domain.dto.project;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProjectDTO {
    @NotEmpty(message = "Nama proyek is required")
    @JsonAlias("nama_proyek")
    private String namaProyek;

    @JsonAlias("lokasi")
    private Set<Long> locationIds;

    @NotEmpty(message = "Client is required")
    private String client;

    @NotNull(message = "Tanggal mulai is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonAlias("tgl_mulai")
    private Date tglMulai;

    @NotNull(message = "Tanggal selesai is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonAlias("tgl_selesai")
    private Date tglSelesai;

    @NotEmpty(message = "Pimpinan proyek is required")
    @JsonAlias("pimpinan_proyek")
    private String pimpinanProyek;

    @NotEmpty(message = "Keterangan is required")
    private String keterangan;
}