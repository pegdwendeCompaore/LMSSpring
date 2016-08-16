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

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.bookCopies;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.LibrarianService;

@Controller
public class LibrarianController {
	@Autowired
	LibrarianService service;
	@Autowired
	AdminService servic;
	

	@RequestMapping(value = "/librarian", method = RequestMethod.GET)
	public String librarian( Model model) {	
			
			
			return "librarian";
		
		
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("branchId")Integer branchId, Model model) {	
			
			model.addAttribute("branchId", branchId);
			return "lbranch";
		
		
	}
	@RequestMapping(value = "/lbranch", method = RequestMethod.POST)
	public String lbranch(@RequestParam("name")String name, @RequestParam("address")String address, 
			@RequestParam("branchId")Integer branchId, @RequestParam("booksId")String [] booksId, Model model) {	
		
		Branch branch = new Branch();
		if (booksId!=null)
		{
			
			List<Book>books = new ArrayList<Book>();


			for(int i=0; i<booksId.length; i++)
			{
				Book book = new Book();
				book.setBookId(Integer.parseInt(booksId[i]));
				

				books.add(book);

			}
			branch.setBooks(books);
	
		}
		
		
		branch.setBranchName(name);
		branch.setBranchAddres(address);
		branch.setBranchId(branchId);
		try {
			servic.editBranch(branch);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		String message = "Branch edited successfully";
		model.addAttribute("message",message);
		return "librarian";
	}
	@RequestMapping(value = "/addcopies", method = RequestMethod.GET)
	public String addCopies(@RequestParam("branchId")Integer branchId, Model model) {	
			
			model.addAttribute("branchId", branchId);
			return "addcopies";
		
		
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addC(@RequestParam("branchId")Integer branchId,@RequestParam("bookId")Integer bookId, Model model) {	
			
			model.addAttribute("branchId", branchId);
			model.addAttribute("bookId", bookId);
			return "add";
		
		
	}
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String book(Model model) {	

			return "book";

	}
	@RequestMapping(value = "/branch", method = RequestMethod.GET)
	public String branch(Model model) {	

			return "branch";

	}
	@RequestMapping(value = "/publisher", method = RequestMethod.GET)
	public String publisher(Model model) {	

			return "publisher";

	}
	@RequestMapping(value = "/author", method = RequestMethod.GET)
	public String author(Model model) {	

			return "author";

	}
	@RequestMapping(value = "/addCopi", method = RequestMethod.POST)
	public String add(@RequestParam("branchId")Integer branchId,@RequestParam("bookId")Integer bookId,
			@RequestParam("copies")Integer copies,Model model) {	
			
		
	
		bookCopies bc = new bookCopies();
		
		try {
			
			bc.setBookId(bookId);
			bc.setBranchId(branchId);
			bc.setNoOfCopies(copies);
			service.addCopies(bc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String message="copies added successfully";
			model.addAttribute("branchId",branchId);
			model.addAttribute("message",message);
			return "addcopies";
		
		
	}

}
