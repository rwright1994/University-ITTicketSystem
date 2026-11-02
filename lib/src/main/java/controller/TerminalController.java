package controller;

import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Optional;

import models.User;
import repository.InMemoryTicketRepository;
import repository.InMemoryUserRepository;
import repository.TicketRepository;
import repository.UserRepository;
import services.TicketIssuingService;
import services.UserCreationService;

public class TerminalController {

	private boolean running;
	private final Scanner scanner = new Scanner(System.in);
	
	private final TicketIssuingService ticketIssuingService;
	private final UserCreationService userCreationService;
	private final TicketRepository ticketRepository;
	private final UserRepository userRepository;
	
	private final Map<String, Runnable> commands;
	
	public TerminalController(TicketIssuingService ticketIssuingService, UserCreationService userCreationService, TicketRepository ticketRepository, UserRepository userRepository) {
		
		this.ticketIssuingService = ticketIssuingService;
		this.userCreationService = userCreationService;
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
		
		
		this.commands = Map.of(
				"help", this::showHelp,
				"find-user", this::handleFindUser,
				"submit-ticket", this::handleCreateTicket,
				"create-account", this::handleCreateUser,
				"login", this::login,
				"exit", this::exit
			);
		running = true;
	}
	
	
	public void showTitle() {
		System.out.println("""
			    ╔════════════════════════════════════════════════════════════════════════╗
			    ║                                                                        ║
			    ║  ██╗   ██╗████████╗██╗ ██████╗██╗  ██╗███████╗███████╗███████╗██████╗  ║
			    ║  ██║   ██║╚══██╔══╝██║██╔════╝██║ ██╔╝██╔════╝██╔════╝██╔════╝██╔══██╗ ║
			    ║  ██║   ██║   ██║   ██║██║     █████╔╝ █████╗  █████╗  █████╗  ██████╔╝ ║
			    ║  ██║   ██║   ██║   ██║██║     ██╔═██╗ ██╔══╝  ██╔══╝  ██╔══╝  ██╔══██╗ ║
			    ║  ╚██████╔╝   ██║   ██║╚██████╗██║  ██╗███████╗███████╗███████╗██║  ██║ ║
			    ║   ╚═════╝    ╚═╝   ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝╚═╝  ╚═╝ ║
			    ║                                                                        ║
			    ║                          U T I C K E T E E R                           ║
			    ║                                                                        ║
			    ║                      CAMPUS IT SERVICE MANAGEMENT                      ║
			    ║                                                                        ║
			    ╚════════════════════════════════════════════════════════════════════════╝
			    """);
	}
	
	public String requestInput() {
		 String input = scanner.nextLine();
		 return input;
	}
	
	public void handleInput(String input) {
		
		Runnable command = commands.get(input.toLowerCase().trim());
		

		if(command == null) {
			System.out.println("Please enter a valid command.");
			return;
		}
		command.run();
	}
	
	
	private void handleCreateTicket() {
		
		System.out.print("Enter title:");
		String title = scanner.nextLine();
		System.out.println("What type of issue are you having? (Software, Hardware, Internet or Application)");
		System.out.print("Enter a brief description of the issue:");
		String description = scanner.nextLine();
		
		
	}
	
	private void handleFindUser() {
		System.out.print("Enter the user's ID:");
		int id = scanner.nextInt();
		findUserById(id);
	}
	
	private void handleCreateUser() {
		System.out.print("Enter your first Name:");
		String fn = scanner.nextLine();
		System.out.print("Enter your last name:");
		String ln = scanner.nextLine();
		System.out.print("Enter your email address:");
		String email = scanner.nextLine();
		System.out.print("Enter your password:");
		String pw = scanner.nextLine();
		User testUser = userCreationService.createUser(fn, ln, email, pw);
		userRepository.save(testUser);
		
		System.out.println("Successfully create a new user");
		System.out.println(testUser);
	}
	
	private void viewTicketList() {
		
	}
	
	private void login() {
		
		
	}
	
	private void exit() {
		this.running = false;
		System.out.println("Exitting the system.....");
		System.out.println("Thanks for using University Ticketeer! A fast and easy way to submit IT Support Tickets!");
		scanner.close();
	}
	

	public boolean isRunning() {
		return running;
	}
	
	private void showHelp() {
		System.out.println("------ Available Commands -------");
		System.out.println("  help - Show available commands.");
		System.out.println("  new - Submit a new ticket.     ");
		System.out.println("  list - Open list menu.         ");
		System.out.println("  exit - Exit application/       ");
	}
	
	private void findUserById(int id) {
		Optional<User> foundUser = userRepository.findUserById(id);
		System.out.println(foundUser);
	}
	
	
}
