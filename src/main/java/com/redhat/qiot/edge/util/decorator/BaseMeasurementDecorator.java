/**
 * 
 */
package com.redhat.qiot.edge.util.decorator;

import java.io.StringReader;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map.Entry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.slf4j.Logger;

import com.redhat.qiot.edge.service.edge.MeasurementStationService;

/**
 * @author Andrea
 *
 */
@ApplicationScoped
public class BaseMeasurementDecorator implements MeasurementDecorator {

    @Inject
    Logger LOGGER;

    @Inject
    MeasurementStationService measurementStationService;

    @Override
    public String decorate(String measurement) {
        JsonObject mJsonObject = null;
        JsonObject finalJsonObject = null;
        JsonObjectBuilder job = null;
        String decoratedMeasurement = null;
        int stationId = 0;
        try (JsonReader reader = Json
                .createReader(new StringReader(measurement));) {
            mJsonObject = reader.readObject();
        }
        job = Json.createObjectBuilder();
            stationId = measurementStationService.getStationId();
        job.add("stationId", stationId)//
                .add("instant", OffsetDateTime.now(ZoneOffset.UTC).toInstant()
                        .toString());
        for (Entry<String, JsonValue> entry : mJsonObject.entrySet()) {
            job.add(entry.getKey(), entry.getValue());
        }
        mJsonObject = null;
        finalJsonObject = job.build();
        job = null;
        decoratedMeasurement = finalJsonObject.toString();
        finalJsonObject = null;
        return decoratedMeasurement;
    }

}
