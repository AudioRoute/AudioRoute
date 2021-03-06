/*
 * Copyright 2019 n-Track S.r.l. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.ntrack.audioroute;

import android.app.Notification;

/**
 * Audioroute client interface for handling notifications when the state of the
 * audioroute changes. All methods are straightforward counterparts of methods
 * IAudiorouteService.aidl.
 */
oneway interface IAudiorouteClient {

  void onModuleCreated(String name, int inputChannels, int outputChannels, in Notification notification);
  void onModuleDeleted(String name);
  
  void onModuleActivated(String name, int samplerate, int buffersize, in int[] connectedInputBuses, in int[] connectedOutputBuses);
  void onModuleDeactivated(String name);
    
  void onPortsConnected(String source, int sourcePort, String sink, int sinkPort);
  void onPortsDisconnected(String source, int sourcePort, String sink, int sinkPort);
    
  void onStart();
  void onStop();
}
