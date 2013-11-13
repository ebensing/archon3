package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import data.Artist;
import data.MongoResource;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import play.*;
import play.libs.F;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    private static Datastore db = MongoResource.INSTANCE.getDatastore("archon");

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static F.Promise<Result> addArtist() {
        final JsonNode json = request().body().asJson();

        F.Promise<Artist> artistPromise = F.Promise.promise(
                new F.Function0<Artist>() {
                    public Artist apply() {
                        Artist art = new Artist(true);
                        art.setName(json.findPath("name").textValue());
                        Query<Artist> q = Application.db.find(Artist.class);
                        try {
                            q = art.createQuery(new String[]{"name"}, Application.db);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        UpdateOperations<Artist> op = art.getUpdate(Application.db);

                        Application.db.findAndModify(q, op, false, true);

                        return art;
                    }
                }
        );
        return artistPromise.map(
                new F.Function<Artist, Result>() {
                    @Override
                    public Result apply(Artist artist) throws Throwable {
                        return ok(Json.toJson(artist));
                    }
                }
        );
    }

}
