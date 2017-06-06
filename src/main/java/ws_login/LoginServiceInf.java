/**
 * 
 */
package ws_login;

import javax.jws.WebService;

@WebService
public interface LoginServiceInf {

	public String login(String username, String password);

}
