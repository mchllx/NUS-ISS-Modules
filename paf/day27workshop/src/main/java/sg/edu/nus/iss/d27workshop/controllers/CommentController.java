package sg.edu.nus.iss.d27workshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class CommentController {

    @GetMapping
    public ModelAndView showForm() {
        return new ModelAndView("form");

    }


    
}
