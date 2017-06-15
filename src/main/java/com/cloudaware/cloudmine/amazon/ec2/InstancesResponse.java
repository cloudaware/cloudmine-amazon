package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.Reservation;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 17:36
 */
public final class InstancesResponse extends AmazonResponse {
    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(final List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
