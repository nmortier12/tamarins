/*
 * Copyright 2015 Nicolas MORTIER.
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

import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SlidingWindowReservoir;
import com.codahale.metrics.UniformReservoir;

/**
 * Test class used to play with Histograms features of dropwizard metrics.
 * 
 * @author Nicolas
 */
public class HistogramTest
{
    public HistogramTest()
    {
        
    }
    
    public void init(MetricRegistry registry)
    {
        Histogram edrh = new Histogram( new ExponentiallyDecayingReservoir());
        
        Histogram urh = new Histogram(new UniformReservoir());
        
        Histogram swrh = new Histogram(new SlidingWindowReservoir(1024));
        
    }
}
