package com.example.domain;

import org.junit.Test;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class VehiclePositionTest {

  @Test
  public void exampleTest() {
    VehiclePositionTestKit testKit = VehiclePositionTestKit.of(VehiclePosition::new);
    // use the testkit to execute a command
    // of events emitted, or a final updated state:
    // ValueEntityResult<SomeResponse> result = testKit.someOperation(SomeRequest);
    // verify the response
    // SomeResponse actualResponse = result.getReply();
    // assertEquals(expectedResponse, actualResponse);
    // verify the final state after the command
    // assertEquals(expectedState, testKit.getState());
  }

  @Test
  public void addPositionTest() {
    VehiclePositionTestKit testKit = VehiclePositionTestKit.of(VehiclePosition::new);
    // ValueEntityResult<Empty> result = testKit.addPosition(VehiclePosition.newBuilder()...build());
  }

}
