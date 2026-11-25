package _ITTicketSystem.controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import _ITTicketSystem.models.Employee;
import _ITTicketSystem.models.Note;
import _ITTicketSystem.models.NoteVisibility;
import _ITTicketSystem.models.Student;
import _ITTicketSystem.models.Technician;
import _ITTicketSystem.models.Ticket;
import _ITTicketSystem.models.TicketCategory;
import _ITTicketSystem.models.User;
import _ITTicketSystem.services.RegularTerminalPolicy;
import _ITTicketSystem.services.TechnicianTerminalPolicy;
import _ITTicketSystem.services.TerminalPolicy;
import _ITTicketSystem.services.TicketIssuingService;
import _ITTicketSystem.services.TicketNoteService;
import _ITTicketSystem.services.UserCreationService;
import _ITTicketSystem.utils.TerminalFormatter;
import _ITTicketSystem.utils.TerminalTicketFormatter;
import _ITTicketSystem.utils.TerminalNoteFormatter;
import _ITTicketSystem.utils.TerminalUserFormatter;


public class TerminalController {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    
	private boolean running;
	private final Scanner scanner = new Scanner(System.in);
	
	private final TicketIssuingService ticketIssuingService;
	private final UserCreationService userCreationService;
	private final TicketNoteService ticketNoteService;
	private TerminalPolicy terminalPolicy;
	

	private TerminalFormatter<Ticket> ticketFormatter;
	private TerminalFormatter<User> userFormatter;
	private TerminalFormatter<Note> noteFormatter;

