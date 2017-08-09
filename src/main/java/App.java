import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Travel;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        //get: delete all posts
        get("/travel/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Travel.clearAllTravel();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        //show all posts
        get("/", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Travel> travels= Travel.getAll();
            model.put("travels", travels);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//get: show new post form
        get("/travel/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "travel-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new post form
        post("/travel/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String title = request.queryParams("title");
            String description = request.queryParams("description");
            Travel newTravel = new Travel(title,description);
            model.put("travel", newTravel);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual post
        get("/travel/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTravel = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Travel individualTravel = Travel.findById(idOfTravel); //use it to find post
            model.put("travel", individualTravel); //add it to model for template to display
            return new ModelAndView(model, "travel-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post
        get("/travel/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTravel = Integer.parseInt(req.params("id"));
            Travel editTravel = Travel.findById(idOfTravel);
            model.put("editTravel", editTravel);
            return new ModelAndView(model, "travel-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a post
        post("/travel/:id/update", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String newTitle = req.queryParams("title");
            String newDescription = req.queryParams("description");
            int idOfTravel = Integer.parseInt(req.params("id"));
            Travel editTravel = Travel.findById(idOfTravel);
            editTravel.update(newTitle, newDescription);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual post
        get("/travel/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTravel = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Travel tobeDeleted = Travel.findById(idOfTravel); //use it to find post
            tobeDeleted.deleteByID(idOfTravel);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
