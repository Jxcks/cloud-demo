package cn.xxxx.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.cloud.gateway.support.RouteMetadataUtils.CONNECT_TIMEOUT_ATTR;
import static org.springframework.cloud.gateway.support.RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR;

//@Configuration
public class GatewayConfig {

    /**
     * 注入RouteLocator的Bean
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        // gateway的配置
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // 路由规则
        routes.route("user-service",
                r -> r.path("/user/**")
                        .uri("http://localhost:8081")
                        .metadata(RESPONSE_TIMEOUT_ATTR, 200)
                        .metadata(CONNECT_TIMEOUT_ATTR, 200))
                .build();

        return routes.build();
    }
}
