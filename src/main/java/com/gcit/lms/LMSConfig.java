package com.gcit.lms;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.gcit.lms.dao.*;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibrarianService;


@Configuration
public class LMSConfig {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/library";
	private static String user = "root";
	private static String pass = "";
	
	@Bean
	public BasicDataSource dataSource() {
	
	BasicDataSource ds = new BasicDataSource();
	ds.setDriverClassName(driver);
	ds.setUrl(url);
	ds.setUsername(user);
	ds.setPassword(pass);
	return ds;
	}
	
	@Bean
	public JdbcTemplate template(){
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource());
		return template;
	}
	
	@Bean
	public AuthorDAO aDao(){
		return new AuthorDAO();
	}
	@Bean
	public BookDAO bDao(){
		return new BookDAO();
	}
	@Bean
	public GenreDAO gDao(){
		return new GenreDAO();
	}
	@Bean
	public BorrowerDAO boDao(){
		return new BorrowerDAO();
	}
	@Bean
	public BranchDAO brDao(){
		return new BranchDAO();
	}
	@Bean
	public BookCopiesDAO bcDao(){
		return new BookCopiesDAO();
	}
	@Bean
	public PublisherDAO pubDao(){
		return new PublisherDAO();
	}
	@Bean
	public LoansDAO lDao(){
		return new LoansDAO();
	}
	
	@Bean(name = "AdminService")
	public AdminService adminService(){
		return new AdminService();
	}
	@Bean(name = "BorrowerService")
	public BorrowerService borrowerService(){
		return new BorrowerService();
	}
	
	@Bean(name = "LibrarianService")
	public LibrarianService librarianService(){
		return new LibrarianService();
	}
	@Bean 
	PlatformTransactionManager txManager()
	{
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(dataSource());
		return tx;
	}
}
