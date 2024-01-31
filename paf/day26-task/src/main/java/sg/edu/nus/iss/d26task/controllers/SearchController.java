package sg.edu.nus.iss.d26task.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.d26task.models.Game;
import sg.edu.nus.iss.d26task.repositories.GameRepository;

@Controller
@RequestMapping
public class SearchController {

    @Autowired
    private GameRepository gameRepo;

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String title) {
        List<Game> results = gameRepo.findGamesbyName(title);
        // System.out.println("Search result:" + results);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("search");
        mav.addObject("games", results);

        return mav; 
    }





    
}