	private final Map<String, Runnable> commands;
	private User currUser;
	
	
	public TerminalController(TicketIssuingService ticketIssuingService, TicketNoteService ticketNoteService, UserCreationService userCreationService) {
		
		this.ticketIssuingService = ticketIssuingService;
		this.ticketNoteService = ticketNoteService;
		this.userCreationService = userCreationService;
		this.commands = Map.of(
				"help", this::showHelp,
				"create-account", this::handleCreateUser,
				"submit-ticket", this::handleSubmitTicket,
				"add-note", this::handleAddNote,
				"find", this::handleFind,
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
	
	private void showHelp() {
		setTextColor(ANSI_YELLOW);
		terminalPolicy.displayHelp();
		resetTextColor();
	}
	
	private void handleCreateUser() {
		
		String fn,ln,accountType;
		
		System.out.print("Enter the first Name:");
		setTextColor(ANSI_RED);
		fn = scanner.nextLine();
		resetTextColor();
	
		System.out.print("Enter the last name:");
		setTextColor(ANSI_RED);
		ln = scanner.nextLine();
		resetTextColor();
			
		System.out.print("Is this a student or employee account? (Enter 'S' for student, 'E' for employee, and T for Technician)");
		setTextColor(ANSI_RED);
		accountType = scanner.nextLine();
		resetTextColor();
		
		if(!accountType.equalsIgnoreCase("e") && !accountType.equalsIgnoreCase("s") && !accountType.equalsIgnoreCase("t")) {
			throw new IllegalArgumentException("Account code not recognized. Please try again!");
		}
		
		User newUser = null;
		
		
		switch(accountType.toLowerCase()) {
			case "s":
				newUser = userCreationService.createStudent(fn, ln);
				userCreationService.save(newUser);
			case "e":
				newUser = userCreationService.createEmployee(fn, ln);
				userCreationService.save(newUser);
			case "t":
				newUser = userCreationService.createTechnician(fn, ln);
				userCreationService.save(newUser);
		}
	}
	
	
	
	private void handleFind() {
		
		System.out.println("What are you searching for?");
		setTextColor(ANSI_YELLOW);
		terminalPolicy.displayFind();
		resetTextColor();
		
		System.out.print("Enter selection:");
		setTextColor(ANSI_RED);
		String selection = scanner.nextLine();
		resetTextColor();
		
		if(selection.equalsIgnoreCase("ticket")) {
			System.out.print("Ticket ID:");
			setTextColor(ANSI_RED);
			String ticketId = scanner.nextLine();
			resetTextColor();
			Ticket foundTicket = ticketIssuingService.findTicketById(ticketId).orElseThrow( () -> new RuntimeException("No ticket found matching the provided ID."));
			
			setTextColor(ANSI_GREEN);
			System.out.println(ticketFormatter.format(foundTicket));
			if(foundTicket.getNotes() != null) {
				
				List<Note> ticketNotes = foundTicket.getNotes();
				System.out.println(noteFormatter.format(ticketNotes));
			}
			resetTextColor();
			
		
		}else if(selection.equalsIgnoreCase("all-tickets")) {
			setTextColor(ANSI_GREEN);
			List<Ticket> tickets = ticketIssuingService.findAllTickets();
			System.out.println(ticketFormatter.format(tickets));
			resetTextColor();
		}else if(selection.equalsIgnoreCase("all-users") && terminalPolicy.isAuthorizedCommand(selection)) {
			setTextColor(ANSI_GREEN);
			List<User> users = userCreationService.findAllUsers();
			System.out.println(userFormatter.format(users));
			resetTextColor();
		}else if(selection.equalsIgnoreCase("user") && terminalPolicy.isAuthorizedCommand(selection)) {
			System.out.print("User ID:");
			setTextColor(ANSI_RED);
			String userId = scanner.nextLine();
			resetTextColor();
			User foundUser = userCreationService.findUserById(userId).orElseThrow( () -> new RuntimeException("No user found matching the provide ID."));
			System.out.println(foundUser);
		}
	}
	
	
	private void handleSubmitTicket() {
		System.out.print("Enter your student/employee ID:");
		setTextColor(ANSI_RED);
		String id = scanner.nextLine();
		resetTextColor();
		
		System.out.println("What type of issue are you having?");
		System.out.println("0 - Software");
		System.out.println("1 - Hardware");
		System.out.println("2 - Network");
		System.out.println("3 - Personal Device");
		System.out.print("Enter option:");
		setTextColor(ANSI_RED);
		TicketCategory ticketCategory = null;
		int selection = scanner.nextInt();
		scanner.nextLine();
		
		switch(selection) {
			case 0:
				ticketCategory = TicketCategory.SOFTWARE;
			case 1:
				ticketCategory = TicketCategory.HARDWARE;
			case 2:
				ticketCategory = TicketCategory.NETWORK;
			case 3:
				ticketCategory = TicketCategory.PERSONAL_DEVICE;
		}
		
		resetTextColor();
		System.out.print("Enter title:");
		setTextColor(ANSI_RED);
		String title = scanner.nextLine();
		resetTextColor();
	
		
		System.out.println("Enter a brief description of the issue:");
		setTextColor(ANSI_RED);
		String description = scanner.nextLine();
		resetTextColor();
		
		User foundUser = userCreationService.findUserById(id).orElseThrow( () -> new RuntimeException("No user found matching the provide ID."));
		
		if(foundUser != null) {
			System.out.println(foundUser);
			Ticket ticket = ticketIssuingService.issueTicket(title,description, ticketCategory,foundUser);
			System.out.println(ticket);
			ticketIssuingService.save(ticket);
		}
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
	
	public void handleAddNote() {
		NoteVisibility visibility = null;
		System.out.println("What ticket would you like to add a note to? (Enter ticket id)");
		setTextColor(ANSI_RED);
		String ticketId = scanner.nextLine();
		resetTextColor();
		
		if(terminalPolicy instanceof TechnicianTerminalPolicy) {
			System.out.println("Is this note for internal use Y/N?");
			setTextColor(ANSI_RED);
			String input = scanner.nextLine();
			if(input.equalsIgnoreCase("y")) {
				visibility = NoteVisibility.INTERNAL;
			}else {
				visibility = NoteVisibility.EXTERNAL;
			}
			resetTextColor();
		}
		
		System.out.println("Enter new details for the note:");
		setTextColor(ANSI_RED);
		String content = scanner.nextLine();
		resetTextColor();
		int num = ticketIssuingService.findTicketById(ticketId).orElseThrow( () -> new RuntimeException("No ticket found matching the provided ID.")).getNotes().size() +1;
		Note note = ticketNoteService.create(Integer.toString(num),content, currUser, visibility);
		ticketNoteService.addNote(ticketId, note);
		
	}
	
	
	private void setTextColor(String ansi) {
		System.out.print(ansi); 
	}
	
	private void resetTextColor() {
		System.out.print(ANSI_RESET);
	}
	
	public void handleLogin() {
		System.out.print("Enter your ID: ");
		setTextColor(ANSI_RED);
		String loginId = scanner.nextLine();
		resetTextColor();
		User foundUser = userCreationService.findUserById(loginId).orElseThrow(() -> new RuntimeException("No user found matching the provide ID."));
		
		if(foundUser != null) {
			currUser = foundUser;
			if(foundUser instanceof Technician) {
				terminalPolicy = new TechnicianTerminalPolicy();
				System.out.println("Created technician policy");
			}else if ((foundUser instanceof Student || foundUser instanceof Employee)){
				terminalPolicy = new RegularTerminalPolicy();
				System.out.println("Created regular policy");
			}
		}
		
		ticketFormatter = new TerminalTicketFormatter();
		userFormatter = new TerminalUserFormatter();
		noteFormatter = new TerminalNoteFormatter(terminalPolicy);
		
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
		
		if(!terminalPolicy.isAuthorizedCommand(input)) {
			System.out.println("Command not authorized");
			return;
		}
		command.run();
	}
	
}
