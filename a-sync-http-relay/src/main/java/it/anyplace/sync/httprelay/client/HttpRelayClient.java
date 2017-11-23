/* 
 * Copyright (C) 2016 Davide Imbriaco
 *
 * This Java file is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.anyplace.sync.httprelay.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

import it.anyplace.sync.core.beans.DeviceAddress;
import it.anyplace.sync.core.beans.DeviceAddress.AddressType;
import it.anyplace.sync.core.configuration.ConfigurationService;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author aleph
 */
public class HttpRelayClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ConfigurationService configuration;

    public HttpRelayClient(ConfigurationService configuration) {
        this.configuration = configuration;
    }

    public HttpRelayConnection openRelayConnection(DeviceAddress deviceAddress) throws Exception {
        checkNotNull(deviceAddress);
        checkArgument(EnumSet.of(AddressType.HTTP_RELAY, AddressType.HTTPS_RELAY).contains(deviceAddress.getType()));
        String httpRelayServerUrl = deviceAddress.getAddress().replaceFirst("^relay-", "");
        String deviceId = deviceAddress.getDeviceId();
        logger.info("open http relay connection, relay url = {}, target device id = {}", httpRelayServerUrl, deviceId);
        return new HttpRelayConnection(httpRelayServerUrl, deviceId);
    }

}
