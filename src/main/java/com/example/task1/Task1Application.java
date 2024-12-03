package com.example.task1;

import com.example.task1.domain.Task;
import com.example.task1.service.OwnerService;
import com.example.task1.service.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Task1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Task1Application.class, args);

		LoginManager loginManager = context.getBean(LoginManager.class);
		TaskService taskService = context.getBean(TaskService.class);

		Scanner scanner = new Scanner(System.in);

		while(true) {

			loginManager.printMenu();

			System.out.print("Choose an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			System.out.println();

			switch (choice) {
				case 1: //login
					System.out.print("Enter name: ");
					String name = scanner.next();
					scanner.nextLine();
					System.out.println();

					System.out.print("Enter password: ");
					String password = scanner.next();
					scanner.nextLine();
					System.out.println();

					if(loginManager.login(name, password)) {
						boolean loggedIn = true;
						while(loggedIn) {
							loginManager.printLoggedInMenu();
							choice = scanner.nextInt();
							scanner.nextLine();

							switch (choice) {
								case 1:
									System.out.print("Enter task id: ");
									long taskId = scanner.nextLong();
									scanner.nextLine();
									System.out.print("\nEnter new effort in hours: ");
									int newEffort = scanner.nextInt();
									scanner.nextLine();
									System.out.println();

									try{
										Task updatedTask = taskService.updateEffort(taskId, newEffort);
										System.out.println("Task updated succesfully.");
									} catch (IllegalArgumentException e) {
										System.out.println("Error updating task: " + e.getMessage());
									}
									break;

								case 2:
									System.out.println("Logging out...");
									loggedIn = false;
									break;

								default:
									System.out.println("Invalid option. Please try again!");
							}
						}
					}
					break;

				case 2: //Register
					System.out.println("Will be implemented");
					break;

				case 3: //Exit
					System.out.println("Goodbye!");
					System.exit(0);
					break;

				default:
					System.out.println("Invalid option. Please try again!");

			}
		}
	}

}
