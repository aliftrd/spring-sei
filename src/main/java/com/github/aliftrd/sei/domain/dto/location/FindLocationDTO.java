package com.github.aliftrd.sei.domain.dto.location;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindLocationDTO {
    protected Long id;

    @JsonProperty("nama_lokasi")
    protected String namaLokasi;

    protected String negara;

    protected String provinsi;

    protected String kota;

    @JsonProperty("created_at")
    protected Date createdAt;
}
