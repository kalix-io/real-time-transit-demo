package com.example.api;

import akka.NotUsed;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.google.protobuf.BytesValue;
import com.google.protobuf.Empty;
import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedHeader;
import com.google.transit.realtime.GtfsRealtime.FeedHeader.Incrementality;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.FeedMessage.Builder;
import com.google.transit.realtime.GtfsRealtime.Position;
import com.google.transit.realtime.GtfsRealtime.VehiclePosition;
import com.example.domain.VehiclePositionDomain.VehiclePositionState;
import com.example.view.AllVehiclePositions;
import java.util.List;
import java.util.concurrent.CompletionStage;
import kalix.javasdk.action.ActionCreationContext;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
// This is the implementation for the Action Service described in your com/matthewcasperson/api/gtfs_feed.proto file.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class GtfsFeedAction extends AbstractGtfsFeedAction {

  private static final Logger LOG = LoggerFactory.getLogger(GtfsFeedAction.class);

  private final ActionCreationContext creationContext;

  public GtfsFeedAction(ActionCreationContext creationContext) {
    this.creationContext = creationContext;
  }

  @Override
  public Effect<BytesValue> getGtfsFeed(Empty empty) {
    try {
      final AllVehiclePositions service = actionContext().getGrpcClient(
          AllVehiclePositions.class,
          "realtimetransitdemo");

      final Source<VehiclePositionState, NotUsed> positions =
          service.getAllVehiclePositions(Empty.getDefaultInstance());

      final CompletionStage<List<VehiclePositionState>> positionsList = positions
          .take(1000)
          .runWith(Sink.seq(), creationContext.materializer());

      final Builder feedMessageBuilder = FeedMessage.newBuilder();
      final FeedHeader header = FeedHeader.newBuilder()
          .setGtfsRealtimeVersion("2.0")
          .setIncrementality(Incrementality.FULL_DATASET)
          .setTimestamp(System.currentTimeMillis() / 1000)
          .build();
      feedMessageBuilder.setHeader(header);

      positionsList.toCompletableFuture().get()
          .forEach(p -> addVehiclePositions(feedMessageBuilder, p));

      final FeedMessage feedMessage = feedMessageBuilder.build();

      LOG.info(feedMessage.toString());

      return effects().reply(BytesValue.of(feedMessage.toByteString()));
    } catch (Exception e) {
      LOG.error(e + "\n" + ExceptionUtils.getStackTrace(e));
      throw new RuntimeException(e);
    }
  }

  public void addVehiclePositions(
      final Builder builder,
      final VehiclePositionState position) {

    final Position gtfsPosition = Position.newBuilder()
        .setBearing(position.getBearing())
        .setLatitude(position.getLatitude())
        .setLongitude(position.getLongitude())
        .setOdometer(position.getOdometer())
        .setSpeed(position.getSpeed())
        .build();

    final VehiclePosition vehiclePosition = VehiclePosition.newBuilder()
        .setPosition(gtfsPosition)
        .build();

    final FeedEntity entity = FeedEntity.newBuilder()
        .setId(position.getEntityId())
        .setVehicle(vehiclePosition)
        .build();

    builder.addEntity(entity);
  }
}
