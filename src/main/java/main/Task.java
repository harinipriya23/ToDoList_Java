package main;

public class Task {

	private int id;
	private String task;
	private boolean status;
	private String description;

	// constructors
	public Task(String task) {
		this.task = task;
		this.description = "No description";
		this.status = false;
	}

	public Task(String task, String description) {
		this.task = task;
		this.description = description;
		this.status = false;
	}

	public Task(Integer id, String task, String description, boolean status) {
		this.id = id;
		this.task = task;
		this.description = description;
		this.status = status;
	}

	// getters
	public int getId() {
		return id;
	}

	public String getTaskName() {
		return task;
	}

	public boolean getStatus() {
		return status;
	}

	// setters
	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String des = (description.isEmpty()? "No description mentioned" : description);
		return  task + " | " + des  + " | " + (status ? "Done" : "Pending");
	}
}
