/**
 * 
 */
package ws_login;

import javax.xml.ws.Endpoint;

public class Test_Ws {

	public static void main(String[] args) {

		LoginServiceInf service = new LoginServiceImp();

		Endpoint.publish("http://localhost:1234/login", service);

	}

}
