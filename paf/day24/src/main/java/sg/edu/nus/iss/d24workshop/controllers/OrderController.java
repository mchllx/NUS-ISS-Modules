package sg.edu.nus.iss.d24workshop.controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.d24workshop.Utils;
import sg.edu.nus.iss.d24workshop.models.Order;
import sg.edu.nus.iss.d24workshop.models.OrderDetail;
import sg.edu.nus.iss.d24workshop.repositories.OrderException;
import sg.edu.nus.iss.d24workshop.services.OrderService;

@Controller
@RequestMapping(path={"/", "/index"})
public class OrderController {

	@Autowired
	OrderService orderSvc;

	private Logger logger = Logger.getLogger(OrderController.class.getName());

	@GetMapping
	public ModelAndView viewOrder(HttpSession sess) {
		ModelAndView mav = new ModelAndView("index");
		List<OrderDetail> cart = Utils.getCart(sess);
		Order order = new Order();

		if (Utils.hasCart(sess) == true) {
			order.setOrderDetails(cart);
		}
		
		mav.addObject("order", order);
		mav.setStatus(HttpStatusCode.valueOf(200));
		return mav;
	}

	//ensure spring-boot-starter-validation dependency is injected...
	@PostMapping(path="/order")
	public ModelAndView postOrder(@Valid @ModelAttribute("order") Order order, BindingResult binding,
	HttpSession sess, @RequestParam String product, Integer quantity) {
		ModelAndView mav = new ModelAndView("index");

		//retrieve cart items
		List<OrderDetail> cart = Utils.getCart(sess);
		
		if (Utils.hasCart(sess) == true) {
			order.setOrderDetails(cart);
		}

		if (binding.hasErrors()) {
			logger.info("===========Validation errors===========" + binding.getAllErrors());
			mav = new ModelAndView("index", HttpStatusCode.valueOf(400));
			mav.addObject("order", order);
			return mav;
		}

		//saving this instance
		OrderDetail od = new OrderDetail();
		od.setProduct(product);
		od.setQuantity(quantity);
		cart.add(od);
		order.setOrderDetails(cart);

		logger.info("===========Adding order to cart===========" + order);
		
		sess.setAttribute("order", order);
	
		mav.setStatus(HttpStatusCode.valueOf(200));
		mav.addObject("order", order);
		mav.addObject("orderDetails", od);
		return mav;
	}

	//POST checkout, text
	@PostMapping(path="/checkout")
	public ModelAndView postCart(HttpSession sess) {
		ModelAndView mav = new ModelAndView("index");

		Order order = Utils.getOrder(sess);
		List<OrderDetail> orderDetails = order.getOrderDetails();
      
        order.setOrderId(orderSvc.generateId(order));
        order.setOrderDate(new Date());

        logger.info("===========Checking out order===========" + order);
        logger.info("===========Checking out order details===========" + orderDetails);

        try {
            orderSvc.insertOrder(order);
            orderSvc.insertOrderDetail(orderDetails, order.getOrderId());  
        } catch (OrderException e1) {
            mav.setStatus(HttpStatusCode.valueOf(500));
        }

		sess.invalidate();

		mav.setStatus(HttpStatusCode.valueOf(200));
		mav.addObject("order", new Order());	
		return mav;
	}

}

