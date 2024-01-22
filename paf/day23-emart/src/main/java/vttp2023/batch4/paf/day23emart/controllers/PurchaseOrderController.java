package vttp2023.batch4.paf.day23emart.controllers;

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
import vttp2023.batch4.paf.Utils;
import vttp2023.batch4.paf.day23emart.models.LineItem;
import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;
import vttp2023.batch4.paf.day23emart.services.LineItemService;
import vttp2023.batch4.paf.day23emart.services.PurchaseOrderService;

@Controller
@RequestMapping(path={"/", "/index"})
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService poSvc;

	@Autowired
	LineItemService liSvc;

	private Logger logger = Logger.getLogger(PurchaseOrderController.class.getName());

	@GetMapping
	public ModelAndView viewOrder(HttpSession sess) {
		ModelAndView mav = new ModelAndView("index");
		List<LineItem> cart = Utils.getCart(sess);
		PurchaseOrder po = new PurchaseOrder();

		if (Utils.hasCart(sess) == true) {
			po.setLineItems(cart);
		}
		
		mav.addObject("po", po);
		mav.setStatus(HttpStatusCode.valueOf(200));
		return mav;
	}

	//ensure spring-boot-starter-validation dependency is injected...
	@PostMapping(path="/order")
	public ModelAndView postOrder(@Valid @ModelAttribute("po") PurchaseOrder po, BindingResult binding,
	HttpSession sess, @RequestParam String item, Integer quantity) {
		ModelAndView mav = new ModelAndView("index");

		//retrieve cart items
		List<LineItem> cart = Utils.getCart(sess);
		
		if (Utils.hasCart(sess) == true) {
			po.setLineItems(cart);
		}

		if (binding.hasErrors()) {
			logger.info("===========Validation errors===========" + binding.getAllErrors());
			mav = new ModelAndView("index", HttpStatusCode.valueOf(400));
			mav.addObject("po", po);
			return mav;
		}

		// logger.info("===========Form data===========" + po);
		po.setCreatedOn(new Date());
		po.setLastUpdate(new Date());

		//saving this instance
		LineItem li = new LineItem();
		li.setItem(item);
		li.setQuantity(quantity);
		cart.add(li);
		po.setLineItems(cart);

		logger.info("===========Purchase Object===========" + po);
		
		sess.setAttribute("po", po);
	
		mav.setStatus(HttpStatusCode.valueOf(200));
		mav.addObject("po", po);
		return mav;
	}

	// @GetMapping(path="/order")
	// public ModelAndView getOrder(PurchaseOrder po, HttpSession sess) {
	// 	ModelAndView mav = new ModelAndView("index");
	// 	List<LineItem> cart = Utils.getCart(sess);

	// 	if (Utils.hasCart(sess) == true) {
	// 		po.setLineItems(cart);
	// 		mav.addObject("po", po);
	// 	} else {
	// 		mav.addObject("po", new PurchaseOrder());
	// 	}

	// 	mav.setStatus(HttpStatusCode.valueOf(200));
	// 	return mav;
	// }

	//POST checkout, text
	@PostMapping(path="/checkout")
	public ModelAndView postCart(HttpSession sess) {
		ModelAndView mav = new ModelAndView("index");

		PurchaseOrder po = Utils.getPO(sess);
		List<LineItem> lineItems = po.getLineItems();

		while (poSvc.hasOrderId(po) == true) {
			poSvc.generateId(po);
		}

		poSvc.insertPurchaseOrder(po);
		liSvc.insertLineItem(lineItems, po.getOrderId());
		sess.invalidate();

		mav.setStatus(HttpStatusCode.valueOf(200));
		mav.addObject("po", new PurchaseOrder());	
		return mav;
	}

}

