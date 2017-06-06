/**
 * 
 */
package ws_login;

import javax.jws.WebService;


public class LoginServiceImp implements LoginServiceInf {

	public String login(String username, String password) {
		System.out.println(username + " " + password);
		System.out.println("webservice ��¼��֤���� ������");
		return "success";
	}

}
