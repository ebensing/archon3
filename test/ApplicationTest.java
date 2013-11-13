import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;
import play.test.FakeRequest;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.MOVED_PERMANENTLY;
import static play.test.Helpers.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void testAddUser() {

        String body = "{ \"name\" : \"epica\" }";

        JsonNode jn = Json.parse(body);

        FakeRequest req = new FakeRequest(POST, "/artist/add").withJsonBody(jn);

        Result res = callAction(controllers.routes.ref.Application.addArtist(), req);

        assertThat(contentAsString(res)).contains("epica");
    }


}
