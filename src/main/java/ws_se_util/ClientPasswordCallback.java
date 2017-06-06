
package ws_se_util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

/**
 * @author xjb
 * @2016年5月9日
 */
public class ClientPasswordCallback implements CallbackHandler {

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

		pc.setKey("cxf".getBytes());
		pc.setPassword("wss4j");

	}

}
