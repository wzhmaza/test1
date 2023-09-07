package helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.remote.SessionId;

public class SelBoxApiUtils {

    public static void postResult(SessionId sessionId, Boolean passedFailed) {
        if (sessionId == null) {
            throw new IllegalArgumentException("sessionId cannot be null.");
        }

        RestAssured.baseURI = "https://selenium.testingcloud.internal.rbigroup.cloud/e34/api/";

        try {
            RequestSpecification httpRequest = RestAssured.given();
            Response res = httpRequest
                    .queryParam("sessionId", sessionId.toString())
                    .and()
                    .queryParam("passed", passedFailed)
                    .post("/test-data");

            // Handle the response as needed
            // For example, you can check the response status code and log the result
            int statusCode = res.getStatusCode();
            if (statusCode != 200) {
                System.out.println("Failed to post data. Status code: " + statusCode);
                System.out.println("Response body: " + res.getBody().asString());
            }
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log the error)
            System.err.println("Error occurred while posting data: " + e.getMessage());
        }
    }
}
