package com.gcit.lms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Loans;
import com.gcit.lms.service.BorrowerService;

@Controller
public class BorrowerController {

	@Autowired
	BorrowerService service;
	
	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	public String LogIn(@RequestParam("cardNo")Integer cardNo, Model model) {	
		
	
		boolean logIn=false;
		try {
			logIn = service.CheckId(cardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(logIn ==true)
		{
			model.addAttribute("cardNo", cardNo);
		 return "borrowerMenu";
		}
		else {
			String message ="This card number was not registered. Try a new one";
			model.addAttribute("message", message);
			return "borrower";
		}
		
		
	}
	@RequestMapping(value = "/checked", method = RequestMethod.POST)
	public String checkBook(@RequestParam("bookId")Integer bookId,@RequestParam("branchId")Integer branchId,
			@RequestParam("cardNo")Integer cardNo,Model model) {	
		
		Loans loans = new Loans();
		loans.setBookId(bookId);
		loans.setBranchId(branchId);
		loans.setCardNo(cardNo);
		try {
			service.checkOut(loans);
			model.addAttribute("cardNo", cardNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String message ="checked successfully";
		model.addAttribute("message",message);
		return "borrowerMenu";
	}
	@RequestMapping(value = "/returned", method = RequestMethod.GET)
	public String returnBook(@RequestParam("bookId")Integer bookId,@RequestParam("branchId")Integer branchId,
			@RequestParam("cardNo")Integer cardNo,Model model) {	
		
		
		Loans l = new Loans();
		l.setBookId(bookId);
		l.setBranchId(branchId);
		l.setCardNo(cardNo);
		try {
			service.Return(l);
			model.addAttribute("cardNo", cardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String message ="Returned successfully";
		model.addAttribute("message",message);
		return "borrowerMenu";
	}
	@RequestMapping(value = "/checkOut", method = RequestMethod.GET)
	public String checkOutMenu(@RequestParam("cardNo")Integer cardNo, Model model) {	
		
		model.addAttribute("cardNo", cardNo);
		
		return "checkOut";
	}
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String checkOut(@RequestParam("cardNo")Integer cardNo,@RequestParam("branchId")Integer branchId, Model model) {	
		
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);
		return "check";
	}
	@RequestMapping(value = "/checkIn", method = RequestMethod.GET)
	public String checkIn(@RequestParam("cardNo")Integer cardNo, Model model) {	
		
		model.addAttribute("cardNo", cardNo);
		
		return "checkIn";
	}
	@RequestMapping(value = "/returnbook", method = RequestMethod.GET)
	public String returnBook(@RequestParam("cardNo")Integer cardNo,@RequestParam("branchId")Integer branchId, Model model) {	
		
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);
		return "returnbook";
	}
	
	
	
}
