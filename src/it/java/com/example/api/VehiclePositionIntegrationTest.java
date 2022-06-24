package com.example.api;

import com.example.Main;
import kalix.javasdk.testkit.junit.KalixTestKitResource;
import org.junit.ClassRule;
import org.junit.Test;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

// Example of an integration test calling our service via the Akka Serverless proxy
// Run all test classes ending with "IntegrationTest" using `mvn verify -Pit`
public class VehiclePositionIntegrationTest {

  /**
   * The test kit starts both the service container and the Akka Serverless proxy.
   */
  @ClassRule
  public static final KalixTestKitResource testKit =
    new KalixTestKitResource(Main.createKalix());

  /**
   * Use the generated gRPC client to call the service through the Akka Serverless proxy.
   */
  private final VehiclePositionService client;

  public VehiclePositionIntegrationTest() {
    client = testKit.getGrpcClient(VehiclePositionService.class);
  }

  @Test
  public void addPositionOnNonExistingEntity() throws Exception {
    // TODO: set fields in command, and provide assertions to match replies
    // client.addPosition(VehiclePositionApi.VehiclePosition.newBuilder().build())
    //         .toCompletableFuture().get(5, SECONDS);
  }
}
