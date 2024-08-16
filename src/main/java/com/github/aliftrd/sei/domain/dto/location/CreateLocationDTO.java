package com.github.aliftrd.sei.domain.dto.location;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateLocationDTO {
    @NotEmpty(message = "Nama lokasi is required")
    @JsonAlias("nama_lokasi")
    protected String namaLokasi;

    @NotEmpty(message = "Negara is required")
    protected String negara;

    @NotEmpty(message = "Provinsi is required")
    protected String provinsi;

    @NotEmpty(message = "Kota is required")
    protected String kota;
}
