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

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.BasicConfigurator;

/**
 * Main class for the metrics test application.
 *
 * @author Nicolas
 */
public class MetricsTestsMain
{
    // --------------------------------------------
    // STATIC ATTRRIBUTES
    // --------------------------------------------
    //<editor-fold defaultstate="expanded" desc="...">
    public static MetricRegistry baseRegistry = new MetricRegistry();
    //</editor-fold>

    // --------------------------------------------
    // METHODS
    // --------------------------------------------
    //<editor-fold defaultstate="expanded" desc="...">
    /**
     * Entry point for metrics test application.
     *
     * @param args not used.
     */
    public static void main(String[] args)
    {
        BasicConfigurator.configure();
        
        // Meter metric
        new MeterTest().init(baseRegistry);

        // Gauge metric
        new GaugeTest().init(baseRegistry);

        // Report to the console every 3 seconds
        ConsoleReporter cReporter = ConsoleReporter.forRegistry(baseRegistry).
                convertDurationsTo(TimeUnit.MILLISECONDS).
                convertRatesTo(TimeUnit.SECONDS).
                outputTo(new PrintStream(new MetricLoggingStream())).
                build();
        cReporter.start(3, TimeUnit.SECONDS);
    }
    //</editor-fold>

}
