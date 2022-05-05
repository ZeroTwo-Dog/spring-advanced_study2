package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import hello.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import hello.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import hello.proxy.config.v2_proxy.interface_proxy.v1_proxy.InterfaceProxyConfig2;
import hello.proxy.config.v3_proxyfactroy.ProxyFactoryConfigV1;
import hello.proxy.config.v3_proxyfactroy.ProxyFactoryConfigV2;
import hello.proxy.config.v4_postProcessor.BeanPostProcessorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
//@Import({InterfaceProxyConfig.class, InterfaceProxyConfig2.class})
//@Import(DynamicProxyBasicConfig.class)
//@Import(DynamicProxyFilterConfig.class)
@Import(BeanPostProcessorConfig.class)
//@Import(ProxyFactoryConfigV2.class)
//@Import(ProxyFactoryConfigV1.class)
//@Import({AppV2Config.class ,AppV1Config.class})
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);

	}

}
