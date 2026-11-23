package com.capitanmurasa.impulse.Tracks;

import jakarta.persistence.*;

@Entity
@Table(name = "formats")
public class FormatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Column(name = "mime", nullable = false)
    private String mime;

    @Column(name = "is_lossless", nullable = false)
    private boolean lossless;

    public FormatModel() {}

    public FormatModel(String name, String extension, String mime, boolean lossless) {
        this.name = name;
        this.extension = extension;
        this.mime = mime;
        this.lossless = lossless;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public boolean isLossless() {
        return lossless;
    }

    public void setLossless(boolean lossless) {
        this.lossless = lossless;
    }
}