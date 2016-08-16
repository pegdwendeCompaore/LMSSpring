package com.gcit.lms;

import java.security.Provider.Service;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Loans;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;


@Controller
public class Admincontroller {	
	
	@Autowired
	AdminService Service;
	@RequestMapping(value = "/adminmenu", method = RequestMethod.GET)
	public String Admin(Locale locale, Model model) {	

		
		return "adminmenu";
	}

	@RequestMapping(value = "/addauthor", method = RequestMethod.GET)
	public String addAuthor(Locale locale, Model model) {	
		
		
		return "addauthor";
	}
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String addBook(Locale locale, Model model) {	
		
		
		return "addbook";
	}
	@RequestMapping(value = "/addborrower", method = RequestMethod.GET)
	public String addBorrower(Locale locale, Model model) {	
	
		
		return "addborrower";
	}
	@RequestMapping(value = "/addbranch", method = RequestMethod.GET)
	public String addBranch(Locale locale, Model model) {	
		
		
		return "addbranch";
	}
	@RequestMapping(value = "/addpublisher", method = RequestMethod.GET)
	public String addPublisher(Locale locale, Model model) {	
		
		
		return "addpublisher";
	}
	@RequestMapping(value = "/addAuthor", method = RequestMethod.POST)
	public String addAuthor(@RequestParam("authorName")String name, @RequestParam(value="booksId",required=false)String [] booksId, Model model) {	
		
		
		List<Book>books = new ArrayList<Book>();
		
		
		for(int i=0; i<booksId.length; i++)
		{
			Book book = new Book();
		book.setBookId(Integer.parseInt(booksId[i]));
			System.out.println(booksId[i]);
			books.add(book);
			
		}
		
		name = name.replaceAll("<", "at");
		Author author = new Author();
		author.setAuthorName(name);
		author.setBooks(books);
		
		
		

	try {
			Service.addAuthor(author);
	} catch (SQLException e) {
		
		e.printStackTrace();
		}
	String message = "Author added successfully";
	model.addAttribute("message",message);
		return "viewauthors";
	}
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@RequestParam("bookName")String name, @RequestParam(value="authors",required=false)String[]authors,
			@RequestParam("publishers")Integer pubId, @RequestParam(value="genres",required=false)String [] genres, Model model) {	
		
		List <Author> Authors = new ArrayList<Author>();
		List <Genre> Genre = new ArrayList<Genre>();
		
		for(int i=0; i<authors.length; i++)
		{
		 Author author = new Author();
		 author.setAuthorID(Integer.parseInt(authors[i]));
		
		
			Authors.add(author);
			
		}
		 System.out.println("succees");
		for(int i=0; i<genres.length; i++)
		{
		 Genre genre = new Genre();
		 genre.setGenreId(Integer.parseInt(genres[i]) );
			Genre.add(genre);
		}
		
		Book book = new Book();
		name = name.replaceAll("<", "at");
		book.setTitle(name);
		book.setPubId(pubId);
		book.setGenres(Genre);
		book.setAuthors(Authors);
		
		try {


			 Service.addBook(book);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		String message = "Book added successfully";
		model.addAttribute("message",message);
		return "viewbook";
	}
	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST)
	public String addborrower(@RequestParam("borrowerName")String name,@RequestParam(value="borrowerAddress",required=false)String address,
			@RequestParam(value="borrowerPhone",required =false)String phone,  Model model) {	
		name = name.replaceAll("<", "at");
		address = address.replace("<", "at");
		phone = phone.replace("<", "at");
		Borrower borrowe = new Borrower();
		borrowe.setBorrowerName(name);
		borrowe.setBorrowerAddress(address);
		borrowe.setBorrowerPhone(phone);
		
		try {
			 Service.addBorrower(borrowe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = "Client added successfully";
		model.addAttribute("message",message);
		return "viewborrower";
	}
	
	@RequestMapping(value = "/addBranch", method = RequestMethod.POST)
	public String addBranch(@RequestParam("branchName")String name,@RequestParam("branchAddress")String address,
			@RequestParam(value="booksId", required =false)String [] booksId, Model model) {	
		
		List <Book> Books = new ArrayList<Book>();

		for(int i=0; i<booksId.length; i++)
		{
			Book books = new Book();
			books.setBookId(Integer.parseInt(booksId[i]));
			Books.add(books);

		}
		Branch branch = new Branch();
		name = name.replaceAll("<", "at");
		address =address.replace("<", "at");
		branch.setBranchName(name);
		branch.setBranchAddres(address);
		branch.setBooks(Books);

		try {

			Service.addBranch(branch);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String message = "Branch added successfully";
		model.addAttribute("message",message);
		return "viewbranch";
	}
	
	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST)
	public String addPublisher(@RequestParam("publisherName")String name,@RequestParam(value="publisherAddress",required=false)String address,
			@RequestParam(value="publisherPhone",required =false)String phone, Model model) {	
		
		Publisher publisher = new Publisher();
		name = name.replaceAll("<", "at");
		address = address.replace("<", "at");
		phone = phone.replace("<", "at");
		publisher.setPublisherName(name);
		publisher.setPublisherAddress(address);
		publisher.setPublisherPhone(phone);
		try {
			 
			 Service.addPublisher(publisher);;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String message = "Publisher added successfully";
		model.addAttribute("message",message);
		return "viewpublisher";
	}
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.GET)
	public String deleteAhthor(@RequestParam("authorId") Integer authorId, Model model) {	
	
		Author author = new Author();
		author.setAuthorID(authorId);
		try {
			Service.deleteAuthor(author);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		String message = "Author deleted successfully";
		model.addAttribute("message",message);
		return "viewauthors";
	}
	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String deleteBook(@RequestParam("bookId")Integer bookId, Model model) {	
		
			Book book = new Book();
			book.setBookId(bookId);
			try {
				Service.deleteBook(book);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

			String message = "Book deleted successfully";
			model.addAttribute("message",message);
			
		return "viewbook";
	}
	
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.GET)
	public String deleteBorrower(@RequestParam("cardNo")Integer cardNo, Model model) {	
		

		Borrower borrower = new Borrower();
		borrower.setCarNo(cardNo);
		try {
			Service.deleteBorrower(borrower);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = "Borrower deleted successfully";
		model.addAttribute("message",message);
		return "viewborrower";
	}
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.GET)
	public String deleteBranch(@RequestParam("branchId")Integer branchId, Model model) {	
		
			Branch branch = new Branch();
			branch.setBranchId(branchId);
			try {
				Service.deleteBranch(branch);
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String message = "Branch deleted successfully";
			model.addAttribute("message",message);
		return "viewbranch";
	}
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.GET)
	public String deletePublisher(@RequestParam("pubId")Integer pubId, Model model) {	
		
			Publisher pub = new Publisher();
			pub.setPublisherId(pubId);
			try {
				Service.deletePublisher(pub);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String message = "Publisher deleted successfully";
			model.addAttribute("message",message);
		return "viewpublisher";
	}
	@RequestMapping(value = "/editauthor", method = RequestMethod.GET)
	public String editAu( Model model) {	
	
		return "editauthor";
	}
	
	@RequestMapping(value = "/editAuthor", method = RequestMethod.POST)
	public String editAuthor( @RequestParam("authorId") Integer authorId, 
			
			
			@RequestParam(value="booksId",required=false) String[] books,@RequestParam("authorName") String authorName,Model model) {	

		Author author = new Author();
		if (books!=null)
		{
			
			List<Book>books3 = new ArrayList<Book>();


			for(int i=0; i<books.length; i++)
			{
				Book book = new Book();
				book.setBookId(Integer.parseInt(books[i]));
				books3.add(book);

			}
			
		author.setBooks(books3);
		}
		authorName = authorName.replaceAll("<", "at");
		author.setAuthorName(authorName);
		author.setAuthorID(authorId);
		
		try {
			Service.editAuthor(author); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String message = "author edited successfully";
		model.addAttribute("message",message);
		
		return "viewauthors";
	}
	@RequestMapping(value = "/editbook", method = RequestMethod.GET)
	public String editBook(Locale locale, Model model) {	
		
		
		return "editbook";
	}
@RequestMapping(value = "/editBook", method = RequestMethod.POST)
public String editBook( @RequestParam("bookId") Integer bookId, 
			@RequestParam(value="authors",required =false) String[] authorsId,@RequestParam("title") String title,
			@RequestParam(value="genres",required=false) String[] genresId,@RequestParam(value="publishers",required=false) Integer pubId,Model model) {	
	Book book = new Book();

	List <Author> edAuthors = new ArrayList<Author>();
	List <Genre> edGenres = new ArrayList<Genre>();
	if(authorsId!=null)
	{
		
	for(int i=0; i<authorsId.length; i++)
	{
	 Author author = new Author();
	 author.setAuthorID(Integer.parseInt(authorsId[i]));
	
	
		edAuthors.add(author);
		
	}
	book.setAuthors(edAuthors);
	}
	if(genresId!=null)
	{
		
	for(int i=0; i<genresId.length; i++)
	{
	 Genre genre = new Genre();
	 genre.setGenreId(Integer.parseInt(genresId[i]) );
		edGenres.add(genre);
	}
	book.setGenres(edGenres);
	}
	
	
	
	title = title.replace("<", "at");
	book.setTitle(title);
	book.setBookId(bookId);
	book.setPubId(pubId);

	try {
		Service.editBook(book);
	
	;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	String message = "book edited successfully";
	model.addAttribute("message",message);
	return "viewbook";
}
	@RequestMapping(value = "/editborrower", method = RequestMethod.GET)
	public String editBorrower(Locale locale, Model model) {	
		return "editborrower";
	}
	@RequestMapping(value = "/editBorrower", method = RequestMethod.POST)
	public String editBorrower(@RequestParam("name")String name, @RequestParam("address") String address, 
			@RequestParam("phone")String phone, @RequestParam("cardNo") Integer cardNo, Model model) {	
		
		Borrower borrower = new Borrower();
		borrower.setBorrowerName(name);
		borrower.setBorrowerAddress(address);
		borrower.setBorrowerPhone(phone);
		borrower.setCarNo(cardNo);
		try {
			Service.editBorrower(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String message = "borrower edited successfully";
		model.addAttribute("message",message);
		return "viewborrower";
	}
	@RequestMapping(value = "/editbranch", method = RequestMethod.GET)
	public String editBranch(Locale locale, Model model) {	
		
		return "editbranch";
	}
	@RequestMapping(value = "/branchselection", method = RequestMethod.GET)
	public String overwrite(Locale locale, Model model) {	
		
		return "branchselection";
	}
	@RequestMapping(value = "/borrowerselection", method = RequestMethod.GET)
	public String overwrite2(Locale locale, Model model) {	
		
		return "borrowerselection";
	}
	@RequestMapping(value = "/bookselection", method = RequestMethod.GET)
	public String overwrite3(Locale locale, Model model) {	
		
		return "bookselection";
	}
	@RequestMapping(value = "/overwrite", method = RequestMethod.GET)
	public String over(@RequestParam("branchId")Integer branchId, @RequestParam("cardNo")Integer cardNo,
			@RequestParam("bookId")Integer bookId, Model model) {	
		
		Loans loans = new Loans();
		loans.setBookId(bookId);
		loans.setBranchId(branchId);
		loans.setCardNo(cardNo);
		try {
			Service.overWrite(loans);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String message ="over write successfull";
		model.addAttribute("message",message);
		
		return "bookselection";
	}
	@RequestMapping(value = "/editBranch", method = RequestMethod.POST)
	public String editBranch(@RequestParam(value="name",required =false)String name, @RequestParam(value="address",required =false)String address, 
			@RequestParam("branchId")Integer branchId, @RequestParam(value="booksId",required=false)String [] booksId, Model model) {	
		
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
		
		name = name.replaceAll("<", "at");
		address = address.replace("<", "at");
		
		branch.setBranchName(name);
		branch.setBranchAddres(address);
		branch.setBranchId(branchId);
		
		try {
			Service.editBranch(branch);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		String message = "Branch edited successfully";
		model.addAttribute("message",message);
		return "viewbranch";
	}
	@RequestMapping(value = "/editpublisher", method = RequestMethod.GET)
	public String editPublisher(Locale locale, Model model) {	
		
		
		return "editpublisher";
	}
	@RequestMapping(value = "/editPublisher", method = RequestMethod.POST)
	public String editPublisher(@RequestParam(value="name",required=false) String name, @RequestParam(value="address", required=false)String address, @RequestParam(value="phone",required =false)String phone,
			
			@RequestParam("pubId") Integer pubId, Model model) {	
		
		Publisher publisher = new Publisher();
		name = name.replaceAll("<", "at");
		address = address.replace("<", "at");
		phone = phone.replace("<", "at");
		publisher.setPublisherName(name);
		publisher.setPublisherAddress(address);
		publisher.setPublisherPhone(phone);
		publisher.setPublisherId(pubId);
		
		try {
			Service.editPublisher(publisher);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = "Publisher edited successfully";
		model.addAttribute("message",message);
		return "viewpublisher";
	}
	
	@RequestMapping(value = "/viewauthors", method = RequestMethod.GET)
	public String viewAuthors(Locale locale, Model model) {	
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "viewauthors";
	}
	@RequestMapping(value = "/viewbook", method = RequestMethod.GET)
	public String viewBook(Locale locale, Model model) {	
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "viewbook";
	}
	
	@RequestMapping(value = "/viewborrower", method = RequestMethod.GET)
	public String viewBorrower(Locale locale, Model model) {	
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "viewborrower";
	}
	@RequestMapping(value = "/viewbranch", method = RequestMethod.GET)
	public String viewBranch(Locale locale, Model model) {	
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "viewbranch";
	}
	@RequestMapping(value = "/viewpublisher", method = RequestMethod.GET)
	public String viewPublisher(Locale locale, Model model) {	
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "viewpublisher";
	}

}
