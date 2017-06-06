/**
 * 
 */
package ws_se_util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class ServiceUtil {

	public static <T> T getService(Class<T> t, String url) {
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

		jaxWsProxyFactoryBean.setAddress(url);
		T create = jaxWsProxyFactoryBean.create(t);

		return create;
	}

}
