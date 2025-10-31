package com.example.CodeWordle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attempt {
    private Long id;
    private Game game;
    private String guessedWord;
    private int attemptNumber;
}
