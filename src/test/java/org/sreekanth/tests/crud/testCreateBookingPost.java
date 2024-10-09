package org.sreekanth.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.sreekanth.base.BaseTest;
import org.sreekanth.endpoints.APIConstants;
import org.sreekanth.pojos.BookingResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class testCreateBookingPost extends BaseTest{

    @Description("Verify that POST request is working fine")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void testVerifyCreateBookingPOST01() {
        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();
        validatableResponse = response.then().log().all();


        //Default Rest Assured
        validatableResponse.body("booking.firstname", Matchers.equalTo("James"));


        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("James");

        // TestNG Assertions
        assertActions.verifyStatusCode(response,200);
    }
}
