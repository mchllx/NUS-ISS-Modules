package sg.edu.nus.iss.d24workshop.controllers;

import java.util.logging.Logger;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import sg.edu.nus.iss.d24workshop.models.Order;

@Controller
@RequestMapping
public class OrderController {

    private Logger logger = Logger.getLogger(OrderController.class.getName());

    @GetMapping(path={"/", "index"})
    public ModelAndView showLandingPage() {
        return new ModelAndView("index", "order", new Order());
    }

    @PostMapping(path="/order")
    public ModelAndView showForm(@Valid @ModelAttribute("order") Order order, BindingResult binding) {
        ModelAndView mav = new ModelAndView("index", "order", order);

        if (binding.hasFieldErrors()) {
            logger.info("======Error occured at validation======" + binding.getAllErrors());
            return new ModelAndView("index", HttpStatusCode.valueOf(400));
        }

        logger.info("======Getting form data======" + order);
        mav.setStatus(HttpStatusCode.valueOf(200));

        //logic to set values from form obj to java pojo
        return mav;
    }
    
}
