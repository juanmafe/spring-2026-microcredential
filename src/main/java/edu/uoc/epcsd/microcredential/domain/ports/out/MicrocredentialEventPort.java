package edu.uoc.epcsd.microcredential.domain.ports.out;

import edu.uoc.epcsd.microcredential.domain.Microcredential;

/** Outbound port to publish microcredential domain events. */
public interface MicrocredentialEventPort {

  /**
   * Publishes an event when a microcredential has been requested (pending approval).
   *
   * @param microcredential the requested microcredential
   */
  void publishPending(Microcredential microcredential);

  /**
   * Publishes an event when a microcredential has been approved.
   *
   * @param microcredential the approved microcredential
   */
  void publishApproved(Microcredential microcredential);

  /**
   * Publishes an event when a microcredential has been rejected.
   *
   * @param microcredential the rejected microcredential
   */
  void publishRejected(Microcredential microcredential);
}

