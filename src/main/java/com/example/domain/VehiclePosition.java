package com.example.domain;

import com.google.protobuf.Empty;
import com.example.api.VehiclePositionApi;
import kalix.javasdk.valueentity.ValueEntityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
// This is the implementation for the Value Entity Service described in your com/matthewcasperson/api/vehicle_position_api.proto file.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class VehiclePosition extends AbstractVehiclePosition {
  private static final Logger LOG = LoggerFactory.getLogger(VehiclePosition.class);

  @SuppressWarnings("unused")
  private final String entityId;

  public VehiclePosition(ValueEntityContext context) {
    this.entityId = context.entityId();
  }

  @Override
  public VehiclePositionDomain.VehiclePositionState emptyState() {
    return VehiclePositionDomain.VehiclePositionState.getDefaultInstance();
  }

  @Override
  public Effect<Empty> addPosition(
      VehiclePositionDomain.VehiclePositionState currentState,
      VehiclePositionApi.VehiclePosition vehiclePosition) {
    LOG.info("Entity Id: " + entityId);

    final VehiclePositionDomain.VehiclePositionState state = VehiclePositionDomain.VehiclePositionState.newBuilder()
        .setEntityId(entityId)
        .setLatitude(vehiclePosition.getLatitude())
        .setLongitude(vehiclePosition.getLongitude())
        .setBearing(vehiclePosition.getBearing())
        .setOdometer(vehiclePosition.getOdometer())
        .setSpeed(vehiclePosition.getSpeed())
        .build();
    return effects().updateState(state).thenReply(Empty.getDefaultInstance());
  }
}
