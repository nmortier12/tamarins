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

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

/**
 * Test class used to play with Meter features of dropwizard metrics.
 * 
 * @author Nicolas
 */
public class MeterTest
{

    // --------------------------------------------
    // ATTRIBUTES
    // --------------------------------------------
    //<editor-fold defaultstate="expanded" desc="...">
    private final Meter meter;
    //</editor-fold>

    // --------------------------------------------
    // CONSTRUCTOR
    // --------------------------------------------
    //<editor-fold defaultstate="expanded" desc="...">
    public MeterTest()
    {
        meter = new Meter();
    }
    //</editor-fold>

    // --------------------------------------------
    // METHODS
    // --------------------------------------------
    //<editor-fold defaultstate="expanded" desc="...">
    @SuppressWarnings("SleepWhileInLoop")
    public void init(MetricRegistry registry)
    {
        registry.register(MeterTest.class.toString(), meter);

               new Thread(() ->
        {
            while (true)
            {
                try
                {
                    meter.mark();

                    Thread.sleep(100);
                }
                catch (InterruptedException ex)
                {
                    // Not hanlded on purpose
                }
            }
        }).start();
    }
    
    //</editor-fold>

}
