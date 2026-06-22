

	package com.bank.bankingApp.controller;

	import com.bank.bankingApp.entity.Account;
	import com.bank.bankingApp.service.BankingService;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import jakarta.servlet.http.HttpSession;

	@Controller
	public class BankingController {

	    @Autowired
	    private BankingService bankingService;

	    @GetMapping("/")
	    public String showLoginPage(HttpSession session) {
	        if (session.getAttribute("loggedInUser") != null) {
	            return "redirect:/dashboard";
	        }
	        return "login"; 
	    }

	    @PostMapping("/login")
	    public String handleLogin(@RequestParam String accountNumber,
	                              @RequestParam String password,
	                              HttpSession session,
	                              Model model) {

	        Account account = bankingService.authenticate(accountNumber, password);

	        if (account != null) {
	            session.setAttribute("loggedInUser", account);
	            return "redirect:/dashboard";
	        }

	        model.addAttribute("error", "Invalid Account Number or Password!");
	        return "login";
	    }

	    @GetMapping("/dashboard")
	    public String showDashboard(HttpSession session, Model model) {
	        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
	        if (loggedInUser == null) {
	            return "redirect:/"; // 
	        }
	        
	        model.addAttribute("accountName", loggedInUser.getAccountHolderName());
	        model.addAttribute("balance", loggedInUser.getBalance());
	        
	        if (session.getAttribute("error") != null) {
	            model.addAttribute("error", session.getAttribute("error"));
	            session.removeAttribute("error"); 
	        }
	        
	        return "dashboard"; // 
	    }

	    @PostMapping("/deposit")
	    public String handleDeposit(@RequestParam double amount, HttpSession session) {
	        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
	        if (loggedInUser != null) {
	            bankingService.deposit(loggedInUser, amount);
	        }
	        return "redirect:/dashboard";
	    }

	    @PostMapping("/withdraw")
	    public String handleWithdraw(@RequestParam double amount, HttpSession session) {
	        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
	        if (loggedInUser != null) {
	            boolean success = bankingService.withdraw(loggedInUser, amount);
	            if (!success) {
	                session.setAttribute("error", "Insufficient balance! शिल्लक रक्कम अपुरी आहे.");
	            }
	        }
	        return "redirect:/dashboard";
	    }

	    @GetMapping("/logout")
	    public String handleLogout(HttpSession session) {
	        session.invalidate(); // 
	        return "redirect:/";
	    }
	}

