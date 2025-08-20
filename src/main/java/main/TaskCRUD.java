package main;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbConnection.DBconnection;

public class TaskCRUD {

	PreparedStatement prstmt = null;
	Statement stmt = null;
	ResultSet res = null;

	// Create
	public void createNewTask(String task, String description) {

		String newTaskQuery = "INSERT INTO TASK (task, description) values (?,?)";

		try (Connection con = DBconnection.getConnection()) {

			prstmt = con.prepareStatement(newTaskQuery);

			prstmt.setString(1, task);
			prstmt.setString(2, description);

			int rows = prstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("|----------Task created successfully!----------|");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Read
	public List<Task> getAllTask() {
		String getTaskQuery = "SELECT id, task, description, status FROM task ORDER BY id ASC";
		List<Task> taskList = new ArrayList<Task>();

		try (Connection con = DBconnection.getConnection()) {

			prstmt = con.prepareStatement(getTaskQuery);
			res = prstmt.executeQuery();

			while (res.next()) {
				
				Task task = new Task(res.getInt("id"), res.getString("task"), res.getString("description"),
				res.getBoolean("status"));
				taskList.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskList;
	}

	// Update
	public void updateTask(String task, String description, boolean status, Integer key) {
		if (key == null) {
	        System.out.println("|---------Invalid choice! Task not found--------|");
	        return;
	    }
		   String updateQuery = "UPDATE TASK SET task = COALESCE(?, task), " +
                   "description = COALESCE(?, description), " +
                   "status = ? " +
                   "WHERE id = ?";
		   
		try (Connection con = DBconnection.getConnection()) {

			prstmt = con.prepareStatement(updateQuery);

			prstmt.setString(1, task.isEmpty() ? null : task);
			prstmt.setString(2, description.isEmpty()? null : description);
			prstmt.setBoolean(3,status);
			prstmt.setInt(4, key);

			int rows = prstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("|----------Task Updated successfully!----------|");
			}else {
				System.out.println("|---------Task not updated!---------|");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Delete
	public void deleteTask(Integer key) {
		if (key == null) {
			System.out.println("|----------Invalid choice! Task Not found----------|");
			return;
		}
		String deleteQuery = "DELETE FROM TASK WHERE id = ?";

		try (Connection con = DBconnection.getConnection()) {

			prstmt = con.prepareStatement(deleteQuery);

			prstmt.setInt(1, key);
			int rows = prstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("|----------Task Deleted successfully!----------|");
			} else {
				System.out.println("|---------Invalid ID! Task not found--------|");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
