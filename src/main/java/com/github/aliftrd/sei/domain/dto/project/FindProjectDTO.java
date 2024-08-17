package com.github.aliftrd.sei.domain.dto.project;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.aliftrd.sei.domain.dto.location.FindLocationDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindProjectDTO {
    protected Long id;

    @JsonProperty("nama_proyek")
    protected String namaProyek;
    
    @JsonProperty("lokasi")
    protected Set<FindLocationDTO> locations;

    protected String client;

    @JsonProperty("tgl_mulai")
    protected Date tglMulai;

    @JsonProperty("tgl_selesai")
    protected Date tglSelesai;

    @JsonProperty("pimpinan_proyek")
    protected String pimpinanProyek;

    protected String keterangan;

    @JsonProperty("created_at")
    protected Date createdAt;
}
