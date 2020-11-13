package no.hvl.dat110.rest.counters;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.delete;

import java.util.ArrayList;
import java.util.Map;

import static spark.Spark.post;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static ArrayList<Todo> listOfTodos = new ArrayList<>();
	

	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}
		
		Todo exampleTodo = new Todo(0, "Default summary", "Default descripton");
		listOfTodos.add(exampleTodo);
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		get("/hello", (req, res) -> "Hello World!");
		
        get("/counters", (req, res) -> {
        	// Get list of todos
        	 Gson gson = new Gson();
        	return gson.toJson(listOfTodos);
        });
               
        put("/counters", (req,res) -> {
        	// Replace a todo
        	Gson gson = new Gson();
        	Todo newTodo = gson.fromJson(req.body(), Todo.class);
        	
        	for(Todo todo : listOfTodos) {
        		if(todo.getId() == newTodo.getId()) {
        			listOfTodos.remove(todo);
        			listOfTodos.add(newTodo);
        			return gson.toJson(listOfTodos);
        		}
        	}
            return "ID does not exist";
        });
        
        post("/counters", (req, res) -> {
        	// Create a new todo
            Gson gson = new Gson();
            Todo newTodo = gson.fromJson(req.body(), Todo.class);
            for (Todo todo : listOfTodos) {
                if (newTodo.getId() == todo.getId()) {
                    return "ID is not unique";
                }
            }
            // Add the new todo if the id is unique
            listOfTodos.add(newTodo);
            return gson.toJson(listOfTodos);
        });
        
        delete("/counters", (req, res) -> {
        	Gson gson = new Gson();
            Map<String, Double> map = gson.fromJson(req.body(), Map.class);
            int idToDelete = (map.get("id")).intValue();
            for (Todo todo : listOfTodos) {
                if (todo.getId() == idToDelete) {
                    listOfTodos.remove(todo);
                    break;
                }
            }
            return gson.toJson(listOfTodos);
        });
    }
    
}
