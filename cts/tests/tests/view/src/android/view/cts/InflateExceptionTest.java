/*
 * Copyright (C) 2008 The Android Open Source Project
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
package android.view.cts;

import junit.framework.TestCase;
import android.view.InflateException;

public class InflateExceptionTest extends TestCase {
   public void testInflateException(){
       InflateException ne = null;
       boolean isThrowed = false;

       try {
           ne = new InflateException();
           throw ne;
       } catch (InflateException e) {
           assertSame(ne, e);
           isThrowed = true;
       } finally {
           if (!isThrowed) {
               fail("should throw out InflateException");
           }
       }

       String detailMessage = "testInflateException";
       Throwable throwable = new Exception();

       isThrowed = false;

       try {
           ne = new InflateException(detailMessage, throwable);
           throw ne;
       } catch (InflateException e) {
           assertSame(ne, e);
           isThrowed = true;
       } finally {
           if (!isThrowed) {
               fail("should throw out InflateException");
           }
       }

       isThrowed = false;

       try {
           ne = new InflateException(detailMessage);
           throw ne;
       } catch (InflateException e) {
           assertSame(ne, e);
           isThrowed = true;
       } finally {
           if (!isThrowed) {
               fail("should throw out InflateException");
           }
       }

       isThrowed = false;

       try {
           ne = new InflateException(throwable);
           throw ne;
       } catch (InflateException e) {
           assertSame(ne, e);
           isThrowed = true;
       } finally {
           if (!isThrowed) {
               fail("should throw out InflateException");
           }
       }
   }
}
