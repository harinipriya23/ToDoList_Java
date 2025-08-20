package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

import dbConnection.DBconnection;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TaskCRUD taskCrud = new TaskCRUD();

		while (true) {
			System.out.println("|-----------|---------|");
			System.out.println("Today's To Do List");
			System.out.println("|-----------|---------|");
			System.out.println("1. Create new Task");
			System.out.println("2. View Tasks");
			System.out.println("3. Update Task");
			System.out.println("4. Delete Task");
			System.out.println("5. Exit");
			System.out.println("|-----------|---------|");
			System.out.println("Choose a option : ");

			int selectedOption = scanner.nextInt();
			scanner.nextLine();

			switch (selectedOption) {

			case 1: {
				System.out.println("Enter Task");
				String task = scanner.nextLine();

				if (task == null || task.trim().isEmpty()) {
					System.out.println("Task cannot be empty!");
					return;
				}

				System.out.println("Enter description");
				String description = scanner.nextLine();

				taskCrud.createNewTask(task, description);
				break;
			}
			case 2: {
				System.out.println("Task List");
				List<Task> list = taskCrud.getAllTask();

				int sno = 1;
				for (Task t : list) {
					System.out.println(sno + ". " + t);
					sno++;
				}
				break;
			}
			case 3: {
				System.out.println("Select Task to Update");
				List<Task> list = taskCrud.getAllTask();
				Map<Integer, Integer> map = new HashMap<Integer, Integer>();

				int sno = 1;
				for (Task t : list) {
					System.out.println(sno + ". " + t);
					map.put(sno, t.getId());
					sno++;
				}
				System.out.println("Enter Task ID : ");
				int id = scanner.nextInt();
				scanner.nextLine();

				Integer key = map.get(id);

				System.out.println("A. Update Task");
				System.out.println("B. Update description");
				System.out.println("C. Update Status (True/False)");
				System.out.println("D. Update All");

				String opt = scanner.nextLine();

				String task = "";
				String description = "";
				boolean status = false;

				switch (opt.toUpperCase()) {
				case "A":
					System.out.println("Update Task");
					task = scanner.nextLine();
					break;
				case "B":
					System.out.println("Update description");
					description = scanner.nextLine();
					break;
				case "C":
					System.out.println("Update Status (True/False)");
					status = Boolean.parseBoolean(scanner.nextLine());
					break;
				case "D":
					System.out.println("Update Task");
					task = scanner.nextLine();
					System.out.println("Update description");
					description = scanner.nextLine();
					System.out.println("Update Status (True/False)");
					status = Boolean.parseBoolean(scanner.nextLine());
					break;
				default:
					System.out.println("|---------Invalid Option!--------|");
					break;
				}
				taskCrud.updateTask(task, description, status, key);
				break;
			}
			case 4: {
				System.out.println("Selete Task to Delete");

				List<Task> list = taskCrud.getAllTask();
				if (list.isEmpty()) {
					System.out.println("|----------No tasks available----------|");
					break;
				}

				Map<Integer, Integer> map = new HashMap<Integer, Integer>();

				int sno = 1;
				for (Task t : list) {
					System.out.println(sno + ". " + t);
					map.put(sno, t.getId());
					sno++;
				}
				System.out.println("Enter Task ID : ");
				int id = scanner.nextInt();
				scanner.nextLine();

				Integer key = map.get(id);
				System.out.println(key + "," + id);

				taskCrud.deleteTask(key);
				break;
			}
			case 5: {
				System.out.println("Exiting!");
				return;
			}
			default:
				System.out.println("Invalid Option");
			}

		}

	}
}
