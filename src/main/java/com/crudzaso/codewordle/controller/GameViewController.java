package com.crudzaso.codewordle.controller;

import com.crudzaso.codewordle.dto.GameDTO;
import com.crudzaso.codewordle.dto.ThemeDTO;
import com.crudzaso.codewordle.service.GameService;
import com.crudzaso.codewordle.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * MVC controller for rendering game views.
 * Handles page rendering and view model preparation.
 */
@Controller
@RequiredArgsConstructor
public class GameViewController {

    private final ThemeService themeService;
    private final GameService gameService;

    /**
     * Renders the main game interface.
     * Prepares themes and current game data for the view.
     *
     * @param model the Spring MVC model for view data
     * @return the game view template name
     */
    @GetMapping("/")
    public String gameView(Model model) {
        List<ThemeDTO> themes = themeService.getAllThemes();
        GameDTO currentGame = gameService.getCurrentGame();
        
        model.addAttribute("themes", themes);
        model.addAttribute("currentGame", currentGame);
        
        return "game";
    }

    /**
     * Renders the game rules and instructions page.
     *
     * @return the rules view template name
     */
    @GetMapping("/rules")
    public String rulesView() {
        return "rules";
    }

    /**
     * Renders the about page with application information.
     *
     * @return the about view template name
     */
    @GetMapping("/about")
    public String aboutView() {
        return "about";
    }
}