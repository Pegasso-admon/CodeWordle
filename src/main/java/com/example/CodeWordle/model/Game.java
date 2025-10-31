package com.example.CodeWordle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Long id;
    private Word word;
    private Theme theme;
    private int maxAttempts;
    private boolean isActive;
}
