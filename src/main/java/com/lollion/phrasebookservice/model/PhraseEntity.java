package com.lollion.phrasebookservice.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "phrases")
@Builder
@Data
public class PhraseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @NotNull
    public String phrase;

    @NotNull
    @Column(name = "translation")
    public String translation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lang_from")
    public Language languageFrom;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lang_to")
    public Language languageTo;
}
