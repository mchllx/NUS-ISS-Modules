package vttp2023.batch4.paf.day25emart.controllers;

import java.util.Arrays;
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
import vttp2023.batch4.paf.day25emart.models.LineItem;
import vttp2023.batch4.paf.day25emart.models.PurchaseOrder;
import vttp2023.batch4.paf.day25emart.repositories.PurchaseOrderException;
import vttp2023.batch4.paf.day25emart.services.LineItemService;
import vttp2023.batch4.paf.day25emart.services.PurchaseOrderService;

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

		String[] names = poSvc.getAllRegisteredCustomers();
		sess.setAttribute("names", names);
		
		mav.addObject("po", po);
		mav.addObject("names", names);
		mav.setStatus(HttpStatusCode.valueOf(200));
		return mav;
	}

	//ensure spring-boot-starter-validation dependency is injected...
	@PostMapping(path="/order")
	public ModelAndView postOrder(@Valid @ModelAttribute("po") PurchaseOrder po, BindingResult binding,
	HttpSession sess, @RequestParam String item, Integer quantity) {
		ModelAndView mav = new ModelAndView("index");

		//retrieve names again after checkout
		if (sess.isNew()) {
			String[] names = poSvc.getAllRegisteredCustomers();
			sess.setAttribute("names", names);
			mav.addObject("names", names);
		} else {
			String[] names = (String[])sess.getAttribute("names");
			mav.addObject("names", names);
		}

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

	//POST checkout, text
	@PostMapping(path="/checkout")
	public ModelAndView postCart(HttpSession sess) {

		ModelAndView mav = new ModelAndView("index");

		String[] names = (String[])sess.getAttribute("names");
		mav.addObject("names", names);

		PurchaseOrder po = Utils.getPO(sess);
		List<LineItem> lineItems = po.getLineItems();

		//platformtransaction
		if (!poSvc.createPurchaseOrderManualTx(po)) {
			mav.setStatus(HttpStatusCode.valueOf(400));
			mav.addObject("po", po);
		} else {
			mav.setStatus(HttpStatusCode.valueOf(200));
			sess.invalidate();
			mav.addObject("po", new PurchaseOrder());	
		}

		// transaction without redis
		// while (poSvc.hasOrderId(po) == true) {
		// 	poSvc.generateId(po);
		// }

		// try {
		// 	poSvc.insertPurchaseOrder(po);
		// 	liSvc.insertLineItem(lineItems, po.getOrderId());
		// } catch (PurchaseOrderException e1) {
		// 	//bad request
		// 	// e1.printStackTrace();
		// 	mav.setStatus(HttpStatusCode.valueOf(500));
		// 	mav.addObject("po", po);
		// }

		return mav;
	}

}

