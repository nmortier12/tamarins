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

import java.io.IOException;
import java.io.OutputStream;
import org.apache.log4j.Logger;

/**
 *
 * @author Nicolas
 */
public class MetricLoggingStream extends OutputStream
{
    private static final Logger LOGGER = Logger.getLogger(MetricLoggingStream.class);

    private static final int BUFFER_SIZE = 8192;
    
    // --------------------------------------------
    // ATTRIBUTES
    // --------------------------------------------
    //<editor-fold defaultstate="expanded" desc="...">
    private byte[] buffer = new byte[BUFFER_SIZE];
    
    private int byteCount = 0;
    
    //</editor-fold>
    // --------------------------------------------
    // OVERRIDED METHODS
    // --------------------------------------------
    //<editor-fold defaultstate="expanded" desc="...">
    @Override
    public void write(int b) throws IOException
    {
        buffer[byteCount++] = (byte)b;
    }
    
    
    //</editor-fold>·

    @Override
    public void flush() throws IOException
    {
        if( byteCount == 0 )
        {
            return;
        }
            
        byte[] b = new byte[byteCount];
        
        System.arraycopy(buffer, 0, b, 0, byteCount);
        
        String toLog = new String(b);
        
        if (LOGGER.isInfoEnabled())
        {
            LOGGER.info(toLog);
        }
        
        byteCount = 0;
    }

}
