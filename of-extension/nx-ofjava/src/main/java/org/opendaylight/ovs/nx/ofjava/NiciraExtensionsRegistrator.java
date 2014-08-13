/*
 * Copyright (C) 2014 Red Hat, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Authors : Madhu Venugopal
 */
package org.opendaylight.ovs.nx.ofjava;
import org.opendaylight.openflowjava.nx.api.NiciraExtensionCodecRegistrator;
import org.opendaylight.ovs.nx.ofjava.codec.action.NiciraActionCodecs;
import org.opendaylight.ovs.nx.ofjava.codec.action.ResubmitCodec;
import org.opendaylight.ovs.nx.ofjava.codec.action.SetNsiCodec;
import org.opendaylight.ovs.nx.ofjava.codec.action.SetNspCodec;

import com.google.common.base.Preconditions;

public class NiciraExtensionsRegistrator implements AutoCloseable {

    private final NiciraExtensionCodecRegistrator registrator;
    public NiciraExtensionsRegistrator(NiciraExtensionCodecRegistrator registrator) {
        Preconditions.checkNotNull(registrator);
        this.registrator = registrator;
    }

    public void registerNiciraExtensions() {
        registrator.registerActionDeserializer(ResubmitCodec.RESUBMIT_DESERIALIZER_KEY, NiciraActionCodecs.RESUBMIT_CODEC);
        registrator.registerActionDeserializer(ResubmitCodec.RESUBMIT_TABLE_DESERIALIZER_KEY, NiciraActionCodecs.RESUBMIT_CODEC);
        registrator.registerActionSerializer(ResubmitCodec.SERIALIZER_KEY, NiciraActionCodecs.RESUBMIT_CODEC);

        registrator.registerActionDeserializer(SetNspCodec.DESERIALIZER_KEY, NiciraActionCodecs.SET_NSP_CODEC);
        registrator.registerActionSerializer(SetNspCodec.SERIALIZER_KEY, NiciraActionCodecs.SET_NSP_CODEC);

        registrator.registerActionDeserializer(SetNsiCodec.DESERIALIZER_KEY, NiciraActionCodecs.SET_NSI_CODEC);
        registrator.registerActionSerializer(SetNsiCodec.SERIALIZER_KEY, NiciraActionCodecs.SET_NSI_CODEC);
    }

    public void unregisterExtensions() {
        registrator.unregisterActionDeserializer(ResubmitCodec.RESUBMIT_DESERIALIZER_KEY);
        registrator.unregisterActionDeserializer(ResubmitCodec.RESUBMIT_TABLE_DESERIALIZER_KEY);
        registrator.unregisterActionSerializer(ResubmitCodec.SERIALIZER_KEY);
        registrator.unregisterActionDeserializer(SetNsiCodec.DESERIALIZER_KEY);
        registrator.unregisterActionSerializer(SetNsiCodec.SERIALIZER_KEY);
        registrator.unregisterActionDeserializer(SetNspCodec.DESERIALIZER_KEY);
        registrator.unregisterActionSerializer(SetNspCodec.SERIALIZER_KEY);
    }

    @Override
    public void close() throws Exception {
        unregisterExtensions();
    }

}