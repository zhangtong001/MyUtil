
package ws_se_util;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;

/**
 * @author xjb
 * @2016年5月9日
 */
public class MyServiceFactory {

	public static <T> T getService(Class<T> t, String url) {
		// 创建客户端请求代理
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

		// 想请求代理里面放入请求wsdl地址
		jaxWsProxyFactoryBean.setAddress(url);// "http://localhost:8080/stuws/student?wsdl"

		// 设置请求代理所代理的ws接口
		jaxWsProxyFactoryBean.setServiceClass(t.getClass());

		// 如果是服务器加入了InInterceptor的拦截器，设置该拦截器的构造参数，map
		// 该构造参数map对应服务器上的InInterceptor的设置
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);

		// 根服务器上的PasswordText类型一样就行了
		map.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");

		// 这里有一个问题
		map.put(WSHandlerConstants.USER, "username");

		// 设置拦截器的回调函数，可以由程序员控制校验方式
		map.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallback.class.getName());

		// 将设置好的校验方式map放入代理客户端请求的wss4jOutInterceptor
		WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(map);

		// 现将拦截器放入集合再将集合放入OutInterceptors
		// jaxWsProxyFactoryBean.setOutInterceptors(new ArrayList<Map>());

		// 不用讲拦截器放入集合，将拦截器一个一个的放入jaxWsProxyFactoryBean代理工厂
		jaxWsProxyFactoryBean.getOutInterceptors().add(wss4jOutInterceptor);

		// 通过客户端代理工厂创建ws请求代理
		T s = null;
		try {
			s = (T) jaxWsProxyFactoryBean.create(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s;

	}

}
