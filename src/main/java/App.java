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
    }
}
