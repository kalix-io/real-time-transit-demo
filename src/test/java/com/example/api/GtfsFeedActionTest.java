package com.example.api;

import org.junit.Test;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class GtfsFeedActionTest {

  @Test
  public void exampleTest() {
    GtfsFeedActionTestKit testKit = GtfsFeedActionTestKit.of(GtfsFeedAction::new);
    // use the testkit to execute a command
    // ActionResult<SomeResponse> result = testKit.someOperation(SomeRequest);
    // verify the response
    // SomeResponse actualResponse = result.getReply();
    // assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void getGtfsFeedTest() {
    GtfsFeedActionTestKit testKit = GtfsFeedActionTestKit.of(GtfsFeedAction::new);
    // ActionResult<BytesValue> result = testKit.getGtfsFeed(Empty.newBuilder()...build());
  }

}
