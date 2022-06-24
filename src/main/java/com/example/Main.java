package com.example;

import com.example.api.GtfsFeedAction;
import com.example.domain.VehiclePosition;
import com.example.view.AllVehiclePositionsView;
import kalix.javasdk.Kalix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public final class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  public static Kalix createKalix() {
    // The AkkaServerlessFactory automatically registers any generated Actions, Views or Entities,
    // and is kept up-to-date with any changes in your protobuf definitions.
    // If you prefer, you may remove this and manually register these components in a
    // `new AkkaServerless()` instance.
    return KalixFactory.withComponents(
      VehiclePosition::new,
      AllVehiclePositionsView::new,
      GtfsFeedAction::new);
  }

  public static void main(String[] args) throws Exception {
    LOG.info("starting the Kalix service");
    createKalix().start();
  }
}
