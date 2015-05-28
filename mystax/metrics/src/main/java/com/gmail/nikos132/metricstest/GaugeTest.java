/*
 * Copyright 2015 Nicolas Mortier.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gmail.nikos132.metricstest;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.util.Random;

/**
 * Test class used to play with Gauge features of dropwizard metrics.
 *
 * @author Nicolas
 */
public class GaugeTest
{
    private final Gauge<Double> gauge;

    private double value;

    private Random rand = new Random();

    // --------------------------------------------
    // CONSTRUCTOR
    // --------------------------------------------
    //<editor-fold defaultstate="expanded" desc="...">
    /**
     * Create a new GaugeTest instance.
     *
     */
    public GaugeTest()
    {
        // Return value as the gauge value
        gauge = () -> value;
    }
    //</editor-fold>

    // --------------------------------------------
    // METHODS
    // --------------------------------------------
    //<editor-fold defaultstate="expanded" desc="...">
    /**
     * Initialise this GaugeTest instance and register it to the given registry.
     *
     * @param registry the registry to link this GaugeTest instance to
     */
    public void init(MetricRegistry registry)
    {
        registry.register(GaugeTest.class.toString(), gauge);

        Thread updater = new Thread(
                () ->
                {
                    while (true)
                    {
                        value = rand.nextGaussian();
                        try
                        {
                            Thread.sleep(250);
                        }
                        catch (InterruptedException ex)
                        {
                            // Not hanlded on purpose
                        }
                    }

                }, "Gauge updater");

        updater.start();
    }
    //</editor-fold>
}
